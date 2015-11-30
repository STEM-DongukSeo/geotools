package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/indoorgml/1.0/core:CellSpace.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:element name="CellSpace" substitutionGroup="gml:AbstractFeature" type="CellSpaceType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:annotation&gt;
 *  			&lt;xs:documentation&gt;
 *  				Within the dual graph structure of one layer a node in dual space represents a space (e.g. a room within a building) in primal space
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
public class CellSpaceBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpace;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return null;
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
		return super.parse(instance,node,value);
	}

}