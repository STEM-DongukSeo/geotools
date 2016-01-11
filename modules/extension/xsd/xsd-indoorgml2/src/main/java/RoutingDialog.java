

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.ComplexFeatureGraphGenerator;
import org.geotools.graph.build.line.LineStringGraphGenerator;
import org.geotools.graph.path.DijkstraShortestPathFinder;
import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.standard.DijkstraIterator;
import org.geotools.graph.traverse.standard.DijkstraIterator.EdgeWeighter;
import org.opengis.feature.Feature;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.primitive.Point;

public class RoutingDialog {
	public JDialog jDialog;
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JButton submitButton = new JButton("Submit");
	private JComboBox<String> startStateComboBox = new JComboBox<>();
	private JComboBox<String> endStateComboBox = new JComboBox<>();
	private JLabel startStateLabel = new JLabel("Source");
	private JLabel endStateLabel = new JLabel("Desination");
	private JTextField routingResult = new JTextField();
	
	private ComplexFeatureServer server = null;
	
	public RoutingDialog(JFrame jFrame, ComplexFeatureServer server) throws IOException{
		jDialog  = new JDialog(jFrame, "MapMatching used Graph Module");
		this.server = server;
		
		routingResult.setColumns(30);
		
		FeatureSource fs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StateType"));
		FeatureIterator fi = fs.getFeatures().features();
		while(fi.hasNext()){
			Feature state = fi.next();
			startStateComboBox.addItem(state.getIdentifier().getID());
			endStateComboBox.addItem(state.getIdentifier().getID());
		}
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					String resultpath = calRoute((String)startStateComboBox.getSelectedItem(), (String)endStateComboBox.getSelectedItem());
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
		
		jDialog.setBounds(100, 100, 400, 200);
		
		//jDialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
	}
	
	
	public String calRoute(String startStateID, String endStateID) throws IOException{
		FeatureSource statefs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","State"));
		FeatureSource transitionfs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","Transition"));
        FeatureCollection tfs = transitionfs.getFeatures();
        
        //create a linear graph generate
        LineStringGraphGenerator lineStringGen = new LineStringGraphGenerator();
        ComplexFeatureGraphGenerator featureGen = new ComplexFeatureGraphGenerator( lineStringGen );
        
        for(int i = 0 ; i < tfs.size(); i++) {
            Feature transition = (Feature) tfs.toArray()[i];
            featureGen.add(transition);
        }
        
        FeatureCollection fc = server.idFilter(statefs, startStateID);
		Feature startState = fc.features().next();
		Point startStateGeometty = (Point) startState.getDefaultGeometryProperty();
		fc = server.idFilter(statefs, endStateID);
		Feature endState = fc.features().next();
		Point endStateGeometty = (Point) startState.getDefaultGeometryProperty();
		
        
        Graph graph = lineStringGen.getGraph();
        
        Node sourceNode = null;
        Node destinationNode = null;
        
        Collection<Node> nodelist = graph.getNodes();
        
        while(nodelist.iterator().hasNext()){
        	Node node = nodelist.iterator().next();
        	Point p = (Point) node.getObject();
        	if(p.equals(startStateGeometty))
        		sourceNode = node;
        	if(p.equals(endStateGeometty))
        		destinationNode = node;
        }
        
        EdgeWeighter weighter = new DijkstraIterator.EdgeWeighter() {
            public double getWeight(Edge e) {
            	Geometry startPoint = (Geometry) e.getNodeA().getObject();
            	Geometry endPoint = (Geometry) e.getNodeB().getObject();
               
            	return startPoint.distance(endPoint);
            }
        };
        
        DijkstraShortestPathFinder pf = new DijkstraShortestPathFinder( graph, sourceNode, weighter );
        pf.calculate();
        
        Path path = pf.getPath( destinationNode );
        
        String resultPath = "[";
        System.out.print("[");
        for(int i = 0; i < path.size(); i++){
        	Node node = (Node) path.get(i);
        	Point p = (Point) node.getObject();
        	Feature f = server.mapMatchingState(p);
        	resultPath += f.getIdentifier().getID() + ",";
        	System.out.print(f.getIdentifier().getID());
        	System.out.print(" , ");
        }
        resultPath += "]";
        System.out.print("]");
        
        return resultPath;
	}
}
