

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import org.geotools.feature.NameImpl;
import org.geotools.feature.type.AbstractLazyAttributeTypeImpl;
import org.geotools.feature.type.AbstractLazyComplexTypeImpl;
import org.geotools.feature.type.AttributeDescriptorImpl;
import org.geotools.feature.type.SchemaImpl;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.Schema;
import org.geotools.gml3.v3_2.GMLSchema;
import org.geotools.xlink.XLINKSchema;
import org.geotools.xs.XSSchema;

public class INDOORCORESchema2 extends SchemaImpl {

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceBoundaryMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="0"&gt;
     *  					&lt;xs:element ref="CellSpaceBoundary"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACEBOUNDARYMEMBERTYPE_TYPE = build_CELLSPACEBOUNDARYMEMBERTYPE_TYPE();
    
    private static ComplexType build_CELLSPACEBOUNDARYMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACEBOUNDARYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundary"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceBoundaryPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="CellSpaceBoundary"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACEBOUNDARYPROPERTYTYPE_TYPE = build_CELLSPACEBOUNDARYPROPERTYTYPE_TYPE();
    
    private static ComplexType build_CELLSPACEBOUNDARYPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACEBOUNDARYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundary"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceBoundaryType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="TransitionPropertyType"/&gt;
     *  					&lt;xs:group minOccurs="0" ref="CellSpaceBoundaryGeometry"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="externalReference" type="ExternalReferenceType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACEBOUNDARYTYPE_TYPE = build_CELLSPACEBOUNDARYTYPE_TYPE();
    
