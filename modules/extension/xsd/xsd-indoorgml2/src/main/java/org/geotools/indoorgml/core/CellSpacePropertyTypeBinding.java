package org.geotools.indoorgml.core;


import org.geotools.gml2.FeatureTypeCache;
import org.geotools.indoorgml.core.binding.GMLComplexParsingUtils;
import org.geotools.xml.*;
import org.opengis.feature.Association;
import org.opengis.feature.Feature;

import javax.xml.namespace.QName;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:CellSpacePropertyType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="CellSpacePropertyType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:sequence minOccurs="0"&gt;
 *  			&lt;xs:element ref="CellSpace"/&gt;
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
public class CellSpacePropertyTypeBinding extends AbstractComplexBinding {

        FeatureTypeCache ftCache;
        BindingWalkerFactory bwFactory;
        
        public CellSpacePropertyTypeBinding(FeatureTypeCache ftCache, BindingWalkerFactory bwFactory) {
            this.ftCache = ftCache;
            this.bwFactory = bwFactory;
        }
        
	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.CellSpacePropertyType;
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
		System.out.println("### CellSpacePropertyTypeBinding ###");
		return node.getChildValues(Feature.class);
	}

}