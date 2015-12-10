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

import java.util.ArrayList;

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
@Platform(include = "cpp/SFLineString.h")
public class SFLineString extends SFGeometry {
    static {
        Loader.load();
    }

    public SFLineString() {
        allocate();
    }

    public SFLineString(ArrayList<SFPoint> p) {
        PointerVector vector = new PointerVector(p.size());

        for (int i = 0; i < p.size(); i++) {
            vector.get(i).put(p.get(i));
        }

        allocate(vector);
    }

    public SFLineString(SFPoint startPoint, SFPoint endPoint) {
        allocate(startPoint, endPoint);
    }

    public SFLineString(Pointer p) {
        super(p);
    }

    private native void allocate();

    private native void allocate(@ByRef PointerVector p);

    private native void allocate(@ByRef SFPoint startPoint, @ByRef SFPoint endPoint);

    @Name("operator=")
    public native @ByRef SFLineString assign(@ByRef SFLineString ls);

    public native SFLineString clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native void clear();

    public native void reverse();

    public native @Cast("size_t") int numPoints();

    public native @Cast("size_t") int numSegments();

    public native @ByRef SFPoint pointN(@Cast("size_t") int n);

    public native @ByRef SFPoint startPoint();

    public native @ByRef SFPoint endPoint();

    public native void addPoint(SFPoint p);

    public native @Cast("bool") boolean isClosed();

    public native void reserve(@Cast("size_t") int n);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFLineString ls = new SFLineString(p1, p2);

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        ls.addPoint(p3);

        if (ls.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        if (ls.isClosed()) {
            System.out.println("closed");
        } else {
            System.out.println("not closed");
        }

        System.out.println("p3 coodinateDemension : " + ls.pointN(2).coordinateDimension());
        System.out.println("p3 demension : " + ls.pointN(2).dimension());
        System.out.println("p3 m : " + ls.pointN(2).m());

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p2);
        points.add(p3);
        points.add(p1);

        SFLineString ls1 = new SFLineString(points);
        System.out.println("p3 coodinateDemension : " + ls1.pointN(2).coordinateDimension());
        System.out.println("p3 demension : " + ls1.pointN(2).dimension());
        System.out.println("p3 m : " + ls1.pointN(2).m());

        System.out.println("ls1 numpoins : " + ls1.numPoints() + " ls1 numsegments : "
                + ls1.numSegments());

        //
        SFGeometry boundary = ls1.boundary();
        System.out.println("ls1 boundary type : " + boundary.geometryType());
        System.out.println("ls1 boundary numGeometries : " + boundary.numGeometries());

        SFEnvelope envelope = ls1.envelope();
        System.out.println("envelope.is3D() : " + envelope.is3D());
        System.out.println("envelope.xmin() : " + envelope.xMin());
        System.out.println("envelope.ymin() : " + envelope.yMin());
        System.out.println("envelope.zmin() : " + envelope.zMin());
        System.out.println("envelope.xmax() : " + envelope.xMax());
        System.out.println("envelope.ymax() : " + envelope.yMax());
        System.out.println("envelope.zmax() : " + envelope.zMax());
        //

        System.out.println("ls1.clear");
        ls1.clear();
        System.out.println("ls1 numpoins : " + ls1.numPoints() + " ls1 numsegments : "
                + ls1.numSegments());

    }
}
