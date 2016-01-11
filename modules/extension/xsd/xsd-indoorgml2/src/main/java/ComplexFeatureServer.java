import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
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
import org.geotools.factory.Hints;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.NewFeatureCollection;
import org.geotools.feature.complex.NewXmlComplexFeatureParser;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.io.wkt.ParseException;
import org.geotools.geometry.iso.io.wkt.WKTReader;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.xml.SchemaIndex;
import org.geotools.xml.resolver.SchemaResolver;
import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.PositionFactory;
import org.opengis.geometry.primitive.Point;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

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
    private GeometryBuilder builder = null;
    
    public ComplexFeatureServer() {
        
        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        builder = new GeometryBuilder(hints);
        
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
    
    public void getResource(URL url) throws IOException, NullPointerException {
        Name rootName = rootDescriptor.getName();
        
        NewXmlComplexFeatureParser featureParser = new NewXmlComplexFeatureParser(
                url.openStream(),
                rootType, new QName(rootName.getNamespaceURI(),
                        rootName.getLocalPart()));
        
        Feature feature = featureParser.parse();
        
        if(feature == null) {
            throw new NullPointerException("feature parsing failed");
        }
        
        table = new FeatureTableGenerator(feature);
        types = table.getFeatureTypeMap();
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
            System.out.println();
        }
    }
    
    public void printRegisteredSource() {
        if(table != null) {
            Map<Name, FeatureSource> fs = table.getFeatureSources();
            for( Map.Entry<Name, FeatureSource> elem : fs.entrySet()) {
                System.out.println( String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
            }
            System.out.println();
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
        String[] idArr = ids.split("\\|");
        for(String i : idArr) {
            fids.add( ff.featureId(i) );
        }
        Filter filter = ff.id( fids );
        return source.getFeatures(filter);
    }
    
    public FeatureCollection spatialFilter(FeatureSource source, String queryType, String propName, Geometry geometry) throws IOException {
        NewFeatureCollection fc = new NewFeatureCollection();
        
        FeatureCollection c = source.getFeatures();
        FeatureIterator it = c.features();
        
      
        int error = 0;
        int result = 0;
            while(it.hasNext()) {
                Feature f = it.next();
                try { 
                    GeometryAttribute ga = getGeometryfromPropertyName(f, propName);
                    Geometry g = (Geometry) ga.getValue();
                    
                    
                    if(f.getIdentifier().getID().equalsIgnoreCase("C98")) {
                        System.out.println(g);
                    }
                    
                    
                    if(spatialEvaluate(queryType, g, geometry)) {
                        fc.add(f);
                        result++;
                    }
  
                } catch(Exception e) {
                    System.out.println(f.getIdentifier().getID());
                    error++;
                }
                
            }
            it.close();
        
            System.out.println("CellSpace 갯수 : " + c.size());
            System.out.println("Error 갯수 : " + error);
            System.out.println("Result 갯수 : " + result);
            
            
        return fc;
    }
    
    public GeometryAttribute getGeometryfromPropertyName(Feature feature, String propName) {
        
        GeometryAttribute geom = null;
        
        Collection<? extends Property> values = feature.getValue();
        for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {
            Property p = (Property) it.next();
            
            if(GeometryAttribute.class.isAssignableFrom(p.getClass())) {
                if(p.getDescriptor().getName().getLocalPart().equalsIgnoreCase(propName)) {
                    geom = (GeometryAttribute) p;
                    break;
                }
            }
            else if(p.getValue() != null) {
            
                Iterator<? extends Attribute> ia = (Iterator<? extends Attribute>) ((Collection<? extends Property>) p.getValue()).iterator();
                while(ia.hasNext()){
                    Attribute a = ia.next();
                    if(GeometryAttribute.class.isAssignableFrom(a.getClass())) {
                        if(a.getDescriptor().getName().getLocalPart().equalsIgnoreCase(propName)) {
                            geom = (GeometryAttribute) a;
                            break;
                        }
                    }
                }
            }
        }
        
        return geom;
    }
    
    public Geometry geometryFromWKT(String wkt) throws ParseException {
        CoordinateReferenceSystem crs = builder.getCoordinateReferenceSystem();
        PositionFactory pf = builder.getPositionFactory();
        WKTReader wktReader = new WKTReader( crs, pf );
        return wktReader.read(wkt);
    }
    
    public boolean spatialEvaluate(String queryType, Geometry propGeom, Geometry geometry) {
        
        if("Intersects".equalsIgnoreCase(queryType)) {
           return propGeom.intersects(geometry);
        } else if("Contains".equalsIgnoreCase(queryType)) {
            return propGeom.contains(geometry);
        } else if("Equals".equalsIgnoreCase(queryType)) {
            return propGeom.equals(geometry);
        } else {
            throw new UnsupportedOperationException(queryType + " query is not supported");
        }
    }
    
    public Feature mapMatching(Point point) throws IOException {
        FeatureSource cellSpacefs = table.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpaceType"));
        FeatureCollection result = spatialFilter(cellSpacefs, "Contains", "Geometry3D", point);
        
        /*if(result.size() > 1) {
            throw new IllegalArgumentException("Invalid FeatureSource. Data is invalid. Mapmaching result should be one.");
        }*/
        
        
        FeatureIterator it = result.features();
        Feature cellspace = null;
        while(it.hasNext()) {
            cellspace = it.next();
            break;
        }
        it.close();
        
        
        return cellspace;
    }
    
    public Feature mapMatchingState(Point point) throws IOException {
        FeatureSource cellSpacefs = table.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StateType"));
        FeatureCollection result = spatialFilter(cellSpacefs, "Equals", "geometry", point);
        
        /*if(result.size() > 1) {
            throw new IllegalArgumentException("Invalid FeatureSource. Data is invalid. Mapmaching result should be one.");
        }*/
        
        FeatureIterator it = result.features();
        Feature state = null;
        while(it.hasNext()) {
            state = it.next();
            break;
        }
        it.close();
        
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
