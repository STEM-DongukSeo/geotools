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
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:SpaceLayerType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="SpaceLayerType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
 *  				&lt;xs:sequence&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="usage" type="gml:CodeType"/&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="terminationDate" type="xs:dateTime"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="function" type="gml:CodeType"/&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="creationDate" type="xs:dateTime"/&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="class" type="SpaceLayerClassTypeType"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="nodes" type="NodesType"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="edges" type="EdgesType"/&gt;
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
public class SpaceLayerTypeBinding extends AbstractFeatureTypeBinding {

	public SpaceLayerTypeBinding(FeatureTypeCache ftCache, BindingWalkerFactory bwFactory, SchemaIndex schemaIndex,
			Configuration configuration, XSDIdRegistry idRegistry, GML3EncodingUtils encodingUtils) {
		super(ftCache, bwFactory, schemaIndex, configuration, idRegistry, encodingUtils);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.SpaceLayerType;
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
		return super.parse(instance,node,value);
	}

}