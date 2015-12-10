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
@Platform(include = "cpp/SFGeometryCollection.h")
public class SFGeometryCollection extends SFGeometry {
    static {
        Loader.load();
    }

    public SFGeometryCollection() {
        allocate();
    }

    public SFGeometryCollection(Pointer p) {
        super(p);
    }

    private native void allocate();

    @Name("operator=")
    public native @ByRef SFGeometryCollection assign(@ByRef SFGeometryCollection other);

    public native SFGeometryCollection clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native @Cast("size_t") int numGeometries();

    public native @ByRef SFGeometry geometryN(@Cast("size_t") int n);

    public native void addGeometry(@ByRef SFGeometry geometry);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFGeometryCollection collection = new SFGeometryCollection();

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        collection.addGeometry(p1);
        collection.addGeometry(p2);
        collection.addGeometry(p3);

        if (collection.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        SFLineString ls = new SFLineString();
        ls.addPoint(p1);
        ls.addPoint(p2);
        ls.addPoint(p3);

        collection.addGeometry(ls);

        System.out.println("collection geometryType() : " + collection.geometryType());
        System.out.println("collection geometryTypeId() : " + collection.geometryTypeId());
        System.out.println("collection asText() : " + collection.asText(0));
        System.out.println("collection numGeometries : " + collection.numGeometries());
        System.out.println("collection geometryN(1).geometryType() : "
                + collection.geometryN(1).geometryType());
        System.out.println("collection geometryN(1).asText() : "
                + collection.geometryN(1).asText(0));
        System.out.println("collection geometryN(1).address() : "
                + collection.geometryN(1).address());
        System.out.println("collection geometryN(3).geometryType() : "
                + collection.geometryN(3).geometryType());
        System.out.println("collection geometryN(3).pointN(1).asText() : "
                + new SFLineString(collection.geometryN(3)).pointN(1).asText(0));
        System.out.println("collection geometryN(3).pointN(1).address : "
                + new SFLineString(collection.geometryN(3)).pointN(1).address());
    }
}
