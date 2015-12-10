/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Spatio-temporal Databases Laboratory (STEMLab)
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
package org.geotools.geometry.iso.sfcgal.wrapper;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFMultiPoint.h")
public class SFMultiPoint extends SFGeometryCollection {
    static {
        Loader.load();
    }

    public SFMultiPoint() {
        allocate();
    }

    public SFMultiPoint(Pointer p) {
        super(p);
    }

    private native void allocate();

    @Name("operator=")
    public native @ByRef SFMultiPoint assign(@ByRef SFMultiPoint other);

    public native SFMultiPoint clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native @ByRef SFPoint pointN(@Cast("size_t") int n);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFMultiPoint multiPoint = new SFMultiPoint();

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        multiPoint.addGeometry(p1);
        multiPoint.addGeometry(p2);
        multiPoint.addGeometry(p3);

        if (multiPoint.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("multiPoint.geomtryType() : " + multiPoint.geometryType());
        System.out.println("multiPoint.numGeometries() : " + multiPoint.numGeometries());
        System.out.println("multiPoint.pointN(2).coodinateDemension : "
                + multiPoint.pointN(2).coordinateDimension());
        System.out.println("multiPoint.pointN(2).demension : " + multiPoint.pointN(2).dimension());
        System.out.println("multiPoint.pointN(2).m : " + multiPoint.pointN(2).m());
    }
}
