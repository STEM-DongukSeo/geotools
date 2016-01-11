import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.NameImpl;
import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;

@SuppressWarnings("serial")
public class ISA_Sample extends JFrame implements TreeSelectionListener {
    /**
	 * 
	 */
	private JTable userTable;
	private JScrollPane jScrollPane;
	private JScrollPane jScrollPaneTree;
	private JPanel jPanel;
	private JMenuBar menuBar;
	private JMenu adminMenu;
	private DefaultMutableTreeNode root;
    private JTree tree;
    
	private DefaultTableModel model;
    
	private Vector<String> userColumn;
	private Vector<String> userRow;
    
	private ComplexFeatureServer server;

    public ISA_Sample(String title){
        super(title);
        
        server = new ComplexFeatureServer();
        
        // Menu Setting
        menuBar = new JMenuBar();
        adminMenu = new JMenu("File");
        adminMenu.add(new JMenuItem("Open Schema File"));
        adminMenu.add(new JMenuItem("Open IndoorGML File"));
        menuBar.add(adminMenu);
        setJMenuBar(menuBar);
        
        adminMenu.getItem(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filePath;
				JFileChooser fileChooser = new JFileChooser("C:\\Users\\STEM\\Desktop\\Git\\geotools\\modules\\extension\\xsd\\xsd-indoorgml2\\src\\test\\resources\\org\\geotools\\indoorgml\\core");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Schema(*.xsd)", "xsd");
		        fileChooser.setFileFilter(filter);
		        
		        int returnVal = fileChooser.showOpenDialog(ISA_Sample.this);
	            if( returnVal == JFileChooser.APPROVE_OPTION)
	            {
	                File file = fileChooser.getSelectedFile();
	                try {
						server.registerSchema(file.toURL(), new NameImpl("http://www.opengis.net/indoorgml/1.0/core", ":", "IndoorFeatures"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
			}
		});
        
        adminMenu.getItem(1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filePath;
				JFileChooser fileChooser = new JFileChooser("C:\\Users\\STEM\\Desktop\\Git\\geotools\\modules\\extension\\xsd\\xsd-indoorgml2\\src\\test\\resources");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("GML data(*.gml)", "gml");
		        fileChooser.setFileFilter(filter);
		        
		        int returnVal = fileChooser.showOpenDialog(ISA_Sample.this);
	            if( returnVal == JFileChooser.APPROVE_OPTION)
	            {
	                File file = fileChooser.getSelectedFile();
	                try {
						server.getResource(file.toURL());
						
						// Add Tree componenet
						Collection<FeatureType> fc = server.getFeatureTypes().values();
						Iterator<FeatureType> fi = fc.iterator();
						DefaultTreeModel model = (DefaultTreeModel)tree.getModel(); //모델정보를 가져온다
			            DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getModel().getRoot();
						while(fi.hasNext()){
				            node.add(new DefaultMutableTreeNode(fi.next().getName().getLocalPart()));
						}
						model.reload(node);
			            //tree.expandPath();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
			}
		});
        
        jPanel  = new JPanel(new GridLayout(0,2,5,5));
        
        // Tree Setting
        root = new DefaultMutableTreeNode("Schema");
        tree = new JTree(root);
        jScrollPaneTree = new JScrollPane(tree);
        tree.setVisibleRowCount(10);
        tree.addTreeSelectionListener(this);
        
        jPanel.add(jScrollPaneTree);
        
        // Table Setting
        userTable = new JTable();     
        jScrollPane = new JScrollPane();
        userColumn = new Vector<String>();
        userColumn.addElement("TypeName");
		userColumn.addElement("ID");
		userColumn.addElement("Attribute");
		userColumn.addElement("Geometry");
		model = new DefaultTableModel(userColumn,0);    
        userTable.setModel(model);
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

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tree){
			DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if(selNode != null)
            {
            	
            }
		}
	}
}
