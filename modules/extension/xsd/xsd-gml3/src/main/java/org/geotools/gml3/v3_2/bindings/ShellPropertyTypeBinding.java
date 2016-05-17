/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2016, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.gml3.v3_2.bindings;

import javax.xml.namespace.QName;

import org.geotools.gml3.XSDIdRegistry;
import org.geotools.gml3.bindings.GML3EncodingUtils;
import org.geotools.gml3.bindings.GeometryPropertyTypeBinding;
import org.geotools.gml3.v3_2.GML;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.opengis.geometry.primitive.Shell;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;

/**
 * @author Donguk
 *
 */
public class ShellPropertyTypeBinding extends GeometryPropertyTypeBinding {
    
    GeometryFactory gf;
    
    public ShellPropertyTypeBinding(GML3EncodingUtils encodingUtils, XSDIdRegistry idRegistry, GeometryFactory gf) {
        super(encodingUtils, idRegistry);
        this.gf = gf;
    }

    /**
     * @generated
     */
    public QName getTarget() {
        return GML.ShellPropertyType;
    }

    public Class getGeometryType() {
        return Shell.class;
    }

    @Override
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        Shell surface = (Shell)node.getChildValue(Shell.class);
        
        return surface;
    }

    @Override
    public Object getProperty(Object object, QName name) throws Exception {
        if ("_Surface".equals(name.getLocalPart()) || "AbstractSurface".equals(name.getLocalPart())) {
            MultiPolygon multiPolygon = (MultiPolygon) object;
            // this MultiPolygon consists of a single Polygon wrapped in a MultiPolygon:
            return multiPolygon.getGeometryN(0);
        }

        return super.getProperty(object, name);
    }
}
