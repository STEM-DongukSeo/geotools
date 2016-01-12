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
import org.opengis.geometry.Geometry;

public class IntersectDialog {
	public JDialog jDialog;
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JButton submitButton = new JButton("Submit");
	private JLabel queryGeometryLabel = new JLabel("Query Geometry (WKT)", SwingConstants.CENTER);
	private JLabel resultLabel = new JLabel("Intersection Result (CellSpace):", SwingConstants.CENTER);
	private JTextField intersectionResult = new JTextField();
	private JTextField queryGeometryWKT = new JTextField();
	
	private ComplexFeatureServer server = null;
	
	public IntersectDialog(JFrame jFrame, ComplexFeatureServer server) throws IOException{
		jDialog  = new JDialog(jFrame, "Intersection Query");
		this.server = server;
		
		queryGeometryWKT.setColumns(35);
		intersectionResult.setColumns(23);
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(queryGeometryWKT.getText() != null){
					String result;
					try {
						result = getIntersectionResult(queryGeometryWKT.getText());
						if(result != null)
							intersectionResult.setText(result);
						else
							intersectionResult.setText("해당되는 결과가 없습니다");
					} catch (ParseException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		northPanel.add(queryGeometryLabel);
		northPanel.add(queryGeometryWKT);
		centerPanel.add(resultLabel);
		centerPanel.add(intersectionResult);
		centerPanel.add(submitButton);
		
		jDialog.add(northPanel, BorderLayout.NORTH);
		jDialog.add(centerPanel, BorderLayout.CENTER);
		
		jDialog.setBounds(100, 100, 550, 110);
	}

	protected String getIntersectionResult(String geometryWKT) throws ParseException, IOException {
		// TODO Auto-generated method stub
		String intersectResult = null;
		Geometry geometry = server.geometryFromWKT(geometryWKT);
		FeatureSource cellSpacefs = server.getFeatureSource(new NameImpl("http://www.opengis.net/indoorgml/1.0/core","CellSpace"));
		FeatureCollection fc = server.spatialFilter(cellSpacefs, "Intersects", "Geometry3D", geometry);
		FeatureIterator fi = fc.features();
		if(fi.hasNext())
			intersectResult = "";
		while(fi.hasNext()){
			Feature f = fi.next();
			intersectResult += f.getIdentifier().getID();
			intersectResult += ", ";
		}
		return intersectResult;
	}
}
