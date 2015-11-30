package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/indoorgml/1.0/core:CellSpaceBoundary.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:element name="CellSpaceBoundary" substitutionGroup="gml:AbstractFeature" type="CellSpaceBoundaryType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:annotation&gt;
 *  			&lt;xs:documentation&gt;  Within the dual graph structure of one layer a edge in dual space represents a spaceboundary (e.g. a wall between adjacenced rooms in a building) in primal spac
 *  		&lt;/xs:documentation&gt;
 *  		&lt;/xs:annotation&gt;
 *  	&lt;/xs:element&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class CellSpaceBoundaryBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpaceBoundary;
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