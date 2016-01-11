import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.geotools.geometry.iso.io.wkt.ParseException;
import org.opengis.feature.Feature;
import org.opengis.geometry.primitive.Point;

public class MapMatchingDialog implements ActionListener{
	public JDialog jDialog;
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new GridLayout(1, 5, 3, 3));
	private JButton submitButton = new JButton("Submit");
	private JComboBox<String> stateComboBox = new JComboBox<>();
	private JLabel queryPointLabel = new JLabel("Query Point (WKT)", SwingConstants.CENTER);
	private JLabel guidePointLabel = new JLabel("Guide Point (State)", SwingConstants.CENTER);
	private JLabel resultLabel = new JLabel("Map Matching Result :", SwingConstants.CENTER);
	private JTextField mapMatchingResult = new JTextField();
	private JTextField queryPointWKT = new JTextField();
	
	private ComplexFeatureServer server = null;
	
	public MapMatchingDialog(JFrame jFrame, ComplexFeatureServer server) throws IOException{
		jDialog  = new JDialog(jFrame, "MapMatching used Graph Module");
		this.server = server;
		
		queryPointWKT.setColumns(35);
		mapMatchingResult.setColumns(15);
		stateComboBox.addActionListener(this);
		
		FeatureSource fs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","StateType"));
		FeatureIterator fi = fs.getFeatures().features();
		while(fi.hasNext()){
			Feature state = fi.next();
			stateComboBox.addItem(state.getIdentifier().getID());
		}
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(queryPointWKT.getText() != null){
					try {
						String result = getMapMatchingResult(queryPointWKT.getText());
						if(result != null)
							mapMatchingResult.setText(result);
						else
							mapMatchingResult.setText("해당되는 결과가 없습니다");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
				
			}
		});
		
		northPanel.add(queryPointLabel);
		northPanel.add(queryPointWKT);
		centerPanel.add(guidePointLabel);
		centerPanel.add(stateComboBox);
		centerPanel.add(resultLabel);
		centerPanel.add(mapMatchingResult);
		centerPanel.add(submitButton);
		
		jDialog.add(northPanel, BorderLayout.NORTH);
		jDialog.add(centerPanel, BorderLayout.CENTER);
		
		jDialog.setBounds(100, 100, 500, 90);
	}

	protected String getMapMatchingResult(String pointWKT) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Point querypoint = (Point) server.geometryFromWKT(pointWKT);
		Feature f = server.mapMatching(querypoint);
		if(f != null && f.getIdentifier() != null)
			return f.getIdentifier().getID();
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == stateComboBox){
			String stateID = (String) stateComboBox.getSelectedItem();
			FeatureSource statefs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","State"));
			FeatureCollection fc;
			try {
				fc = server.idFilter(statefs, stateID);
				Feature state = (Feature) fc.features().next().getValue().iterator().next();
				Point stateGeometty = (Point) state.getProperties("geometry").iterator().next().getValue();
				
				queryPointWKT.setText(stateGeometty.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
