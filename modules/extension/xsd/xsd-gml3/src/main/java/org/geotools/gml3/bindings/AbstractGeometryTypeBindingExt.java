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
package org.geotools.gml3.bindings;

import javax.xml.namespace.QName;

import org.geotools.gml2.SrsSyntax;
import org.geotools.xml.Configuration;

/**
 * @author Donguk Seo
 *
 */
public class AbstractGeometryTypeBindingExt extends AbstractGeometryTypeBinding {

    /**
     * @param config
     * @param srsSyntax
     */
    public AbstractGeometryTypeBindingExt(Configuration config, SrsSyntax srsSyntax) {
        super(config, srsSyntax);
    }

    @Override
    public Object getProperty(Object object, QName name) throws Exception {
        if (object instanceof org.geotools.geometry.iso.root.GeometryImpl) {
            return null;
        }
        
        return super.getProperty(object, name);
    }
}
