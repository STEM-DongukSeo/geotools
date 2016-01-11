import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.geotools.data.FeatureSource;
import org.geotools.data.complex.NewFeatureTypeRegistry;
import org.geotools.data.complex.config.EmfComplexFeatureReader;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.NewComplexFeatureSource;
import org.geotools.feature.complex.NewFeatureCollection;
import org.geotools.feature.complex.NewXmlComplexFeatureParser;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.xml.SchemaIndex;
import org.geotools.xml.resolver.SchemaResolver;
import org.opengis.feature.ComplexAttribute;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;

/**
 * 
 */

/**
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public class ComplexFeatureServer {

    private Map<Name, FeatureType> types;

    private NewFeatureTypeRegistry typeRegistry;

    private AttributeDescriptor rootDescriptor;
    private FeatureType rootType;
    
    private FeatureTableGenerator table;
    
    public ComplexFeatureServer() {
        
    }

    public void registerSchema(URL url, Name root) throws IOException {
        SchemaResolver appSchemaResolver = new SchemaResolver();
        EmfComplexFeatureReader reader = EmfComplexFeatureReader.newInstance();
        reader.setResolver(appSchemaResolver);
        NewFeatureTypeRegistry typeRegistry = new NewFeatureTypeRegistry(new ComplexFeatureTypeFactoryImpl(),
                new GmlFeatureTypeRegistryConfiguration(null));
        
        SchemaIndex schemaIdx = reader.parse(url);
        typeRegistry.addSchemas(schemaIdx);
        
        rootDescriptor = typeRegistry.getDescriptor(root, null);
        rootType = (FeatureType) rootDescriptor.getType();
    }
    
    public void getResource(URL url) throws IOException {
        Name rootName = rootDescriptor.getName();
        
        NewXmlComplexFeatureParser featureParser = new NewXmlComplexFeatureParser(
                url.openStream(),
                rootType, new QName(rootName.getNamespaceURI(),
                        rootName.getLocalPart()));
        
        Feature feature = featureParser.parse();
        table = new FeatureTableGenerator(feature);
        types = table.getFeatureTypeMap();
        
        printRegisteredSchmea();
    }
    
    public Map<Name, FeatureSource> getFeatureSources() {
        return table.getFeatureSources();
    }
    
    public Map<Name, FeatureType> getFeatureTypes() {
        if(types == null) {
            return Collections.emptyMap();
        }
        return types;
    }
    
    public void printRegisteredSchmea() {
        if(types != null) {
            for( Map.Entry<Name, FeatureType> elem : types.entrySet()) {
                System.out.println( String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
            }
        }
    }
    
    public FeatureSource getFeatureSource(Name sourceName) {
        if(table != null) {
            return table.getFeatureSource(sourceName);
        }
        return null;
    }
    
    public void destorySchmea() {
        if(typeRegistry != null) {
            typeRegistry.disposeSchemaIndexes();
        }
    }
    
    public FeatureCollection idFilter(FeatureSource source, String ids) throws IOException {
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );
        Set<FeatureId> fids = new HashSet<FeatureId>();
        String[] idArr = ids.split("|");
        for(String i : idArr) {
            fids.add( ff.featureId(i ) );
        }
        Filter filter = ff.id( fids );
        return source.getFeatures(filter);
    }
    
    public FeatureCollection spatialFilter(FeatureSource source, String queryType, String propName, Geometry geometry) throws IOException {
        NewFeatureCollection fc = new NewFeatureCollection();
        
        FeatureCollection c = source.getFeatures();
        FeatureIterator it = c.features();
        
        while(it.hasNext()) {
            Feature f = it.next();
            Geometry g = getGeometryfromPropertyName(f, propName);
            if(spatialEvaluate(queryType, g, geometry)) {
                fc.add(f);
            }
        }
        it.close();
        
        return fc;
    }
    
    public Geometry getGeometryfromPropertyName(Feature feature, String propName) {
        Collection<? extends Property> values = feature.getValue();
        for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {
            Property p = it.next();
            PropertyType type = p.getType();
            if(type instanceof GeometryType) {
                if(p.getName().getLocalPart().equalsIgnoreCase(propName)) {
                    return (Geometry) p.getValue();
                }
            }
        }
        return null;
    }
    
    public boolean spatialEvaluate(String queryType, Geometry propGeom, Geometry geometry) {
        
        if("Intersects".equalsIgnoreCase(queryType)) {
           return geometry.intersects(propGeom);
        } else if("Contains".equalsIgnoreCase(queryType)) {
            return geometry.contains(propGeom);
        }
        
        return false;
    }
    
    public Feature mapMatching(Point point) throws IOException {
        
        FeatureSource cellSpacefs = table.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"));
        FeatureCollection result = spatialFilter(cellSpacefs, "Contains", "Geometry3D", point);
        
        if(result.size() > 1) {
            throw new IllegalArgumentException("Invalid FeatureSource. Data is invalid. Mapmaching result should be one.");
        }
        
        FeatureIterator it = result.features();
        Feature cellspace = null;
        while(it.hasNext()) {
            cellspace = it.next();
        }
        it.close();
        
        if(cellspace == null) {
            return null;
        }
        
        Feature state = (Feature) getPropertyValue(cellspace, "connects");
        return state;
    }
    
    public Object getPropertyValue(Feature feature, String localPart) {
        Collection<? extends Property> values = feature.getValue();
        for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {

            Property p = it.next();
            PropertyType type = p.getType();

            if(type.getName().getLocalPart().equalsIgnoreCase(localPart)) {
                return p.getValue();
            }
        }
        return null;
    }
    
}
