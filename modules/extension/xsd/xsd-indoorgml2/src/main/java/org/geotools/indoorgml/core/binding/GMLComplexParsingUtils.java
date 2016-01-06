package org.geotools.indoorgml.core.binding;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDParticle;
import org.geotools.data.DataUtilities;
import org.geotools.feature.ComplexFeatureBuilder;
import org.geotools.feature.NameImpl;
import org.geotools.feature.TypeBuilder;
import org.geotools.feature.complex.FeatureTypeBuilder;
import org.geotools.feature.complex.PNUFeatureFactory;
import org.geotools.feature.type.FeatureTypeFactoryImpl;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.gml2.FeatureTypeCache;
import org.geotools.gml2.GML;
import org.geotools.referencing.CRS;
import org.geotools.util.Converters;
import org.geotools.util.logging.Logging;
import org.geotools.xml.Binding;
import org.geotools.xml.BindingWalkerFactory;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.geotools.xml.Schemas;
import org.geotools.xml.impl.BindingWalker;
import org.geotools.xs.bindings.XSAnyTypeBinding;
import org.opengis.feature.Association;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Geometry;

/**
 * 
 */

/**
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public class GMLComplexParsingUtils {
    /**
     * logging instance
     */
    static Logger LOGGER = Logging.getLogger( "org.geotools.indoorgml" );
    
    public static Feature parseFeature(ElementInstance instance, Node node, Object value, FeatureTypeCache ftCache, BindingWalkerFactory bwFactory) throws Exception {
        //get the definition of the element
        XSDElementDeclaration decl = instance.getElementDeclaration();
    
        //special case, if the declaration is abstract it is probably "_Feautre" 
        // which means we are parsing an element which could not be found in the 
        // schema, so instead of using the element declaration to build the 
        // type, just use the node given to us
        FeatureType fType = null;
    
        if (!decl.isAbstract()) {
            //first look in cache
            fType = ftCache.get(new NameImpl(decl.getTargetNamespace(), decl.getName()));
            
            FeatureType temp = null;
            if(fType != null && fType.getUserData().get("TEMP") != null) {
                temp = fType;
                fType = null;
            }
            
            if (fType == null) {
                // let's use the CRS from the node (only if it's available) on the feature type
                CoordinateReferenceSystem crs = null;
                if (node.hasChild("boundedBy") && node.getChild("boundedBy").hasChild("Box")) {
                    crs = crs(node.getChild("boundedBy").getChild("Box"));
                } else if (node.hasChild("boundedBy") && node.getChild("boundedBy").hasChild("Envelope")) {
                    crs = crs(node.getChild("boundedBy").getChild("Envelope"));
                }
    
                //build from element declaration
                fType = GMLComplexParsingUtils.featureType(decl, bwFactory, ftCache, crs);
                if(temp == null) ftCache.put(fType);
            }
            temp = fType;
        } else {
            // first look in cache
            fType = ftCache.get(new NameImpl(node.getComponent().getNamespace(), node.getComponent().getName()));
    
            if (fType == null) {
                //build from node
                fType = GMLComplexParsingUtils.featureType(node);
                ftCache.put(fType);
            }
        }
    
        //fid
        String fid = (String) node.getAttributeValue("fid");
    
        if (fid == null) {
            //look for id
            fid = (String) node.getAttributeValue("id");
        }
    
        //create feature
        return GMLComplexParsingUtils.feature(fType, fid, node);
    }
    
    public static Association parseAssociation(ElementInstance instance, Node node, Object value, FeatureTypeCache ftCache, BindingWalkerFactory bwFactory) throws Exception {
        XSDElementDeclaration decl = instance.getElementDeclaration();
        
        
        AssociationDescriptor associationDescriptor = associationDescriptor(decl, ftCache, bwFactory);
        
        
        
        
        return association(decl, node, associationDescriptor);
    }
    
    public static AssociationDescriptor associationDescriptor(XSDElementDeclaration decl, FeatureTypeCache ftCache, BindingWalkerFactory bwFactory) throws Exception {
        TypeBuilder tBuilder = new TypeBuilder( new FeatureTypeFactoryImpl() );
        tBuilder.setName(decl.getName());
        tBuilder.setNamespaceURI(decl.getTargetNamespace());
        
        List children = Schemas.getChildElementParticles(decl.getType(), true);
        for (Iterator itr = children.iterator(); itr.hasNext();) {
            XSDParticle particle = (XSDParticle) itr.next();
            XSDElementDeclaration property = (XSDElementDeclaration) particle.getContent();
            
            if (property.isElementDeclarationReference()) {
                property = property.getResolvedElementDeclaration();
            }
            
            final ArrayList bindings = new ArrayList();
            BindingWalker.Visitor visitor = new BindingWalker.Visitor() {
                    public void visit(Binding binding) {
                        //TODO
                        bindings.add(binding);
                    }
                };

            bwFactory.walk(property, visitor);
            
            
            //Binding last = ((Binding) bindings.get(bindings.size() - 1));
            //QName targetName = last.getTarget();
            
            FeatureType fType = ftCache.get(new NameImpl(property.getTargetNamespace(), property.getName()));
            if(fType == null) {
                System.out.println("비어잇네 샹");
                /*FeatureTypeBuilder b = new FeatureTypeBuilder();
                b.setName(property.getName());
                b.setNamespaceURI(property.getTargetNamespace());
                FeatureType temp = b.feature();
                Map<Object, Object> userMap = temp.getUserData();
                userMap.put("TEMP", "TRUE");
                fType = temp;
                ftCache.put(temp);*/
            }
            
            tBuilder.referenceType(fType);
            
            int min = particle.getMinOccurs();
            int max = particle.getMaxOccurs();
            //check for uninitialized values
            if (min == -1) min = 0;
            if (max == -1) max = 1;
            
            tBuilder.cardinality(min, max);
        }
        AssociationType type = tBuilder.association();
        tBuilder.setPropertyType(type);
        return tBuilder.associationDescriptor();
    }
    
    public static Association association(XSDElementDeclaration decl, Node node, AssociationDescriptor desc) {
        PNUFeatureFactory factory = new PNUFeatureFactory();
        Feature related = (Feature) node.getChildValue(Feature.class);
        return factory.createAssociation(related, desc);
    }
    
    public static FeatureType featureType(XSDElementDeclaration element,
            BindingWalkerFactory bwFactory, FeatureTypeCache ftCache, CoordinateReferenceSystem crs) throws Exception {
            
            FeatureTypeBuilder tBuilder = new FeatureTypeBuilder();
            tBuilder.setName(element.getName());
            tBuilder.setNamespaceURI(element.getTargetNamespace());
            
            FeatureTypeBuilder propBuilder = new FeatureTypeBuilder();
            // build the feature type by walking through the elements of the
            // actual xml schema type
            List children = Schemas.getChildElementParticles(element.getType(), true);
            for (Iterator itr = children.iterator(); itr.hasNext();) {
                XSDParticle particle = (XSDParticle) itr.next();
                XSDElementDeclaration property = (XSDElementDeclaration) particle.getContent();

                if (property.isElementDeclarationReference()) {
                    property = property.getResolvedElementDeclaration();
                }

                final ArrayList bindings = new ArrayList();
                BindingWalker.Visitor visitor = new BindingWalker.Visitor() {
                        public void visit(Binding binding) {
                            //TODO
                            bindings.add(binding);
                        }
                    };
                bwFactory.walk(property, visitor);

                if (bindings.isEmpty()) {
                    // could not find a binding, use the defaults
                    LOGGER.fine( "Could not find binding for " + property.getQName() + ", using XSAnyTypeBinding." );
                    bindings.add( new XSAnyTypeBinding() );
                }

                // get the last binding in the chain to execute
                Binding last = ((Binding) bindings.get(bindings.size() - 1));
                Class theClass = last.getType();

                if (theClass == null) {
                    throw new RuntimeException("binding declares null type: " + last.getTarget());
                }

                // get the attribute properties
                int min = particle.getMinOccurs();
                int max = particle.getMaxOccurs();
                
                //check for uninitialized values
                if (min == -1) min = 0;
                if (max == -1) max = 1;
                propBuilder.cardinality(min, max);
                
                // if the next property is of type geometry, let's set its CRS
                if (Geometry.class.isAssignableFrom(theClass) && crs != null) {
                    propBuilder.crs(crs);
                }
                
                String propName = property.getName();
                Class<?> binding = theClass;
                
                propBuilder.setBinding(binding);
                propBuilder.setName(propName);
                PropertyDescriptor descriptor = null;
                Name defaultGeom = tBuilder.getDefaultGeometry();
                if( defaultGeom != null && defaultGeom.equals(propName) || Geometry.class.isAssignableFrom(binding)) {
                    
                    //TODO if default crs is not set
                    if(propBuilder.getCRS() == null) {
                        //TODO
                        //set default crs
                    }
                    GeometryType type = propBuilder.geometry();
                    propBuilder.setName(propName);
                    propBuilder.setPropertyType(type);
                    descriptor = propBuilder.attributeDescriptor();
                }
                else {
                    PropertyType type = null;
                    /*if(Association.class.isAssignableFrom(binding)) {
                        descriptor = associationDescriptor(property, ftCache, bwFactory);
                    }*/
                    /*
                    if(Feature.class.isAssignableFrom(binding)) {
                        type = ftCache.get(new NameImpl(property.getTargetNamespace(), property.getName()));
                        //Create Association Type
                    }*/
                    type = propBuilder.attribute();
                    propBuilder.setName(propName);
                    propBuilder.setPropertyType(type);
                    descriptor = propBuilder.attributeDescriptor();
                }
                
                if(descriptor != null) tBuilder.add(descriptor);
                
                //set the default geometry explicitly. Note we're comparing the GML namespace
                //with String.startsWith to catch up on the GML 3.2 namespace too, which is hacky.
                final String propNamespace = property.getTargetNamespace();
                if (Geometry.class.isAssignableFrom(theClass)
                        && (propNamespace == null || !propNamespace.startsWith(GML.NAMESPACE))) {
                    //only set if non-gml, we do this because of "gml:location", 
                    // we dont want that to be the default if the user has another
                    // geometry attribute
                    if (tBuilder.getDefaultGeometry() == null) {
                        tBuilder.setDefaultGeometry(property.getName());
                    }
                }
            }

            return tBuilder.feature();
        }
    
    public static FeatureType featureType(Node node)
            throws Exception {
            
            FeatureTypeBuilder tBuilder = new FeatureTypeBuilder();
            
            tBuilder.setName(node.getComponent().getName());
            tBuilder.setNamespaceURI(node.getComponent().getNamespace());
            tBuilder.setCRS(null); //JD: set explicitly to null to avoid warning

            CoordinateReferenceSystem crs = null;
            
            //mandatory gml attributes
            if (!node.hasChild("description")) {
                tBuilder.attribute("description", String.class);
            }

            if (!node.hasChild("name")) {
                tBuilder.attribute("name", String.class);
            }

            if (!node.hasChild("boundedBy")) {
                tBuilder.attribute("boundedBy", ReferencedEnvelope.class);
            } else {
                if (node.getChild("boundedBy").hasChild("Box")) {
                    crs = crs(node.getChild("boundedBy").getChild("Box"));
                } else if (node.getChild("boundedBy").hasChild("Envelope")) {
                    crs = crs(node.getChild("boundedBy").getChild("Envelope"));
                }
            }

            //application schema defined attributes
            for (Iterator c = node.getChildren().iterator(); c.hasNext();) {
                Node child = (Node) c.next();
                String name = child.getComponent().getName();
                Object valu = child.getValue();

                // if the next property is of type geometry, let's set its CRS
                if (Geometry.class.isAssignableFrom(valu.getClass()) && crs != null) {
                    tBuilder.crs(crs);
                }

                //TODO
                //ftBuilder.add(name, (valu != null) ? valu.getClass() : Object.class);
            }

            return tBuilder.feature();
        }
    
    public static Feature feature(FeatureType fType, String fid, Node node)
            throws Exception {
        ComplexFeatureBuilder b = new ComplexFeatureBuilder(fType);
    
        Collection<PropertyDescriptor> descriptors = fType.getDescriptors();
        Iterator<PropertyDescriptor> dIter = descriptors.iterator();
    
        while(dIter.hasNext()) {
            PropertyDescriptor p = dIter.next();
            PropertyType pType = p.getType();
            
            
            
            List valueList = node.getChildValues(p.getName().getLocalPart());
            for(int i = 0; i < valueList.size(); i++) {
                Object value = valueList.get(i);
                
                if ((value != null) && !pType.getBinding().isAssignableFrom(value.getClass())) {
                    //type mismatch, to try convert
                    Object converted = Converters.convert(value, pType.getBinding());
    
                    if (converted != null) {
                        value = converted;
                    }
                }
                
                if ( value == null) {
                    //if the content is null and the descriptor says isNillable is false, 
                    // then set the default value
                    if (!p.isNillable()) {
                        value = DataUtilities.defaultValue(p.getType().getBinding());
                    }
                } else {
                    Class<?> target = p.getType().getBinding(); 
                    Object converted = Converters.convert(value, target);
                    if(converted != null) {
                        value = converted;
                    }
                }
                
                //b.append(p.getName(), (Property) value);
            }
        }
        
        /*        int attributeCount = fType.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                AttributeDescriptor att = fType.getDescriptor(i);
                AttributeType attType = att.getType();
                Object attValue = node.getChildValue(att.getLocalName());
    
                if ((attValue != null) && !attType.getBinding().isAssignableFrom(attValue.getClass())) {
                    //type mismatch, to try convert
                    Object converted = Converters.convert(attValue, attType.getBinding());
    
                    if (converted != null) {
                        attValue = converted;
                    }
                }
    
                b.add(attValue);
            }*/
    
        //create the feature
        return b.buildFeature(fid);
    }
    
    public static CoordinateReferenceSystem crs(Node node) {
        if (node.getAttribute("srsName") != null) {
            URI srs = null;
            Object raw = node.getAttributeValue("srsName");
    
            if (raw instanceof URI) {
                srs = (URI) raw;
            } else if (raw instanceof String) {
                //try to parse into a uri
                try {
                    srs = new URI((String) raw);
                } catch (URISyntaxException e) {
                    //failed, continue on
                }
            }
    
            if (srs != null) {
                try {
                    return CRS.decode(srs.toString());
                } catch (Exception e) {
                    throw new RuntimeException("Could not create crs: " + srs, e);
                }
            }
        }
        return null;
    }

}
