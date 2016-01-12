
import static org.custommonkey.xmlunit.XMLAssert.assertXpathEvaluatesTo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.custommonkey.xmlunit.XMLUnit;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.ComplexFeatureGraphGenerator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.gml3.bindings.GML3MockData;
import org.geotools.gml3.bindings.ROUTE;
import org.geotools.gml3.bindings.TEST;
import org.geotools.gml3.bindings.TestConfiguration;
import org.geotools.graph.build.line.LineStringGraphGenerator;
import org.geotools.graph.path.DijkstraShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.DijkstraIterator;
import org.geotools.graph.traverse.standard.DijkstraIterator.EdgeWeighter;
import org.geotools.xml.Configuration;
import org.geotools.xml.Encoder;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;
import org.picocontainer.MutablePicoContainer;
import org.w3c.dom.Document;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

public class RoutingDialog {
    public JDialog jDialog;

    private JPanel northPanel = new JPanel(new GridLayout(1, 4, 3, 3));

    private JPanel centerPanel = new JPanel();

    private JButton submitButton = new JButton("Submit");

    private JComboBox<String> startStateComboBox = new JComboBox<>();

    private JComboBox<String> endStateComboBox = new JComboBox<>();

    private JLabel startStateLabel = new JLabel("Source", SwingConstants.CENTER);

    private JLabel endStateLabel = new JLabel("Desination", SwingConstants.CENTER);

    private JTextField routingResult = new JTextField();

    private ComplexFeatureServer server = null;

    public RoutingDialog(JFrame jFrame, ComplexFeatureServer server) throws IOException {
        jDialog = new JDialog(jFrame, "Routing used Graph Module");
        this.server = server;

        routingResult.setColumns(25);

        FeatureSource fs = server.getFeatureSource(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core", "StateType"));
        FeatureIterator fi = fs.getFeatures().features();
        while (fi.hasNext()) {
            Feature state = fi.next();
            startStateComboBox.addItem(state.getIdentifier().getID());
            endStateComboBox.addItem(state.getIdentifier().getID());
        }

        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                try {
                    String resultpath = calRoute((String) startStateComboBox.getSelectedItem(),
                            (String) endStateComboBox.getSelectedItem());
                    routingResult.setText(resultpath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        northPanel.add(startStateLabel);
        northPanel.add(startStateComboBox);
        northPanel.add(endStateLabel);
        northPanel.add(endStateComboBox);
        centerPanel.add(routingResult);
        centerPanel.add(submitButton);

        jDialog.add(northPanel, BorderLayout.NORTH);
        jDialog.add(centerPanel, BorderLayout.CENTER);

        jDialog.setBounds(100, 100, 400, 110);

        // jDialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
    }

    public String calRoute(String startStateID, String endStateID) throws IOException {
        FeatureSource statefs = server.getFeatureSource(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core", "State"));
        FeatureSource transitionfs = server.getFeatureSource(
                new NameImpl("http://www.opengis.net/indoorgml/1.0/core", "Transition"));
        FeatureCollection tfs = transitionfs.getFeatures();

        // create a linear graph generate
        LineStringGraphGenerator lineStringGen = new LineStringGraphGenerator();
        ComplexFeatureGraphGenerator featureGen = new ComplexFeatureGraphGenerator(lineStringGen);

        for (int i = 0; i < tfs.size(); i++) {
            Feature transition = (Feature) tfs.toArray()[i];
            featureGen.add(transition);
        }

        FeatureCollection fc = server.idFilter(statefs, startStateID);
        Feature startState = (Feature) fc.features().next().getValue().iterator().next();
        Point startStateGeometty = (Point) startState.getProperties("geometry").iterator().next()
                .getValue();
        fc = server.idFilter(statefs, endStateID);
        Feature endState = (Feature) fc.features().next().getValue().iterator().next();
        Point endStateGeometty = (Point) endState.getProperties("geometry").iterator().next()
                .getValue();

        Graph graph = lineStringGen.getGraph();

        Node sourceNode = null;
        Node destinationNode = null;

        Collection<Node> nodelist = graph.getNodes();
        Iterator<Node> ni = nodelist.iterator();
        while (ni.hasNext()) {
            Node node = ni.next();
            Point p = (Point) node.getObject();
            if (p.equals(startStateGeometty))
                destinationNode = node;
            if (p.equals(endStateGeometty))
                sourceNode = node;
        }

        EdgeWeighter weighter = new DijkstraIterator.EdgeWeighter() {
            public double getWeight(Edge e) {
                Geometry startPoint = (Geometry) e.getNodeA().getObject();
                Geometry endPoint = (Geometry) e.getNodeB().getObject();

                return startPoint.distance(endPoint);
            }
        };

        DijkstraShortestPathFinder pf = new DijkstraShortestPathFinder(graph, sourceNode, weighter);
        pf.calculate();

        Path path = pf.getPath(destinationNode);

        String resultPath = "[";
        
        List<Point> pointList = new ArrayList<Point>();

        for (int i = 0; i < path.size() - 1; i++) {
            Node nodeA = (Node) path.get(i);
            Node nodeB = (Node) path.get(i + 1);
            Point pA = (Point) nodeA.getObject();
            Point pB = (Point) nodeB.getObject();
            Feature f = server.mapMatchingTransition(pA, pB);
            resultPath += f.getIdentifier().getID();
            if (i != path.size() - 2)
                resultPath += ",";
            
            pointList.add(pA);
        }
        resultPath += "]";
        
        LineString route = toLineString(pointList);
        exportRoute(route, startStateID, endStateID);

        return resultPath;
    }
    
    private LineString toLineString(List<Point> pointList) {
        GeometryFactory gf = new GeometryFactory();
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for(Point p: pointList) {
            double x = p.getDirectPosition().getOrdinate(0);
            double y = p.getDirectPosition().getOrdinate(1);
            double z = p.getDirectPosition().getOrdinate(2);
            
            coordinates.add(new Coordinate(x, y, z));
        }        
        return gf.createLineString(coordinates.toArray(new Coordinate[]{}));
    }
    
    private void exportRoute(LineString lineString, String startNode, String endNode) throws IOException {
        SimpleFeatureType type = buildPathFeatureType();

        SimpleFeatureBuilder builder = new SimpleFeatureBuilder(type);
        builder.add("shortest path");
        builder.add("It's the result of the shortest path in indoor space");
        builder.add(lineString);
        builder.add(lineString.getLength());
        builder.add(startNode);
        builder.add(endNode);

        SimpleFeature feature = builder.buildFeature("fid.1");
        
        Configuration configuration = new Configuration(ROUTE.getInstance()) {       
            {
                addDependency(new GMLConfiguration());
            }
        };

        Encoder encoder = new Encoder(configuration);
        encoder.setIndentSize(2);
        String xml = encoder.encodeAsString(feature, ROUTE.RouteFeature);

        System.out.println(xml);
    }

    private SimpleFeatureType buildPathFeatureType() {
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.setName(ROUTE.RouteFeature.getLocalPart());
        typeBuilder.setNamespaceURI(ROUTE.RouteFeature.getNamespaceURI());

        typeBuilder.add("name", String.class);
        typeBuilder.add("description", String.class);
        typeBuilder.add("geom", Point.class);
        typeBuilder.nillable(true);
        typeBuilder.add("count", Integer.class);
        typeBuilder.nillable(true);
        typeBuilder.add("startRouteNode", String.class);
        typeBuilder.add("endRouteNode", String.class);

        SimpleFeatureType type = typeBuilder.buildFeatureType();
        
        return type;
    }
}
