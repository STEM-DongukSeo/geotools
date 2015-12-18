/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Spatio-temporal Databases Laboratory(STEMLab)
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
package org.geotools.geometry.iso.sfcgal.relate;

import org.geotools.geometry.iso.root.GeometryImpl;

/**
 * @author Donguk Seo
 *
 */
public class RelateOp3D {
    public static IntersectionMatrix3D relate(GeometryImpl a, GeometryImpl b) {
        RelateOp3D relOp = new RelateOp3D(a, b);
        IntersectionMatrix3D im = relOp.getIntersectionMatrix();
        return im;
    }

    private RelateComputer3D relate;

    RelateOp3D(GeometryImpl gA, GeometryImpl gB) {
        this.relate = new RelateComputer3D(gA, gB);
    }

    public IntersectionMatrix3D getIntersectionMatrix() {
        return this.relate.computeIM3D();
    }
}
