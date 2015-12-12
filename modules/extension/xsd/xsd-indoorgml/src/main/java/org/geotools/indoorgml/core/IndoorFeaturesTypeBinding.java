package org.geotools.indoorgml.core;


import javax.xml.namespace.QName;

import org.geotools.gml2.FeatureTypeCache;
import org.geotools.gml3.XSDIdRegistry;
import org.geotools.gml3.bindings.AbstractFeatureTypeBinding;
import org.geotools.indoorgml.core.binding.GMLComplexParsingUtils;
import org.geotools.xml.BindingWalkerFactory;
import org.geotools.xml.Configuration;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.geotools.xml.SchemaIndex;
import org.opengis.feature.Feature;

/**
 * Binding object for the type http://www.opengis.net/indoorgml/1.0/core:IndoorFeaturesType.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:complexType name="IndoorFeaturesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:complexContent&gt;
 *  			&lt;xs:extension base="gml:AbstractFeatureType"&gt;
 *  				&lt;xs:sequence&gt;
 *  					&lt;xs:element maxOccurs="1" minOccurs="0" name="primalSpaceFeatures" type="PrimalSpaceFeaturesPropertyType"/&gt;
 *  					&lt;xs:element ref="MultiLayeredGraph"/&gt;
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
public class IndoorFeaturesTypeBinding extends AbstractFeatureTypeBinding {

    FeatureTypeCache ftCache;
    XSDIdRegistry idSet;
    BindingWalkerFactory bwFactory;
    SchemaIndex schemaIndex;
    Configuration configuration;

    public IndoorFeaturesTypeBinding(FeatureTypeCache ftCache,
            BindingWalkerFactory bwFactory, SchemaIndex schemaIndex,
            Configuration configuration, XSDIdRegistry idRegistry) {
        super(ftCache, bwFactory, schemaIndex, configuration, idRegistry, null);
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return INDOORCORE.IndoorFeaturesType;
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
        System.out.println("### IndoorFeaturesTypeBinding ###");
        return GMLComplexParsingUtils.parseFeature(instance, node, value, super.getFeatureTypeCache(), super.getBindingWalkerFactory());
    }
}