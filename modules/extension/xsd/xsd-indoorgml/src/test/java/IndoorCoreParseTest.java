import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.geotools.indoorgml.core.INDOORCOREConfiguration;
import org.geotools.xml.Parser;
import org.junit.Test;
import org.opengis.feature.Feature;

public class IndoorCoreParseTest {

    @Test
    public void MapHashParsingTest() {
        
        //org.geotools.xml.Configuration configuration = new org.geotools.indoorgml.core.INDOORCOREConfiguration();
        
        org.geotools.xml.Configuration configuration = new org.geotools.gml2.GMLConfiguration();
        configuration.getProperties().add( Parser.Properties.IGNORE_SCHEMA_LOCATION );
        configuration.getProperties().add(Parser.Properties.PARSE_UNKNOWN_ELEMENTS);
        
        org.geotools.xml.Parser parser = new org.geotools.xml.Parser( configuration );
        
        //the xml instance document above
        try {
                URL url = getClass().getResource("indoor.gml");
                InputStream in = url.openStream();

                System.out.println(in.available());
                
                //parse
                Object fc = parser.parse( in );
                
                Map<String, List<Feature>> m = (Map<String, List<Feature>>) fc;
                for( Map.Entry<String, List<Feature>> e : m.entrySet() ){
                    System.out.println( String.format("키 : %s, 값 : %s", e.getKey(), e.getValue()) );
                }
                
                assertNotNull(fc);
/*                for( Map.Entry<String, List<Feature>> e : fc.entrySet() ){
                    System.out.println( String.format("키 : %s, 값 : %s", e.getKey(), e.getValue()) );
                }*/
                
                
                /*List<Feature> members = (List<Feature>) fc.get( "featureMember" );
                for ( Iterator<Feature> i = members.iterator(); i.hasNext(); ) {
                        SimpleFeature f = (SimpleFeature) i.next();
                        
                        String id = (String) f.getIdentifier().getID();
                        Point point = (Point) f.getDefaultGeometry();
                        String count = (String) f.getProperty("count").getValue();
                        String date = (String) f.getProperty("date").getValue();
                        
                        System.out.println(id + " , " + point + " , " + count + " , " + date);
                }*/
                /*fc.accepts( new AbstractFeatureVisitor(){
                      public void visit( Feature feature ) {
                          Iterator<Property> i = feature.getProperties().iterator();
                                  SimpleFeature f = (SimpleFeature) i.next();
        
                                  Point point = (Point) f.getDefaultGeometry();
                                  String name = (String) f.getAttribute( "name" );
                          }
                  }, new NullProgressListener() );*/
        }
        catch(Exception e) {
                e.printStackTrace();
        }
        
        
    }
    
    @Test
    public void INDOORCOREParsingTest() {
        org.geotools.xml.Configuration configuration = new INDOORCOREConfiguration();
        org.geotools.xml.Parser parser = new org.geotools.xml.Parser( configuration );
        
        //the xml instance document above
        try {
                URL url = getClass().getResource("indoor.gml");
                InputStream in = url.openStream();

                System.out.println(in.available());
                
                //parse
                Object fc = parser.parse( in );
                
                assertNotNull(fc);
        }
        catch(Exception e) {
                e.printStackTrace();
        }
    }

}
