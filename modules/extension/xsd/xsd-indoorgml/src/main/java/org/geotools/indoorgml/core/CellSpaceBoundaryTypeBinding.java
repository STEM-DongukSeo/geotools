package org.geotools.indoorgml.core;


import javax.xml.namespace.QName;

import org.geotools.gml2.FeatureTypeCache;
import org.geotools.gml3.XSDIdRegistry;
import org.geotools.gml3.bindings.AbstractFeatureTypeBinding;
import org.geotools.gml3.bindings.GML3EncodingUtils;
import org.geotools.xml.BindingWalkerFactory;
import org.geotools.xml.Configuration;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.geotools.xml.SchemaIndex;
import org.opengis.feature.Feature;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:CellSpaceBoundaryType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceBoundaryType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
 *  				&lt;xs:sequence&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="TransitionPropertyType"/&gt;
 *  					&lt;xs:group minOccurs="0" ref="CellSpaceBoundaryGeometry"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="externalReference" type="ExternalReferenceType"/&gt;
 *  				&lt;/xs:sequence&gt;
 *  			&lt;/xs:extension&gt;
 *  		&lt;/xs:complexContent&gt;
 *  	&lt;/xs:complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class CellSpaceBoundaryTypeBinding extends AbstractFeatureTypeBinding {

	public CellSpaceBoundaryTypeBinding(FeatureTypeCache ftCache, BindingWalkerFactory bwFactory,
			SchemaIndex schemaIndex, Configuration configuration, XSDIdRegistry idRegistry,
			GML3EncodingUtils encodingUtils) {
		super(ftCache, bwFactory, schemaIndex, configuration, idRegistry, encodingUtils);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpaceBoundaryType;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return Feature.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {

		System.out.println("CellSpaceBoundaryTypeBinding");
        
        System.out.println(node.toString());
        System.out.println(node.getChildValue(Feature.class));
        System.out.println(value);
		
		//TODO: implement and remove call to super
		return super.parse(instance,node,value);
	}

}