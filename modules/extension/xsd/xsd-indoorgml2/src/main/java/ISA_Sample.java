import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.geotools.data.FeatureSource;
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
	private JPanel jMainPanel;
	private JPanel jstatusPanel;
	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenu queryMenu;
	private DefaultMutableTreeNode root;
	private JLabel statusLabel;
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
        queryMenu = new JMenu("Query");
        queryMenu.add(new JMenuItem("Contain(MapMatching)"));
        queryMenu.add(new JMenuItem("Intersect"));
        queryMenu.add(new JMenuItem("Routing"));
        menuBar.add(adminMenu);
        menuBar.add(queryMenu);
        setJMenuBar(menuBar);
        
        // Open Schema File
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
						statusLabel.setText("Schema Import End");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
			}
		});
        
        // Open IndoorGML File
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
						DefaultTreeModel model = (DefaultTreeModel)tree.getModel(); 
			            DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getModel().getRoot();
						while(fi.hasNext()){
							FeatureType ft = fi.next();
							DefaultMutableTreeNode schemaElement = new DefaultMutableTreeNode(ft.getName().toString());
							boolean isInserted = false;
							for(int i = 0; i< node.getChildCount(); i++){
								if(node.getChildAt(i).toString().equals(schemaElement.toString()))
									isInserted = true;
							}
							if(!isInserted)
								node.add(schemaElement);
						}
						model.reload(node);
						
						statusLabel.setText("GML Import End");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
			}
		});
        
        // Contain(MapMatching)
        queryMenu.getItem(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MapMatchingDialog mapMatchingDialog;
				try {
					mapMatchingDialog = new MapMatchingDialog(ISA_Sample.this, server);
					mapMatchingDialog.jDialog.setModal(true);
					mapMatchingDialog.jDialog.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        // Intersect
        queryMenu.getItem(1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IntersectDialog intersectionDialog;
				try {
					intersectionDialog = new IntersectDialog(ISA_Sample.this, server);
					intersectionDialog.jDialog.setModal(true);
					intersectionDialog.jDialog.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

        // Routing
		queryMenu.getItem(2).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoutingDialog routingDialog;
				try {
					routingDialog = new RoutingDialog(ISA_Sample.this, server);
					routingDialog.jDialog.setModal(true);
					routingDialog.jDialog.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        jMainPanel  = new JPanel();
        
        // Tree Setting
        root = new DefaultMutableTreeNode("Schema");
        tree = new JTree(root);
        jScrollPaneTree = new JScrollPane(tree);
        jScrollPaneTree.setPreferredSize(new Dimension(430, 700));
        tree.setVisibleRowCount(10);
        tree.addTreeSelectionListener(this);
        
        jMainPanel.add(jScrollPaneTree);
        
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
        jScrollPane.setPreferredSize(new Dimension(830, 700));
        jMainPanel.add(jScrollPane, BorderLayout.CENTER);
        
        jstatusPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) jstatusPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        statusLabel = new JLabel("init status");
        jstatusPanel.add(statusLabel);
        
        add(jMainPanel, BorderLayout.CENTER);
        add(jstatusPanel, BorderLayout.SOUTH);
        
        setBounds(150,100,1300,800);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

	@SuppressWarnings({ "rawtypes" })
	public void addFeatureCollection(FeatureCollection features) {
		// TODO Auto-generated method stub
		model = new DefaultTableModel(userColumn,0);
		
		FeatureIterator iterator = features.features();
		while (iterator.hasNext()) {
			Feature feature = iterator.next();
			if (feature.getName().getLocalPart().equals("CellSpaceType")) {
				getCellSpace(feature);
			} else if (feature.getName().getLocalPart().equals("StateType")) {
				getState(feature);
			} else if (feature.getName().getLocalPart().equals("TransitionType")) {
				getTransition(feature);
			} 
		}
		userTable.setModel(model);
	}

	@SuppressWarnings("unchecked")
	private void getTransition(Feature feature) {
        
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		String attribute = "";
		String geometry = "";
		while (pi.hasNext()) {
			Property p = pi.next();
			if (p.getName().getLocalPart().equals("connects")) {
				Iterator<? extends Feature> f = ((Collection<? extends Feature>) p.getValue()).iterator();
				attribute += "| connects : " + f.next().getIdentifier().toString() + " |";
			} else if (p.getName().getLocalPart().equals("weight")) {
				attribute += "| weight : " + p.getValue().toString() + " |";
			} else if (GeometryAttribute.class.isAssignableFrom(p.getClass())) {
				geometry = p.getValue().toString();
			}
		}
		userRow.add(2, attribute);
		userRow.add(3, geometry);
		attribute = "";
		geometry = "";
		model.addRow(userRow);
	}

	@SuppressWarnings("unchecked")
	private void getState(Feature feature) {
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		String attribute = "";
		String geometry = "";
		while (pi.hasNext()) {
			Property p = pi.next();
			if (p.getName().getLocalPart().equals("duality")) {
				Iterator<? extends Feature> f = ((Collection<? extends Feature>) p.getValue()).iterator();
				attribute += "| duality : " + f.next().getIdentifier() + " |";
			} else if (p.getName().getLocalPart().equals("connects")) {
				Iterator<? extends Feature> f = ((Collection<? extends Feature>) p.getValue()).iterator();
				attribute += "| connects : " + f.next().getIdentifier() + " |";
			} else if (GeometryAttribute.class.isAssignableFrom(p.getClass())) {
				geometry = p.getValue().toString();
			}
		}
		userRow.add(2, attribute);
		userRow.add(3, geometry);
		attribute = "";
		geometry = "";
		model.addRow(userRow);
	}

	@SuppressWarnings("unchecked")
	private void getCellSpace(Feature feature) {
		userRow = new Vector<String>();
		userRow.add(0, feature.getName().getLocalPart());
		userRow.add(1, feature.getIdentifier().getID());

		Collection<Property> pc = feature.getProperties();
		Iterator<Property> pi = pc.iterator();
		String attribute = "";
		String geometry = "";
		while (pi.hasNext()) {
			Property p = pi.next();
			if (p.getName().getLocalPart().equals("duality")) {
				Iterator<? extends Feature> f = ((Collection<? extends Feature>) p.getValue()).iterator();
				attribute += "| duality : " + f.next().getIdentifier() + " |";
			} else if (p.getName().getLocalPart().equals("externalReference")) {
				// attribute += "| weight : " +
				// a.getValue().toString() + " |";
			} else if (GeometryAttribute.class.isAssignableFrom(p.getClass())) {
				geometry = p.getValue().toString();
			}
		}
		userRow.add(2, attribute);
		userRow.add(3, geometry);
		attribute = "";
		geometry = "";
		model.addRow(userRow);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tree){
			DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if(selNode != null)
            {
            	try {
	            	String name = (String) selNode.getUserObject();
	            	String[] sname = name.split(":");
	            	if(sname.length >= 3){
	            		FeatureSource fs = server.getFeatureSource(new NameImpl(sname[0] + ":" + sname[1],sname[2]));
	            		addFeatureCollection(fs.getFeatures());
	            	}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		}
	}
}
