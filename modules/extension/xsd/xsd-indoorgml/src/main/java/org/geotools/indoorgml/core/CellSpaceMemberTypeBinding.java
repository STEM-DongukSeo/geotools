package org.geotools.indoorgml.core;


import org.geotools.gml2.FeatureTypeCache;
import org.geotools.indoorgml.core.binding.GMLComplexParsingUtils;
import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;
import org.opengis.feature.Association;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:CellSpaceMemberType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpaceMemberType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureMemberType"&gt;
 *  				&lt;xs:sequence minOccurs="0"&gt;
 *  					&lt;xs:element ref="CellSpace"/&gt;
 *  				&lt;/xs:sequence&gt;
 *  				&lt;xs:attributeGroup ref="gml:AssociationAttributeGroup"/&gt;
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
public class CellSpaceMemberTypeBinding extends AbstractComplexBinding {

        FeatureTypeCache ftCache;
        
        public CellSpaceMemberTypeBinding(FeatureTypeCache ftCache) {
            this.ftCache = ftCache;
        }
        
	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpaceMemberType;
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
	    return GMLComplexParsingUtils.parseAssociation(instance, node, value, ftCache);
	}

}