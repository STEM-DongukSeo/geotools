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
package org.geotools.feature;

import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.filter.identity.Identifier;
import org.opengis.geometry.BoundingBox;
import org.opengis.geometry.Geometry;

/**
 * The GeometryAttribute Implementation providing geometry model based on ISO19107
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public class ISOGeometryAttributeImpl extends AttributeImpl implements GeometryAttribute {
    
    protected BoundingBox bounds;
    
    public ISOGeometryAttributeImpl(Object content, GeometryDescriptor descriptor, Identifier id) {
        super(content, descriptor, id);
    }

    @Override
    public GeometryType getType() {
        return (GeometryType) super.getType();
    }

    @Override
    public GeometryDescriptor getDescriptor() {
        return (GeometryDescriptor) super.getDescriptor();
    }
    
    @Override
    public void setValue(Object newValue) throws IllegalArgumentException, IllegalStateException {
        super.setValue( (Geometry) newValue );
    }
    
    public void setValue(Geometry geometry) {
        this.setValue(geometry);
    }

    @Override
    public Object getValue() {
        return (Geometry) super.getValue();
    }

    @Override
    public synchronized BoundingBox getBounds() {
        if(bounds == null) {
            //TODO
        }
        return bounds;
    }

    @Override
    public synchronized void setBounds(BoundingBox bbox) {
        this.bounds = bbox;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
