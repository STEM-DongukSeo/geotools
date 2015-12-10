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
@Platform(include = "cpp/SFPolygon.h")
public class SFPolygon extends SFSurface {
    static {
        Loader.load();
    }

    public SFPolygon() {
        allocate();
    }

    public SFPolygon(ArrayList<SFLineString> rings) {
        PointerVector vector = new PointerVector(rings.size());

        for (int i = 0; i < rings.size(); i++) {
            vector.get(i).put(rings.get(i));
        }

        allocate(vector);
    }

    public SFPolygon(SFLineString exteriorRing) {
        allocate(exteriorRing);
    }

    public SFPolygon(SFTriangle triangle) {
        allocate(triangle);
    }

    public SFPolygon(Pointer p) {
        super(p);
    }

    private native void allocate();

    private native void allocate(@ByRef PointerVector p);

    private native void allocate(@ByRef SFLineString exteriorRing);

    private native void allocate(@ByRef SFTriangle triangle);

    @Name("operator=")
    public native @ByRef SFPolygon assign(@ByRef SFPolygon polygon);

    public native SFPolygon clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native @Cast("bool") boolean isCounterClockWiseOriented();

    public native void reverse();

    public native @ByRef SFLineString exteriorRing();

    public native void setExteriorRing(@ByRef SFLineString exteriorRing);

    public native @Cast("bool") boolean hasInteriorRings();

    public native @Cast("size_t") int numInteriorRings();

    public native @ByRef SFLineString interiorRingN(@Cast("size_t") int n);

    public native @Cast("size_t") int numRings();

    public native @ByRef SFLineString ringN(@Cast("size_t") int n);

    public native void addInteriorRing(@ByRef SFLineString ls);

    public native void addRing(@ByRef SFLineString ls);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);
        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        SFLineString ls = new SFLineString(p1, p2);

        ls.addPoint(p3);
        ls.addPoint(p1);

        SFPolygon polygon = new SFPolygon(ls);

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p2);
        points.add(p3);
        points.add(p1);
        points.add(p2);
        SFLineString ls1 = new SFLineString(points);
        polygon.addInteriorRing(ls1);

        if (polygon.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        if (polygon.isCounterClockWiseOriented()) {
            System.out.println("counter clockwised");
        } else {
            System.out.println("not counterclockwised");
        }

        System.out.println("polygon coodinateDemension : " + polygon.coordinateDimension());
        System.out.println("geometryType : " + polygon.geometryType());
        System.out.println("numrings : " + polygon.numRings());
        System.out.println("interior numPoints : " + polygon.interiorRingN(0).numPoints());

        SFPolygon polygon2 = new SFPolygon(polygon);

        System.out.println(polygon2.geometryType());
    }
}
