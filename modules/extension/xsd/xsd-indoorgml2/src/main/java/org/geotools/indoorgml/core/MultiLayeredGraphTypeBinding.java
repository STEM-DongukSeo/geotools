package org.geotools.indoorgml.core;


import javax.xml.namespace.QName;

import org.geotools.gml2.FeatureTypeCache;
import org.geotools.gml3.XSDIdRegistry;
import org.geotools.gml3.bindings.AbstractFeatureTypeBinding;
import org.geotools.gml3.bindings.GML3EncodingUtils;
import org.geotools.indoorgml.core.binding.GMLComplexParsingUtils;
import org.geotools.xml.BindingWalkerFactory;
import org.geotools.xml.Configuration;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.geotools.xml.SchemaIndex;
import org.opengis.feature.Feature;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:MultiLayeredGraphType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="MultiLayeredGraphType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
 *  				&lt;xs:sequence&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="1" name="spaceLayers" type="SpaceLayersType"/&gt;
 *  					&lt;xs:element maxOccurs="unbounded" minOccurs="0" name="interEdges" type="InterEdgesType"/&gt;
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
public class MultiLayeredGraphTypeBinding extends AbstractFeatureTypeBinding {

	public MultiLayeredGraphTypeBinding(FeatureTypeCache ftCache, BindingWalkerFactory bwFactory,
			SchemaIndex schemaIndex, Configuration configuration, XSDIdRegistry idRegistry,
			GML3EncodingUtils encodingUtils) {
		super(ftCache, bwFactory, schemaIndex, configuration, idRegistry, encodingUtils);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.MultiLayeredGraphType;
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
	    System.out.println("### MultiLayeredGraphTypeBinding ###");
	    return GMLComplexParsingUtils.parseFeature(instance, node, value, super.getFeatureTypeCache(), super.getBindingWalkerFactory());
	}

}