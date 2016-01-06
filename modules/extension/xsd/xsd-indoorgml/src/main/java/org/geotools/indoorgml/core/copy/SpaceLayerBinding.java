package org.geotools.indoorgml.core.copy;


import org.geotools.xml.*;
import org.geotools.xml.AbstractSimpleBinding;


import javax.xml.namespace.QName;

/**
 * Binding object for the element http://www.opengis.net/indoorgml/1.0/core:SpaceLayer.
 *
 * <p>
 *	<pre>
 *	 <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:element name="SpaceLayer" substitutionGroup="gml:AbstractFeature" type="SpaceLayerType" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;
 *  		&lt;xs:annotation&gt;
 *  			&lt;xs:documentation&gt;&lt;SpaceLayer&gt;s represent various space concepts such as topography, sensor, security, etc. A SpaceLayer aggregates  &lt;State&gt; and &lt;Transition&gt; which are directly associated with the corresponding geometry classes.&lt;/xs:documentation&gt;
 *  		&lt;/xs:annotation&gt;
 *  	&lt;/xs:element&gt; 
 *		
 *	  </code>
 *	 </pre>
 * </p>
 *
 * @generated
 */
public class SpaceLayerBinding extends AbstractSimpleBinding {

	/**
	 * @generated
	 */
	public QName getTarget() {
		return INDOORCORE.SpaceLayer;
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