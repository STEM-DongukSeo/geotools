package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:typeOfTopoExpressionCodeEnumerationType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="typeOfTopoExpressionCodeEnumerationType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:restriction base="xs:string"&gt;
 *  			&lt;xs:enumeration value="CONTAINS"/&gt;
 *  			&lt;xs:enumeration value="OVERLAPS"/&gt;
 *  			&lt;xs:enumeration value="EQUALS"/&gt;
 *  			&lt;xs:enumeration value="WITHIN"/&gt;
 *  			&lt;xs:enumeration value="CROSSES"/&gt;
 *  			&lt;xs:enumeration value="INTERSECTS"/&gt;
 *  		&lt;/xs:restriction&gt;
 *  	&lt;/xs:simpleType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class TypeOfTopoExpressionCodeEnumerationTypeBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.typeOfTopoExpressionCodeEnumerationType;
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
	public Object parse(InstanceComponent instance, Object value) 
		throws Exception {
		
		//TODO: implement and remove call to super
		return super.parse(instance,value);
	}

}