import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;

import org.geotools.data.FeatureSource;
import org.geotools.data.complex.NewFeatureTypeRegistry;
import org.geotools.data.complex.config.EmfComplexFeatureReader;
import org.geotools.data.complex.config.FeatureTypeRegistry;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.ComplexFeatureGraphGenerator;
import org.geotools.feature.complex.NewXmlComplexFeatureParser;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.graph.build.line.LineStringGraphGenerator;
import org.geotools.graph.path.DijkstraShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.DijkstraIterator;
import org.geotools.graph.traverse.standard.DijkstraIterator.EdgeWeighter;
import org.geotools.xml.resolver.SchemaResolver;
import org.junit.Test;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;

import com.vividsolutions.jts.geom.Geometry;


public class IndoorCoreParsingTest {

    @Test
    public void INDOORCOREParsingTest() {
        
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL schemaLocation = classLoader.getResource("org/geotools/indoorgml/core/indoorgmlcore.xsd");
            
            // Creating Feature Type
            SchemaResolver appSchemaResolver = new SchemaResolver();
            EmfComplexFeatureReader reader = EmfComplexFeatureReader.newInstance();
            reader.setResolver(appSchemaResolver);
            NewFeatureTypeRegistry typeRegistry = new NewFeatureTypeRegistry(new ComplexFeatureTypeFactoryImpl(),
                        new GmlFeatureTypeRegistryConfiguration(null));
            typeRegistry.addSchemas(reader.parse(schemaLocation));
            
            AttributeDescriptor descriptor = typeRegistry
                            .getDescriptor(new NameImpl("http://www.opengis.net/indoorgml/1.0/core",
                                            ":", "IndoorFeatures"), null);
            FeatureType featureType = (FeatureType) descriptor.getType();
            
            typeRegistry.disposeSchemaIndexes();
            
            // Creating Feature
            URL url = getClass().getResource("indoor.gml");
            NewXmlComplexFeatureParser featureParser = new NewXmlComplexFeatureParser(
                            url.openStream(),
                            featureType, new QName("http://www.opengis.net/indoorgml/1.0/core",
                                    "IndoorFeatures"));
            
            // Act
            Feature feature = featureParser.parse();
            
            FeatureTableGenerator ftg = new FeatureTableGenerator(feature);
            FeatureSource fs = ftg.getFeatureSource( 
                    new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Transition"));
            
            FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

            Set<FeatureId> fids = new HashSet<FeatureId>();
            fids.add( ff.featureId("T10") );
            fids.add( ff.featureId("T19") );
            Filter filter = ff.id( fids );
            
            FeatureCollection tfs = fs.getFeatures(filter);
            
            System.out.println();
            
            
            
            
           //Filter filter = CQL.toFilter("name >= 5");
            
            
            //create a linear graph generate
            LineStringGraphGenerator lineStringGen = new LineStringGraphGenerator();
            ComplexFeatureGraphGenerator featureGen = new ComplexFeatureGraphGenerator( lineStringGen );
            
            for(int i = 0 ; i < tfs.size(); i++) {
                //Feature transition = (Feature) tfs.get(i);
                //featureGen.add(transition);
            }
            
            Graph graph = lineStringGen.getGraph();
            
            Collection<Node> destinations = graph.getNodes();
            Node start = (Node) destinations.iterator().next();
            
            EdgeWeighter weighter = new DijkstraIterator.EdgeWeighter() {
                public double getWeight(Edge e) {
                   Feature feature = (Feature) e.getObject();
                   GeometryAttribute geometry = (GeometryAttribute) feature.getDefaultGeometryProperty();
                   Geometry g = (Geometry) geometry.getValue();
                   return g.getLength();
                }
            };
            
            DijkstraShortestPathFinder pf = new DijkstraShortestPathFinder( graph, start, weighter );
            pf.calculate();
            
            for ( Iterator d = destinations.iterator(); d.hasNext(); ) {
                Node destination = (Node) d.next();
                Path path = pf.getPath( destination );
    
                System.out.println(path);
                //do something with the path
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
