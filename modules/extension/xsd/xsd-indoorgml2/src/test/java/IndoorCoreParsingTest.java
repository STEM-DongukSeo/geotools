import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.geotools.data.FeatureSource;
import org.geotools.data.complex.NewFeatureTypeRegistry;
import org.geotools.data.complex.config.EmfComplexFeatureReader;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.ComplexFeatureGraphGenerator;
import org.geotools.feature.complex.NewXmlComplexFeatureParser;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.sfcgal.util.Geometry3DOperationTest;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.graph.build.line.LineStringGraphGenerator;
import org.geotools.graph.path.DijkstraShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.DijkstraIterator;
import org.geotools.graph.traverse.standard.DijkstraIterator.EdgeWeighter;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.xml.resolver.SchemaResolver;
import org.junit.Test;
import org.opengis.feature.Feature;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Solid;

public class IndoorCoreParsingTest {
    
    @Test
    public void ServerTest() {
        try {
            
            ClassLoader classLoader = getClass().getClassLoader();
            URL schemaLocation = classLoader.getResource("org/geotools/indoorgml/core/indoorgmlcore.xsd");
            
            ComplexFeatureServer server = new ComplexFeatureServer();
            
            server.registerSchema(schemaLocation, new NameImpl("http://www.opengis.net/indoorgml/1.0/core", ":", "IndoorFeatures"));
            
            URL url = getClass().getResource("testIGML_LT.gml");
            server.getResource(url);
            
             server.printRegisteredSource();
            
            GeometryBuilder builder = null;
            Hints hints = GeoTools.getDefaultHints();
            hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
            hints.put(Hints.GEOMETRY_VALIDATE, false);
            builder = new GeometryBuilder(hints);
            
            Point point = builder.createPoint(-232212.7286485749, -71557.77357719778, 0.0);
            
            /*
            List<Solid> solids = Geometry3DOperationTest.getSolids(builder);
            for(Solid s : solids) {
                
                System.out.println(s);
                
                System.out.println("result" + s.contains(point));
            }*/
            
            Feature f = server.mapMatching(point);
            
            server.printRegisteredSchmea();
            
            server.destorySchmea();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
