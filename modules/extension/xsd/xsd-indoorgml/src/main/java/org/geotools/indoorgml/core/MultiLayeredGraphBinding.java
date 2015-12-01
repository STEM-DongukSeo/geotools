package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;
import org.opengis.feature.Feature;

import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/indoorgml/1.0/core:MultiLayeredGraph.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:element name="MultiLayeredGraph" substitutionGroup="gml:AbstractFeature" type="MultiLayeredGraphType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:annotation&gt;
 *  			&lt;xs:documentation&gt;The overall structure of the Multilayered Space Model constitutes a multilayered graph, where all the nodes from all n layers are included but are separated into n partitions which are connected by the inter-space connections. Furthermore the graph also contains the state transition edges (intra-space connections)
 *  			&lt;/xs:documentation&gt;
 *  		&lt;/xs:annotation&gt;
 *  	&lt;/xs:element&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class MultiLayeredGraphBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.MultiLayeredGraph;
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
		
		//TODO: implement and remove call to super
		return null;
	    //return super.parse(instance,node,value);
	}

}