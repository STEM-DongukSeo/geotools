/**
 * 
 */
package org.geotools.gml3.v3_2.bindings;

import javax.xml.namespace.QName;

import org.geotools.gml3.GML;
import org.geotools.gml3.XSDIdRegistry;
import org.geotools.gml3.bindings.GML3EncodingUtils;
import org.geotools.gml3.bindings.GeometryPropertyTypeBindingBase;
import org.opengis.geometry.primitive.Solid;

/**
 * @author hgryoo
 *
 */
public class SolidPropertyTypeBinding extends GeometryPropertyTypeBindingBase {

    public SolidPropertyTypeBinding(GML3EncodingUtils encodingUtils, XSDIdRegistry idRegistry) {
        super(encodingUtils, idRegistry);
    }
    @Override
    public QName getTarget() {
        return GML.SolidPropertyType;
    }
    
    public Class getType() {
        return Solid.class;
    }
    
}