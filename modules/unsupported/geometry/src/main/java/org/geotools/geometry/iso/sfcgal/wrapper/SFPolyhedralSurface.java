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
@Platform(include = "cpp/SFPolyhedralSurface.h")
public class SFPolyhedralSurface extends SFSurface {
    static {
        Loader.load();
    }

    public SFPolyhedralSurface() {
        allocate();
    }

    public SFPolyhedralSurface(ArrayList<SFPolygon> polygons) {
        PointerVector vector = new PointerVector(polygons.size());

        for (int i = 0; i < polygons.size(); i++) {
            vector.get(i).put(polygons.get(i));
        }

        allocate(vector);
    }

    public SFPolyhedralSurface(Pointer p) {
        super(p);
    }

    private native void allocate();

    private native void allocate(@ByRef PointerVector p);

    @Name("operator=")
    public native @ByRef SFPolyhedralSurface assign(@ByRef SFPolyhedralSurface tr);

    public native SFPolyhedralSurface clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native @ByRef SFTriangulatedSurface toTriangulatedSurface();

    public native @Cast("size_t") int numPolygons();

    public native @ByRef SFPolygon polygonN(@Cast("size_t") int n);

    public native void addPolygon(SFPolygon polygon);

    public native void addPolygons(@ByRef SFPolyhedralSurface other);

    public native @Cast("size_t") int numGeometries();

    public native @ByRef SFPolygon geometryN(@Cast("size_t") int n);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(0, 0, 0);
        SFPoint p2 = new SFPoint(0, -2, 0);
        SFPoint p3 = new SFPoint(2, -2, 0);
        SFPoint p4 = new SFPoint(2, 0, 0);
        SFPoint p5 = new SFPoint(0, 0, 2);
        SFPoint p6 = new SFPoint(0, -2, 2);
        SFPoint p7 = new SFPoint(2, -2, 2);
        SFPoint p8 = new SFPoint(2, 0, 2);
        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p2);
        ls1.addPoint(p3);
        ls1.addPoint(p4);
        ls1.addPoint(p1);

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p3);
        points.add(p4);
        points.add(p8);
        points.add(p7);
        points.add(p3);
        SFLineString ls2 = new SFLineString(points);

        SFLineString ls3 = new SFLineString();
        ls3.addPoint(p5);
        ls3.addPoint(p6);
        ls3.addPoint(p7);
        ls3.addPoint(p8);
        ls3.addPoint(p5);

        SFLineString ls4 = new SFLineString();
        ls4.addPoint(p2);
        ls4.addPoint(p1);
        ls4.addPoint(p5);
        ls4.addPoint(p6);
        ls4.addPoint(p2);
        ArrayList<SFLineString> rings = new ArrayList<SFLineString>();
        rings.add(ls4);

        SFPolygon polygon1 = new SFPolygon();
        polygon1.setExteriorRing(ls1);
        SFPolygon polygon2 = new SFPolygon(ls2);
        SFPolygon polygon3 = new SFPolygon(ls3);
        SFPolygon polygon4 = new SFPolygon(rings);

        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();
        polygons.add(polygon1);
        polygons.add(polygon2);
        polygons.add(polygon3);

        SFPolyhedralSurface polyhedral = new SFPolyhedralSurface(polygons);
        polyhedral.addPolygon(polygon4);

        System.out.println("polygon1 <-> polygon3 distance : " + polygon1.distance(polygon3));
        System.out.println("polygon1 <-> polygon3 distance3D : " + polygon1.distance3D(polygon3));
        // System.out.println("polygon1 <-> polygon2 distance : " + polygon1.distance(polygon2)); // polygon2(2D polygon) is invalid
        System.out.println("polygon1 <-> polygon2 distance3D : " + polygon1.distance3D(polygon2));

        System.out.println("PolyhedralSurface toString() : " + polyhedral.asText(0));
        System.out.println("PolyhedralSurface GeometryType() : " + polyhedral.geometryType());
        System.out.println("PolyhedralSurface numPolygons() : " + polyhedral.numPolygons());
        System.out.println("PolyhedralSurface polygonN(3).geometryType() : "
                + polyhedral.polygonN(3).geometryType());
        System.out.println("PolyhedralSurface polygonN(3).asText() : "
                + polyhedral.polygonN(3).asText(0));
        System.out.println("PolyhedralSurface polygonN(3).numInteriorRings() : "
                + polyhedral.polygonN(3).numInteriorRings());
        System.out.println("PolyhedralSurface polygonN(3).numRings() : "
                + polyhedral.polygonN(3).numRings());
        System.out.println("PolyhedralSurface polygonN(3).numGeometries() : "
                + polyhedral.polygonN(3).numGeometries());
        System.out.println("PolyhedralSurface geometryN(3).numGeometries() : "
                + polyhedral.geometryN(3).numGeometries());
        System.out.println("PolyhedralSurface geometryN(3).hasInterioRings() : "
                + polyhedral.geometryN(3).hasInteriorRings());
        System.out.println("PolyhedralSurface geometryN(0).hasInterioRings() : "
                + polyhedral.geometryN(0).hasInteriorRings());
        System.out.println("PolyhedralSurface geometryN(3).isCounterClockWiseOriented() : "
                + polyhedral.geometryN(3).isCounterClockWiseOriented());
        System.out.println("PolyhedralSurface geometryN(0).isCOunterClockWiseOriented() : "
                + polyhedral.geometryN(0).isCounterClockWiseOriented());
    }
}