    private static ComplexType build_CELLSPACEBOUNDARYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TRANSITIONPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","duality"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.SURFACEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","geometry3D"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.CURVEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","geometry2D"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        EXTERNALREFERENCETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","externalReference"),
                        0, 2147483647, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="0"&gt;
     *  					&lt;xs:element ref="CellSpace"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACEMEMBERTYPE_TYPE = build_CELLSPACEMEMBERTYPE_TYPE();
    
    private static ComplexType build_CELLSPACEMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpacePropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="CellSpace"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACEPROPERTYTYPE_TYPE = build_CELLSPACEPROPERTYTYPE_TYPE();
    
    private static ComplexType build_CELLSPACEPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpacePropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:group maxOccurs="1" minOccurs="0" ref="CellSpaceGeometry"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="StatePropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="externalReference" type="ExternalReferenceType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="partialboundedBy" type="CellSpaceBoundaryPropertyType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType CELLSPACETYPE_TYPE = build_CELLSPACETYPE_TYPE();
    
    private static ComplexType build_CELLSPACETYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.SOLIDPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Geometry3D"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.SURFACEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Geometry2D"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","duality"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        EXTERNALREFERENCETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","externalReference"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACEBOUNDARYPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","partialboundedBy"),
                        0, 2147483647, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="EdgesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="transitionMember" type="TransitionMemberType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AggregationAttributeGroup"/&gt;
     *  				&lt;xs:attributeGroup ref="gml:OwnershipAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType EDGESTYPE_TYPE = build_EDGESTYPE_TYPE();
    
    private static ComplexType build_EDGESTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","EdgesType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TRANSITIONMEMBERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","transitionMember"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.AGGREGATIONTYPE_TYPE,
                        new NameImpl("aggregationType"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.BOOLEAN_TYPE,
                        new NameImpl("owns"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="ExternalReferenceType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence&gt;
     *  			&lt;xs:element minOccurs="0" name="informationSystem" type="xs:anyURI"/&gt;
     *  			&lt;xs:element name="externalObject" type="externalObjectReferenceType"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType EXTERNALREFERENCETYPE_TYPE = build_EXTERNALREFERENCETYPE_TYPE();
    
    private static ComplexType build_EXTERNALREFERENCETYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","ExternalReferenceType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","informationSystem"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        EXTERNALOBJECTREFERENCETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","externalObject"),
                        1, 1, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="IndoorFeaturesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="primalSpaceFeatures" type="PrimalSpaceFeaturesPropertyType"/&gt;
     *  					&lt;xs:element ref="MultiLayeredGraph"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType INDOORFEATURESTYPE_TYPE = build_INDOORFEATURESTYPE_TYPE();
    
    private static ComplexType build_INDOORFEATURESTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","IndoorFeaturesType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        PRIMALSPACEFEATURESPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","primalSpaceFeatures"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        MULTILAYEREDGRAPHTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraph"),
                        1, 1, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="InterEdgesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="interLayerConnectionMember" type="InterLayerConnectionMemberType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AggregationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType INTEREDGESTYPE_TYPE = build_INTEREDGESTYPE_TYPE();
    
    private static ComplexType build_INTEREDGESTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterEdgesType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        INTERLAYERCONNECTIONMEMBERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","interLayerConnectionMember"),
                        1, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.AGGREGATIONTYPE_TYPE,
                        new NameImpl("aggregationType"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="InterLayerConnectionMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="0"&gt;
     *  					&lt;xs:element ref="InterLayerConnection"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType INTERLAYERCONNECTIONMEMBERTYPE_TYPE = build_INTERLAYERCONNECTIONMEMBERTYPE_TYPE();
    
    private static ComplexType build_INTERLAYERCONNECTIONMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        INTERLAYERCONNECTIONTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnection"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="InterLayerConnectionPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="InterLayerConnection"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType INTERLAYERCONNECTIONPROPERTYTYPE_TYPE = build_INTERLAYERCONNECTIONPROPERTYTYPE_TYPE();
    
    private static ComplexType build_INTERLAYERCONNECTIONPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        INTERLAYERCONNECTIONTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnection"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="InterLayerConnectionType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="typeOfTopoExpression" type="typeOfTopoExpressionCodeType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="comment" type="xs:string"/&gt;
     *  					&lt;xs:element maxOccurs="2" minOccurs="2" name="interConnects" type="StatePropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="2" minOccurs="2" name="ConnectedLayers" type="SpaceLayerPropertyType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType INTERLAYERCONNECTIONTYPE_TYPE = build_INTERLAYERCONNECTIONTYPE_TYPE();
    
    private static ComplexType build_INTERLAYERCONNECTIONTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TYPEOFTOPOEXPRESSIONCODETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpression"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","comment"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","interConnects"),
                        2, 2, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","ConnectedLayers"),
                        2, 2, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="MultiLayeredGraphPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="MultiLayeredGraph"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType MULTILAYEREDGRAPHPROPERTYTYPE_TYPE = build_MULTILAYEREDGRAPHPROPERTYTYPE_TYPE();
    
    private static ComplexType build_MULTILAYEREDGRAPHPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraphPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        MULTILAYEREDGRAPHTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraph"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="MultiLayeredGraphType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="spaceLayers" type="SpaceLayersType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="interEdges" type="InterEdgesType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType MULTILAYEREDGRAPHTYPE_TYPE = build_MULTILAYEREDGRAPHTYPE_TYPE();
    
    private static ComplexType build_MULTILAYEREDGRAPHTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraphType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERSTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","spaceLayers"),
                        1, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        INTEREDGESTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","interEdges"),
                        0, 2147483647, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="NodesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="stateMember" type="StateMemberType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AggregationAttributeGroup"/&gt;
     *  				&lt;xs:attributeGroup ref="gml:OwnershipAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType NODESTYPE_TYPE = build_NODESTYPE_TYPE();
    
    private static ComplexType build_NODESTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","NodesType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATEMEMBERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","stateMember"),
                        1, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.AGGREGATIONTYPE_TYPE,
                        new NameImpl("aggregationType"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.BOOLEAN_TYPE,
                        new NameImpl("owns"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="PrimalSpaceFeaturesPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="PrimalSpaceFeatures"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType PRIMALSPACEFEATURESPROPERTYTYPE_TYPE = build_PRIMALSPACEFEATURESPROPERTYTYPE_TYPE();
    
    private static ComplexType build_PRIMALSPACEFEATURESPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeaturesPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        PRIMALSPACEFEATURESTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeatures"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="PrimalSpaceFeaturesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="cellSpaceMember" type="gml:FeaturePropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="cellSpaceBoundaryMember" type="gml:FeaturePropertyType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AggregationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType PRIMALSPACEFEATURESTYPE_TYPE = build_PRIMALSPACEFEATURESTYPE_TYPE();
    
    private static ComplexType build_PRIMALSPACEFEATURESTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeaturesType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.FEATUREPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","cellSpaceMember"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.FEATUREPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","cellSpaceBoundaryMember"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.AGGREGATIONTYPE_TYPE,
                        new NameImpl("aggregationType"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="SpaceLayerClassTypeType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:restriction base="xs:string"&gt;
     *  			&lt;xs:enumeration value="TOPOGRAPHIC"/&gt;
     *  			&lt;xs:enumeration value="SENSOR"/&gt;
     *  			&lt;xs:enumeration value="LOGICAL"/&gt;
     *  			&lt;xs:enumeration value="TAGS"/&gt;
     *  			&lt;xs:enumeration value="LOGICAL"/&gt;
     *  			&lt;xs:enumeration value="UNKNOWN"/&gt;
     *  		&lt;/xs:restriction&gt;
     *  	&lt;/xs:simpleType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final AttributeType SPACELAYERCLASSTYPETYPE_TYPE = build_SPACELAYERCLASSTYPETYPE_TYPE();
     
    private static AttributeType build_SPACELAYERCLASSTYPETYPE_TYPE() {
        AttributeType builtType = new AbstractLazyAttributeTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerClassTypeType"),
                java.lang.Object.class, false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.STRING_TYPE;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="SpaceLayerMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="1"&gt;
     *  					&lt;xs:element ref="SpaceLayer"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType SPACELAYERMEMBERTYPE_TYPE = build_SPACELAYERMEMBERTYPE_TYPE();
    
    private static ComplexType build_SPACELAYERMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayer"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="SpaceLayerPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="SpaceLayer"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType SPACELAYERPROPERTYTYPE_TYPE = build_SPACELAYERPROPERTYTYPE_TYPE();
    
    private static ComplexType build_SPACELAYERPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayer"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="SpaceLayerType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="usage" type="gml:CodeType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="terminationDate" type="xs:dateTime"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="function" type="gml:CodeType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="creationDate" type="xs:dateTime"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="class" type="SpaceLayerClassTypeType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="nodes" type="NodesType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="edges" type="EdgesType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType SPACELAYERTYPE_TYPE = build_SPACELAYERTYPE_TYPE();
    
    private static ComplexType build_SPACELAYERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.CODETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","usage"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.DATETIME_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","terminationDate"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.CODETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","function"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.DATETIME_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","creationDate"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERCLASSTYPETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","class"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        NODESTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","nodes"),
                        1, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        EDGESTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","edges"),
                        0, 2147483647, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="SpaceLayersType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="spaceLayerMember" type="SpaceLayerMemberType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AggregationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType SPACELAYERSTYPE_TYPE = build_SPACELAYERSTYPE_TYPE();
    
    private static ComplexType build_SPACELAYERSTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","SpaceLayersType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        SPACELAYERMEMBERTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","spaceLayerMember"),
                        1, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.AGGREGATIONTYPE_TYPE,
                        new NameImpl("aggregationType"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="StateMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="0"&gt;
     *  					&lt;xs:element ref="State"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType STATEMEMBERTYPE_TYPE = build_STATEMEMBERTYPE_TYPE();
    
    private static ComplexType build_STATEMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StateMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","State"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="StatePropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="State"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType STATEPROPERTYTYPE_TYPE = build_STATEPROPERTYTYPE_TYPE();
    
    private static ComplexType build_STATEPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StatePropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATETYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","State"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="StateType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="CellSpacePropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="connects" type="TransitionPropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="geometry" type="gml:PointPropertyType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType STATETYPE_TYPE = build_STATETYPE_TYPE();
    
    private static ComplexType build_STATETYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StateType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","duality"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TRANSITIONPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","connects"),
                        0, 2147483647, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.POINTPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","geometry"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="TransitionMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
     *  				&lt;xs:sequence minOccurs="0"&gt;
     *  					&lt;xs:element ref="Transition"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType TRANSITIONMEMBERTYPE_TYPE = build_TRANSITIONMEMBERTYPE_TYPE();
    
    private static ComplexType build_TRANSITIONMEMBERTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","TransitionMemberType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATUREMEMBERTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TRANSITIONTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Transition"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="TransitionPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:sequence minOccurs="0"&gt;
     *  			&lt;xs:element ref="Transition"/&gt;
     *  		&lt;/xs:sequence&gt;
     *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType TRANSITIONPROPERTYTYPE_TYPE = build_TRANSITIONPROPERTYTYPE_TYPE();
    
    private static ComplexType build_TRANSITIONPROPERTYTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","TransitionPropertyType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        TRANSITIONTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Transition"),
                        1, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="TransitionType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:complexContent&gt;
     *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
     *  				&lt;xs:sequence&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="weight" type="xs:double"/&gt;
     *  					&lt;xs:element maxOccurs="2" minOccurs="2" name="connects" type="StatePropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="CellSpaceBoundaryPropertyType"/&gt;
     *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="geometry" type="gml:CurvePropertyType"/&gt;
     *  				&lt;/xs:sequence&gt;
     *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
     *  			&lt;/xs:extension&gt;
     *  		&lt;/xs:complexContent&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType TRANSITIONTYPE_TYPE = build_TRANSITIONTYPE_TYPE();
    
    private static ComplexType build_TRANSITIONTYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","TransitionType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return GMLSchema.ABSTRACTFEATURETYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.DOUBLE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","weight"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        STATEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","connects"),
                        2, 2, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        CELLSPACEBOUNDARYPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","duality"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.CURVEPROPERTYTYPE_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","geometry"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._ACTUATE_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","actuate"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","arcrole"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","href"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        GMLSchema.NILREASONTYPE_TYPE,
                        new NameImpl("nilReason"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/gml/3.2","remoteSchema"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","role"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XLINKSchema._SHOW_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","show"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","title"),
                        0, 1, true, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.w3.org/1999/xlink","type"),
                        0, 1, true, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="externalObjectReferenceType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:choice&gt;
     *  			&lt;xs:element minOccurs="0" name="name" type="xs:string"/&gt;
     *  			&lt;xs:element name="uri" type="xs:anyURI"/&gt;
     *  		&lt;/xs:choice&gt;
     *  	&lt;/xs:complexType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final ComplexType EXTERNALOBJECTREFERENCETYPE_TYPE = build_EXTERNALOBJECTREFERENCETYPE_TYPE();
    
    private static ComplexType build_EXTERNALOBJECTREFERENCETYPE_TYPE() {
        ComplexType builtType = new AbstractLazyComplexTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","externalObjectReferenceType"),
                false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYTYPE_TYPE;
            }
            @Override
            public Collection<PropertyDescriptor> buildDescriptors() {
                List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.STRING_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","name"),
                        0, 1, false, null));
                descriptors.add(
                    new AttributeDescriptorImpl(
                        XSSchema.ANYURI_TYPE,
                        new NameImpl("http://www.opengis.net/indoorgml/1.0/core","uri"),
                        1, 1, false, null));
                return descriptors;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="typeOfTopoExpressionCodeEnumerationType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:restriction base="xs:string"&gt;
     *  			&lt;xs:enumeration value="CONTAINS"/&gt;
     *  			&lt;xs:enumeration value="OVERLAPS"/&gt;
     *  			&lt;xs:enumeration value="EQUALS"/&gt;
     *  			&lt;xs:enumeration value="WITHIN"/&gt;
     *  			&lt;xs:enumeration value="CROSSES"/&gt;
     *  			&lt;xs:enumeration value="INTERSECTS"/&gt;
     *  		&lt;/xs:restriction&gt;
     *  	&lt;/xs:simpleType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final AttributeType TYPEOFTOPOEXPRESSIONCODEENUMERATIONTYPE_TYPE = build_TYPEOFTOPOEXPRESSIONCODEENUMERATIONTYPE_TYPE();
     
    private static AttributeType build_TYPEOFTOPOEXPRESSIONCODEENUMERATIONTYPE_TYPE() {
        AttributeType builtType = new AbstractLazyAttributeTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeEnumerationType"),
                java.lang.Object.class, false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.STRING_TYPE;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="typeOfTopoExpressionCodeOtherType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:restriction base="xs:string"&gt;
     *  			&lt;xs:pattern value="other. \w{2,}"/&gt;
     *  		&lt;/xs:restriction&gt;
     *  	&lt;/xs:simpleType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final AttributeType TYPEOFTOPOEXPRESSIONCODEOTHERTYPE_TYPE = build_TYPEOFTOPOEXPRESSIONCODEOTHERTYPE_TYPE();
     
    private static AttributeType build_TYPEOFTOPOEXPRESSIONCODEOTHERTYPE_TYPE() {
        AttributeType builtType = new AbstractLazyAttributeTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeOtherType"),
                java.lang.Object.class, false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.STRING_TYPE;
            }
        };
        return builtType;
    }

    /**
     * <p>
     *  <pre>
     *   <code>
     *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="typeOfTopoExpressionCodeType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
     *  		&lt;xs:union memberTypes="typeOfTopoExpressionCodeEnumerationType typeOfTopoExpressionCodeOtherType"/&gt;
     *  	&lt;/xs:simpleType&gt;
     *
     *    </code>
     *   </pre>
     * </p>
     *
     * @generated
     */
    public static final AttributeType TYPEOFTOPOEXPRESSIONCODETYPE_TYPE = build_TYPEOFTOPOEXPRESSIONCODETYPE_TYPE();
     
    private static AttributeType build_TYPEOFTOPOEXPRESSIONCODETYPE_TYPE() {
        AttributeType builtType = new AbstractLazyAttributeTypeImpl(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeType"),
                java.lang.Object.class, false, false, null, null) {
            @Override
            public AttributeType buildSuper() {
                return XSSchema.ANYSIMPLETYPE_TYPE;
            }
        };
        return builtType;
    }


    public INDOORCORESchema2() {
        super("http://www.opengis.net/indoorgml/1.0/core");
        put(CELLSPACEBOUNDARYMEMBERTYPE_TYPE);
        put(CELLSPACEBOUNDARYPROPERTYTYPE_TYPE);
        put(CELLSPACEBOUNDARYTYPE_TYPE);
        put(CELLSPACEMEMBERTYPE_TYPE);
        put(CELLSPACEPROPERTYTYPE_TYPE);
        put(CELLSPACETYPE_TYPE);
        put(EDGESTYPE_TYPE);
        put(EXTERNALREFERENCETYPE_TYPE);
        put(INDOORFEATURESTYPE_TYPE);
        put(INTEREDGESTYPE_TYPE);
        put(INTERLAYERCONNECTIONMEMBERTYPE_TYPE);
        put(INTERLAYERCONNECTIONPROPERTYTYPE_TYPE);
        put(INTERLAYERCONNECTIONTYPE_TYPE);
        put(MULTILAYEREDGRAPHPROPERTYTYPE_TYPE);
        put(MULTILAYEREDGRAPHTYPE_TYPE);
        put(NODESTYPE_TYPE);
        put(PRIMALSPACEFEATURESPROPERTYTYPE_TYPE);
        put(PRIMALSPACEFEATURESTYPE_TYPE);
        put(SPACELAYERCLASSTYPETYPE_TYPE);
        put(SPACELAYERMEMBERTYPE_TYPE);
        put(SPACELAYERPROPERTYTYPE_TYPE);
        put(SPACELAYERTYPE_TYPE);
        put(SPACELAYERSTYPE_TYPE);
        put(STATEMEMBERTYPE_TYPE);
        put(STATEPROPERTYTYPE_TYPE);
        put(STATETYPE_TYPE);
        put(TRANSITIONMEMBERTYPE_TYPE);
        put(TRANSITIONPROPERTYTYPE_TYPE);
        put(TRANSITIONTYPE_TYPE);
        put(EXTERNALOBJECTREFERENCETYPE_TYPE);
        put(TYPEOFTOPOEXPRESSIONCODEENUMERATIONTYPE_TYPE);
        put(TYPEOFTOPOEXPRESSIONCODEOTHERTYPE_TYPE);
        put(TYPEOFTOPOEXPRESSIONCODETYPE_TYPE);
    }

    /**
     * Complete the definition of a type and store it in the schema.
     * 
     * <p>
     * 
     * This method calls {@link AttributeType#getSuper()} (and {@link ComplexType#getDescriptors()}
     * where applicable) to ensure the construction of the type (a concrete
     * {@link AbstractLazyAttributeTypeImpl} or {@link AbstractLazyComplexTypeImpl} sublass) is
     * complete. This should be sufficient to avoid any nasty thread-safety surprises in code using
     * this schema.
     * 
     * @param type
     *            the type to complete and store
     */
    private void put(AttributeType type) {
        type.getSuper();
        if (type instanceof ComplexType) {
            ((ComplexType) type).getDescriptors();
        }
        put(type.getName(), type);
    }

    /**
     * Test that this class can be loaded.
     */
    public static void main(String[] args) {
        Schema schema = new INDOORCORESchema2();
        for (Entry<Name, AttributeType> entry : new TreeMap<Name, AttributeType>(schema).entrySet()) {
            System.out.println("Type: " + entry.getValue().getName());
            System.out.println("    Super type: " + entry.getValue().getSuper().getName());
            if (entry.getValue() instanceof ComplexType) {
                for (PropertyDescriptor descriptor : ((ComplexType) entry.getValue())
                        .getDescriptors()) {
                    System.out.println("    Property descriptor: " + descriptor.getName());
                    System.out.println("        Property type: " + descriptor.getType().getName());
                }
            }
        }
    }

}