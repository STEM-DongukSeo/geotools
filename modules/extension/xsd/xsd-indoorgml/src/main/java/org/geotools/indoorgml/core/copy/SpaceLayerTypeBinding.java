package org.geotools.indoorgml.core.copy;


import org.geotools.xml.*;
import org.geotools.xml.AbstractComplexBinding;


import javax.xml.namespace.QName;

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
public class SpaceLayerTypeBinding extends AbstractComplexBinding {

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