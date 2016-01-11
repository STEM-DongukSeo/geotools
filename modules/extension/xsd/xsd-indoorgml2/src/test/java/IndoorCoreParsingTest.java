import java.net.URL;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.NameImpl;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.junit.Test;
import org.opengis.feature.Feature;
import org.opengis.geometry.primitive.Point;

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
            
            GeometryBuilder builder = null;
            Hints hints = GeoTools.getDefaultHints();
            hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
            hints.put(Hints.GEOMETRY_VALIDATE, false);
            builder = new GeometryBuilder(hints);
            
            Point point = builder.createPoint(-232212.7286485749, -71557.77357719778, 0.0);
            
            
            /*Point p = builder.createPoint(1, 1, 0.0);
            List<Solid> solids = Geometry3DOperationTest.getSolids(builder);
            for(Solid s : solids) {
                
                System.out.println(s);
                
                System.out.println("result" + s.contains(p));
            }*/
            Feature f = server.mapMatching(point);
            
            
            server.printRegisteredSchmea();
            
            server.destorySchmea();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
