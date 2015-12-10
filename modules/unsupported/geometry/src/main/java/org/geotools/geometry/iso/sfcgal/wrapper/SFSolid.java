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
@Platform(include = "cpp/SFSolid.h")
public class SFSolid extends SFGeometry {
    static {
        Loader.load();
    }

    public SFSolid() {
        allocate();
    }

    public SFSolid(ArrayList<SFPolyhedralSurface> shells) {
        PointerVector vector = new PointerVector(shells.size());

        for (int i = 0; i < shells.size(); i++) {
            vector.get(i).put(shells.get(i));
        }

        allocate(vector);
    }

    public SFSolid(Pointer p) {
        super(p);
    }

    public SFSolid(SFPolyhedralSurface exteriorShell) {
        allocate(exteriorShell);
    }

    private native void allocate();

    private native void allocate(@ByRef PointerVector p);

    private native void allocate(@ByRef SFPolyhedralSurface exteriorShell);

    @Name("operator=")
    public native @ByRef SFSolid assign(@ByRef SFSolid tr);

    public native SFSolid clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native @ByRef SFPolyhedralSurface exteriorShell();

    public native @Cast("size_t") int numInteriorShells();

    public native @ByRef SFPolyhedralSurface interiorShellN(@Cast("size_t") int n);

    public native void addInteriorShell(@ByRef SFPolyhedralSurface shell);

    public native @Cast("size_t") int numShells();

    public native @ByRef SFPolyhedralSurface shellN(@Cast("size_t") int n);

    public static SFSolid makeSolid(ArrayList<SFPoint> pointList) {
        SFPoint p1 = pointList.get(0);
        SFPoint p5 = pointList.get(4);
        SFPoint p2 = pointList.get(1);
        SFPoint p6 = pointList.get(5);
        SFPoint p3 = pointList.get(2);
        SFPoint p7 = pointList.get(6);
        SFPoint p4 = pointList.get(3);
        SFPoint p8 = pointList.get(7);
        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p4);
        ls1.addPoint(p3);
        ls1.addPoint(p2);
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
        ls4.addPoint(p6);
        ls4.addPoint(p5);
        ls4.addPoint(p1);
        ls4.addPoint(p2);
        ls4.addPoint(p6);
        ArrayList<SFLineString> rings = new ArrayList<SFLineString>();
        rings.add(ls4);

        SFLineString ls5 = new SFLineString();
        ls5.addPoint(p2);
        ls5.addPoint(p3);
        ls5.addPoint(p7);
        ls5.addPoint(p6);
        ls5.addPoint(p2);

        SFLineString ls6 = new SFLineString();
        ls6.addPoint(p4);
        ls6.addPoint(p1);
        ls6.addPoint(p5);
        ls6.addPoint(p8);
        ls6.addPoint(p4);

        SFPolygon polygon1 = new SFPolygon();
        polygon1.setExteriorRing(ls1);
        SFPolygon polygon2 = new SFPolygon(ls2);
        SFPolygon polygon3 = new SFPolygon(ls3);
        SFPolygon polygon4 = new SFPolygon(rings);
        SFPolygon polygon5 = new SFPolygon(ls5);
        SFPolygon polygon6 = new SFPolygon(ls6);

        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();
        polygons.add(polygon1);
        polygons.add(polygon2);
        polygons.add(polygon3);
        polygons.add(polygon4);
        polygons.add(polygon5);
        polygons.add(polygon6);

