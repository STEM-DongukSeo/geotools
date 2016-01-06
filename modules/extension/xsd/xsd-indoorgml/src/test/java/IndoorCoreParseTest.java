import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.geotools.indoorgml.core.INDOORCOREConfiguration;
import org.geotools.xml.Parser;
import org.geotools.xml.impl.NodeImpl;
import org.junit.Test;
import org.opengis.feature.Association;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;


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
                    System.out.println( String.format("키 : %s", e.getKey()) );
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

                //parse
                Object fc = parser.parse( in );
                
                //InodoorFeatures
                Feature ifs = (Feature) fc;
                assertNotNull(ifs);
                System.out.println( 
                        "Name : " + ifs.getName() + "\n" +
                        "FeatureId : " + ifs.getIdentifier() 
                );
                
                Collection<? extends Property> ifsValues = ifs.getValue();
                Iterator<? extends Property> ifsIter = ifsValues.iterator();

                Association psfs = null;
                Feature mls = null;
                while(ifsIter.hasNext()) {
                    Property p = ifsIter.next();
                    System.out.println("Attr : " + p.getName());
                    if("primalSpaceFeatures".equals(p.getName().getLocalPart())) {
                        psfs = (Association) p;
                    } else if("MultiLayeredGraph".equals(p.getName().getLocalPart())) {
                        mls = (Feature) p;
                    } else {
                        System.out.println("Value : " + p.getValue());
                    }
                }
                assertNotNull(psfs);
                
                //PrimalSpaceFeatureType
                Feature psf = (Feature) psfs.getValue();
                Collection<? extends Property> psfValues = psf.getValue();
                Iterator<? extends Property> psfIter = psfValues.iterator();
                while(psfIter.hasNext()) {
                    Property p = psfIter.next();
                    
                    
                    
                    System.out.println("Attr : " + p.getName());
                    System.out.println("Value : " + p.getValue());
                }
                
                System.out.println();
                
                
                //MultiLayeredGraph
                System.out.println( 
                        "Name : " + mls.getName() + "\n" +
                        "FeatureId : " + mls.getIdentifier() 
                );
                Collection<? extends Property> mlsValues = mls.getValue();
                Iterator<? extends Property> mlsIter = mlsValues.iterator();
                
                Feature spaceLayers = null;
                while(mlsIter.hasNext()) {
                    Property p = mlsIter.next();
                    System.out.println("Attr : " + p.getName());
                    System.out.println("Value : " + p.getValue());
                    
                    if("spaceLayers".equals(p.getName().getLocalPart())) {
                        spaceLayers = (Feature) p.getValue();
                    }
                    
                }
                System.out.println();
                
                //SpaceLayersType
                System.out.println( 
                        "Name : " + spaceLayers.getName() + "\n" +
                        "FeatureId : " + spaceLayers.getIdentifier() 
                );
                Collection<? extends Property> spaceLayersValues = spaceLayers.getValue();
                Iterator<? extends Property> spaceLayersIter = spaceLayersValues.iterator();
                
                List<Feature> sl = null;
                while(spaceLayersIter.hasNext()) {
                    Property p = spaceLayersIter.next();
                    System.out.println("Attr : " + p.getName());
                    System.out.println("Value : " + p.getValue());
                    
                    if("spaceLayerMember".equals(p.getName().getLocalPart())) {
                        sl = (List<Feature>) p.getValue();
                    }
                    
                }
                System.out.println();
                
                //SpaceLayerType List
                for(int i = 0; i < sl.size(); i++) {
                    Feature f = sl.get(i);
                    System.out.println("SpaceLayer[" + i + "]");
                    System.out.println( 
                            "Name : " + f.getName() + "\n" +
                            "FeatureId : " + f.getIdentifier() 
                    );
                    Collection<? extends Property> spaceLayerValues = f.getValue();
                    Iterator<? extends Property> spaceLayerIter = spaceLayerValues.iterator();

                    while(spaceLayerIter.hasNext()) {
                        Property p = spaceLayerIter.next();
                        System.out.println("Attr : " + p.getName());
                        System.out.println("Value : " + p.getValue());
                    }
                    System.out.println();
                    
                }
                
                
                //primalSpaceFeatures
/*                System.out.println( 
                        "Name : " + psf.getName() + "\n" +
                        "FeatureId : " + psf.getIdentifier() 
                );
                Collection<? extends Property> psfValues = psf.getValue();
                Iterator<? extends Property> psfIter = psfValues.iterator();
                while(psfIter.hasNext()) {
                    Property p = psfIter.next();
                    System.out.println("Attr : " + p.getName());
                    System.out.println("Value : " + p.getValue());
                }
                System.out.println();*/
                
                
               
                
                
        }
        catch(Exception e) {
                e.printStackTrace();
        }
    }

}
