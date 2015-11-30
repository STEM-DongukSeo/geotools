package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:CellSpaceType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
 *  				&lt;xs:sequence&gt;
 *  					&lt;xs:group maxOccurs="1" minOccurs="0" ref="CellSpaceGeometry"/&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="duality" type="StatePropertyType"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="externalReference" type="ExternalReferenceType"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="partialboundedBy" type="CellSpaceBoundaryPropertyType"/&gt;
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
public class CellSpaceTypeBinding extends AbstractComplexBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpaceType;
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