        SFPolyhedralSurface polyhedral = new SFPolyhedralSurface(polygons);
        return new SFSolid(polyhedral);
    }

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(0, 0, 0);
        SFPoint p2 = new SFPoint(0, -2, 0);
        SFPoint p3 = new SFPoint(2, -2, 0);
        SFPoint p4 = new SFPoint(2, 0, 0);
        SFPoint p5 = new SFPoint(0, 0, 2);
        SFPoint p6 = new SFPoint(0, -2, 2);
        SFPoint p7 = new SFPoint(2, -2, 2);
        SFPoint p8 = new SFPoint(2, 0, 2);

        ArrayList<SFPoint> points1 = new ArrayList<SFPoint>();
        points1.add(p1);
        points1.add(p2);
        points1.add(p3);
        points1.add(p4);
        points1.add(p5);
        points1.add(p6);
        points1.add(p7);
        points1.add(p8);

        SFPoint p11 = new SFPoint(4, 0, 0);
        SFPoint p12 = new SFPoint(4, -2, 0);
        SFPoint p13 = new SFPoint(6, -2, 0);
        SFPoint p14 = new SFPoint(6, 0, 0);
        SFPoint p15 = new SFPoint(4, 0, 2);
        SFPoint p16 = new SFPoint(4, -2, 2);
        SFPoint p17 = new SFPoint(6, -2, 2);
        SFPoint p18 = new SFPoint(6, 0, 2);

        ArrayList<SFPoint> points2 = new ArrayList<SFPoint>();
        points2.add(p11);
        points2.add(p12);
        points2.add(p13);
        points2.add(p14);
        points2.add(p15);
        points2.add(p16);
        points2.add(p17);
        points2.add(p18);

        SFPoint p19 = new SFPoint(5, 0, 0);
        SFPoint p20 = new SFPoint(5, -2, 0);
        SFPoint p21 = new SFPoint(7, -2, 0);
        SFPoint p22 = new SFPoint(7, 0, 0);
        SFPoint p23 = new SFPoint(5, 0, 1);
        SFPoint p24 = new SFPoint(5, -2, 1);
        SFPoint p25 = new SFPoint(7, -2, 1);
        SFPoint p26 = new SFPoint(7, 0, 1);

        ArrayList<SFPoint> points3 = new ArrayList<SFPoint>();
        points3.add(p19);
        points3.add(p20);
        points3.add(p21);
        points3.add(p22);
        points3.add(p23);
        points3.add(p24);
        points3.add(p25);
        points3.add(p26);

        SFSolid solid1 = SFSolid.makeSolid(points1);
        SFSolid solid2 = SFSolid.makeSolid(points2);
        SFSolid solid3 = SFSolid.makeSolid(points3);

        System.out.println("solid1.exteriorShell().polygon(0).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(0).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(1).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(1).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(2).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(2).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(3).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(3).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(4).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(4).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(5).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(5).exteriorRing().asText(0));

        // System.out.println("solid1 <-> solid2 distance : " + solid1.distance(solid2));
        System.out.println("solid2 <-> solid2 distance3D : " + solid1.distance3D(solid2));

        System.out.println("solid1.geometryType() : " + solid1.geometryType());
        System.out.println("solid1.geometryTypeId() : " + solid1.geometryTypeId());
        System.out.println("solid1.numGeometries() : " + solid1.numGeometries());
        System.out.println("solid1.numInteriorShells() : " + solid1.numInteriorShells());
        System.out.println("solid1.numShells() : " + solid1.numShells());
        System.out.println("solid1.geometryN(0).asText() : " + solid1.geometryN(0).asText(0));
        System.out.println("solid1.shellN(0).asText(0) : " + solid1.shellN(0).asText(0));

        //
        SFEnvelope envelope1 = solid1.envelope();
        System.out.println("envelope1.is3D() : " + envelope1.is3D());
        System.out.println("envelope1.xmin() : " + envelope1.xMin());
        System.out.println("envelope1.ymin() : " + envelope1.yMin());
        System.out.println("envelope1.zmin() : " + envelope1.zMin());
        System.out.println("envelope1.xmax() : " + envelope1.xMax());
        System.out.println("envelope1.ymax() : " + envelope1.yMax());
        System.out.println("envelope1.zmax() : " + envelope1.zMax());

        // Geometry boundary = solid1.boundary();
        // System.out.println("solid1 boundary.geometryType()" + boundary.geometryType());

        SFGeometry intersection = SFAlgorithm.intersection3D(solid2, solid3);
        System.out.println(intersection.asText(0));
        System.out.println(new SFSolid(intersection).exteriorShell().numPolygons());
    }
}
