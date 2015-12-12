package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;
import org.opengis.feature.Association;
import org.opengis.feature.Feature;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:PrimalSpaceFeaturesPropertyType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="PrimalSpaceFeaturesPropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:sequence minOccurs="0"&gt;
 *  			&lt;xs:element ref="PrimalSpaceFeatures"/&gt;
 *  		&lt;/xs:sequence&gt;
 *  		&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
 *  	&lt;/xs:complexType&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class PrimalSpaceFeaturesPropertyTypeBinding extends AbstractComplexBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.PrimalSpaceFeaturesPropertyType;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Class getType() {
		return Association.class;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *	
	 * @generated modifiable
	 */	
	public Object parse(ElementInstance instance, Node node, Object value) 
		throws Exception {
	    System.out.println("### PrimalSpaceFeaturesPropertyTypeBinding ###");
            return node.getChildValue(Feature.class);
	}

}