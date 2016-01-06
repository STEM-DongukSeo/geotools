package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;
import org.opengis.feature.Feature;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:SpaceLayerClassTypeType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:simpleType name="SpaceLayerClassTypeType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:restriction base="xs:string"&gt;
 *  			&lt;xs:enumeration value="TOPOGRAPHIC"/&gt;
 *  			&lt;xs:enumeration value="SENSOR"/&gt;
 *  			&lt;xs:enumeration value="LOGICAL"/&gt;
 *  			&lt;xs:enumeration value="TAGS"/&gt;
 *  			&lt;xs:enumeration value="LOGICAL"/&gt;
 *  			&lt;xs:enumeration value="UNKNOWN"/&gt;
 *  		&lt;/xs:restriction&gt;
 *  	&lt;/xs:simpleType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class SpaceLayerClassTypeTypeBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.SpaceLayerClassTypeType;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return String.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(InstanceComponent instance, Object value) 
		throws Exception {
		
		System.out.println("SpaceLayerClassTypeBinding");
        System.out.println(value);
		
		//TODO: implement and remove call to super
		return value;
	}

}