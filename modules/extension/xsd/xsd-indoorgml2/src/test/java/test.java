import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Point;


public class test {

	@Test
	public void test() {
		
		
		//create the parser with the gml 3.0 configuration
		org.geotools.xml.Configuration configuration = new org.geotools.gml3.GMLConfiguration();
		org.geotools.xml.Parser parser = new org.geotools.xml.Parser( configuration );

		//the xml instance document above
		try {
			URL url = getClass().getResource("testgml.xml");
			InputStream in = url.openStream();

			System.out.println(in.available());
			
			//parse
			Map<String, List<Feature>> fc = (Map<String, List<Feature>>) parser.parse( in );
			List<Feature> members = (List<Feature>) fc.get( "featureMember" );
			for ( Iterator<Feature> i = members.iterator(); i.hasNext(); ) {
				SimpleFeature f = (SimpleFeature) i.next();
				
				String id = (String) f.getIdentifier().getID();
				Point point = (Point) f.getDefaultGeometry();
				String count = (String) f.getProperty("count").getValue();
				String date = (String) f.getProperty("date").getValue();
				
				System.out.println(id + " , " + point + " , " + count + " , " + date);
			}
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
}
