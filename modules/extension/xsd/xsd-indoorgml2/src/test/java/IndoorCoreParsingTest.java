import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

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

public class IndoorCoreParsingTest {
    
    @Test
    public void ServerTest() {
        try {
            
            ClassLoader classLoader = getClass().getClassLoader();
            URL schemaLocation = classLoader.getResource("org/geotools/indoorgml/core/indoorgmlcore.xsd");
            
            ComplexFeatureServer server = new ComplexFeatureServer();
            
            server.registerSchema(schemaLocation, new NameImpl("http://www.opengis.net/indoorgml/1.0/core", ":", "IndoorFeatures"));
            
            server.printRegisteredSchmea();
            
            server.destorySchmea();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void INDOORCOREParsingTest() {
        
        try {
        	ISA_Sample sample = new ISA_Sample("GeoTools/3DIndoor");
        	
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
                    new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"));

            FeatureSource transitionfs = ftg.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Transition"));
            FeatureSource cellSpacefs = ftg.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"));
            FeatureSource statefs = ftg.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","State"));
            
            //sample.addFeatureCollection(cellSpacefs.getFeatures());
            //sample.addFeatureCollection(statefs.getFeatures());
            //sample.addFeatureCollection(transitionfs.getFeatures());
            
            FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );
            
            GeometryBuilder builder = null;
            Hints hints = GeoTools.getDefaultHints();
            hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
            hints.put(Hints.GEOMETRY_VALIDATE, false);
            builder = new GeometryBuilder(hints);
            
            Point point = builder.createPoint(445538.302498147, 5444904.79263906, -2.52);
            Filter filter = ff.intersects("Geometry3D", point);
            
            FeatureCollection tfs = transitionfs.getFeatures(filter);
            
            Filter cqlF = CQL.toFilter("name == 005");
            
            
            //create a linear graph generate
            LineStringGraphGenerator lineStringGen = new LineStringGraphGenerator();
            ComplexFeatureGraphGenerator featureGen = new ComplexFeatureGraphGenerator( lineStringGen );
            
            for(int i = 0 ; i < tfs.size(); i++) {
                Feature transition = (Feature) tfs.toArray()[i];
                featureGen.add(transition);
            }
            
            Graph graph = lineStringGen.getGraph();
            
            Collection<Node> destinations = graph.getNodes();
            Node start = (Node) destinations.iterator().next();
            
            EdgeWeighter weighter = new DijkstraIterator.EdgeWeighter() {
                public double getWeight(Edge e) {
                	/*
                   	Feature feature = (Feature) e.getObject();
                   	GeometryAttribute geometry = (GeometryAttribute) feature.getDefaultGeometryProperty();
                   	Geometry g = (Geometry) geometry.getValue();
                	*/
                	Geometry startPoint = (Geometry) e.getNodeA().getObject();
                	Geometry endPoint = (Geometry) e.getNodeB().getObject();
                   
                	return startPoint.distance(endPoint);
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
