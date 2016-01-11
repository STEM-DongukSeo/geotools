import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;

@SuppressWarnings("serial")
public class ISA_Sample extends JFrame {
    /**
	 * 
	 */
	JTable userTable;
    JScrollPane jScrollPane;
    JPanel jPanel;
    JMenuBar menuBar;
    JMenu adminMenu;
    
    DefaultTableModel model;
    
    Vector<String> userColumn = new Vector<String>();
    Vector<String> userRow;

    public ISA_Sample(String title){
        super(title);
        
        // Menu Setting
        menuBar = new JMenuBar();
        adminMenu = new JMenu("File");
        adminMenu.add(new JMenuItem("Open IndoorGML File"));
        menuBar.add(adminMenu);
        setJMenuBar(menuBar);
        
        adminMenu.getItem(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        // Table Setting
        userTable = new JTable();     
        jScrollPane = new JScrollPane();
        jPanel  = new JPanel();
        
        userColumn.addElement("TypeName");
		userColumn.addElement("ID");
		userColumn.addElement("Attribute");
		userColumn.addElement("Geometry");
		
		model = new DefaultTableModel(userColumn,0);    
        userTable.setModel(model);
        jPanel.setLayout(new BorderLayout());
        jScrollPane.setViewportView(userTable);
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        add(jPanel);
        
        setBounds(150,100,800,400);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
    }

	@SuppressWarnings({ "rawtypes" })
	public void addFeatureCollection(FeatureCollection features) {
		// TODO Auto-generated method stub
		FeatureIterator iterator = features.features();
		while (iterator.hasNext()) {
			Feature feature = iterator.next();
			if (feature.getName().getLocalPart().equals("CellSpace")) {
				getCellSpace(feature);
			} else if (feature.getName().getLocalPart().equals("State")) {
				getState(feature);
			} else if (feature.getName().getLocalPart().equals("Transition")) {
				getTransition(feature);
			} 
		}
	}

	@SuppressWarnings("unchecked")
	private void getTransition(Feature feature) {
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		while (pi.hasNext()) {
			Property p = pi.next();
			Iterator<? extends Attribute> ia = (Iterator<? extends Attribute>) ((Collection<? extends Property>) p.getValue()).iterator();
			String attribute = "";
			String geometry = "";
			while (ia.hasNext()) {
				Attribute a = ia.next();
				if (a.getName().getLocalPart().equals("connects")) {
					Iterator<? extends Feature> f = ((Collection<? extends Feature>) a.getValue()).iterator();
					attribute += "| connects : " + f.next().getIdentifier().toString() + " |";
				} else if (a.getName().getLocalPart().equals("weight")) {
					attribute += "| weight : " + a.getValue().toString() + " |";
				} else if (GeometryAttribute.class.isAssignableFrom(a.getClass())) {
					geometry = a.getValue().toString();
				}
			}
			userRow.add(2, attribute);
			userRow.add(3, geometry);
			attribute = "";
			geometry = "";
		}
		model.addRow(userRow);
	}

	@SuppressWarnings("unchecked")
	private void getState(Feature feature) {
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		while (pi.hasNext()) {
			Property p = pi.next();
			Iterator<? extends Attribute> ia = (Iterator<? extends Attribute>) ((Collection<? extends Property>) p.getValue()).iterator();
			String attribute = "";
			String geometry = "";
			while (ia.hasNext()) {
				Attribute a = ia.next();
				if (a.getName().getLocalPart().equals("duality")) {
					Iterator<? extends Feature> f = ((Collection<? extends Feature>) a.getValue()).iterator();
					attribute += "| duality : " + f.next().getIdentifier() + " |";
				} else if (a.getName().getLocalPart().equals("connects")) {
					//Iterator<? extends Feature> f = ((Collection<? extends Feature>) a.getValue()).iterator();
					//attribute += "| connects : " + f.next().getIdentifier() + " |";
				} else if (GeometryAttribute.class.isAssignableFrom(a.getClass())) {
					geometry = a.getValue().toString();
				}
			}
			userRow.add(2, attribute);
			userRow.add(3, geometry);
			attribute = "";
			geometry = "";
		}
		model.addRow(userRow);
	}

	@SuppressWarnings("unchecked")
	private void getCellSpace(Feature feature) {
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		while (pi.hasNext()) {
			Property p = pi.next();
			Iterator<? extends Attribute> ia = (Iterator<? extends Attribute>) ((Collection<? extends Property>) p.getValue()).iterator();
			String attribute = "";
			String geometry = "";
			while (ia.hasNext()) {
				Attribute a = ia.next();
				if (a.getName().getLocalPart().equals("duality")) {
					Iterator<? extends Feature> f = ((Collection<? extends Feature>) a.getValue()).iterator();
					attribute += "| duality : " + f.next().getIdentifier() + " |";
				} else if (a.getName().getLocalPart().equals("externalReference")) {
					// attribute += "| weight : " +
					// a.getValue().toString() + " |";
				} else if (GeometryAttribute.class.isAssignableFrom(a.getClass())) {
					geometry = a.getValue().toString();
				}
			}
			userRow.add(2, attribute);
			userRow.add(3, geometry);
			attribute = "";
			geometry = "";
		}
		model.addRow(userRow);
	}
}
