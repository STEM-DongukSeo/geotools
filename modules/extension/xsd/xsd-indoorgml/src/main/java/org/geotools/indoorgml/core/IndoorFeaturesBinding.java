package org.geotools.indoorgml.core;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/indoorgml/1.0/core:IndoorFeatures.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:element name="IndoorFeatures" substitutionGroup="gml:AbstractFeature" type="IndoorFeaturesType" xmlns:xs="http://www.w3.org/2001/XMLSchema"/&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class IndoorFeaturesBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.IndoorFeatures;
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
	    
	    System.out.println("IndoorFeaturesBinding");
	    
	    return null;
	}

}