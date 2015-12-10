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
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Platform;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFAlgorithm.h")
public class SFAlgorithm {
    static {
        Loader.load();
    }

    public static native double area(@ByRef SFGeometry g);

    public static native double area3D(@ByRef SFGeometry g);

    public static native @ByRef SFGeometry convexHull(@ByRef SFGeometry g);

    public static native @ByRef SFGeometry convexHull3D(@ByRef SFGeometry g);

    public static native @Cast("bool") boolean covers(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @Cast("bool") boolean covers3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @ByRef SFGeometry difference(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @ByRef SFGeometry difference3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native double distance(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native double distance3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @ByRef SFGeometry extrude(@ByRef SFGeometry g, double dx, double dy,
            double dz);

    public static native @ByRef SFGeometry intersection(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @ByRef SFGeometry intersection3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @Cast("bool") boolean intersects(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @Cast("bool") boolean intersects3D(@ByRef SFGeometry gA,
            @ByRef SFGeometry gB);

    // public static Validity isValid(Geometry g) { return isValid(g, 1e-9); }
    // public static native Validity isValid(@ByRef Geometry g, double toleranceAbs);
    public static native @ByRef SFGeometry minkowskiSum(@ByRef SFGeometry g,
            @ByRef SFPolygon polygon);

    public static native @ByRef SFMultiPolygon offset(@ByRef SFGeometry g, double r);

    public static native @Cast("bool") boolean hasPlane3D(@ByRef SFPolygon polygon);

    public static SFMultiLineString straightSkeleton(SFGeometry g) {
        return straightSkeleton(g, true);
    }

    public static native @ByRef SFMultiLineString straightSkeleton(@ByRef SFGeometry g,
            @Cast("bool") boolean autoOrientation);

    public static native @ByRef SFGeometry tesselate(@ByRef SFGeometry g);

    public static native @ByRef SFGeometry union_(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native @ByRef SFGeometry union3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

    public static native double volume(@ByRef SFGeometry g);

    public static ArrayList<SFPoint> getPoints(int type) {
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

        //
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

        //
        SFPoint p19 = new SFPoint(5, 0, 0);
        SFPoint p20 = new SFPoint(5, -2, 0);
        SFPoint p21 = new SFPoint(7, -2, 0);
        SFPoint p22 = new SFPoint(7, 0, 0);
        SFPoint p23 = new SFPoint(5, 0, 2);
        SFPoint p24 = new SFPoint(5, -2, 2);
        SFPoint p25 = new SFPoint(7, -2, 2);
        SFPoint p26 = new SFPoint(7, 0, 2);

        ArrayList<SFPoint> points3 = new ArrayList<SFPoint>();
        points3.add(p19);
        points3.add(p20);
        points3.add(p21);
        points3.add(p22);
        points3.add(p23);
        points3.add(p24);
        points3.add(p25);
        points3.add(p26);

        SFPoint p27 = new SFPoint(2, 0, 0);
        SFPoint p28 = new SFPoint(2, -2, 0);
        SFPoint p29 = new SFPoint(4, -2, 0);
        SFPoint p30 = new SFPoint(4, 0, 0);
        SFPoint p31 = new SFPoint(2, 0, 2);
        SFPoint p32 = new SFPoint(2, -2, 2);
        SFPoint p33 = new SFPoint(4, -2, 2);
        SFPoint p34 = new SFPoint(4, 0, 2);

        ArrayList<SFPoint> points4 = new ArrayList<SFPoint>();
        points4.add(p27);
        points4.add(p28);
        points4.add(p29);
        points4.add(p30);
        points4.add(p31);
        points4.add(p32);
        points4.add(p33);
        points4.add(p34);

        SFPoint p35 = new SFPoint(0.5, -0.5, 0.5);
        SFPoint p36 = new SFPoint(0.5, -1.5, 0.5);
        SFPoint p37 = new SFPoint(1.5, -1.5, 0.5);
        SFPoint p38 = new SFPoint(1.5, -0.5, 0.5);
        SFPoint p39 = new SFPoint(0.5, -0.5, 1.5);
        SFPoint p40 = new SFPoint(0.5, -1.5, 1.5);
        SFPoint p41 = new SFPoint(1.5, -1.5, 1.5);
        SFPoint p42 = new SFPoint(1.5, -0.5, 1.5);

        ArrayList<SFPoint> points5 = new ArrayList<SFPoint>();
        points5.add(p35);
        points5.add(p36);
        points5.add(p37);
        points5.add(p38);
        points5.add(p39);
        points5.add(p40);
        points5.add(p41);
        points5.add(p42);

        SFPoint p43 = new SFPoint(2, 0, 2);
        SFPoint p44 = new SFPoint(2, -2, 2);
        SFPoint p45 = new SFPoint(4, -2, 2);
        SFPoint p46 = new SFPoint(4, 0, 2);
        SFPoint p47 = new SFPoint(2, 0, 4);
        SFPoint p48 = new SFPoint(2, -2, 4);
        SFPoint p49 = new SFPoint(4, -2, 4);
        SFPoint p50 = new SFPoint(4, 0, 4);

        ArrayList<SFPoint> points6 = new ArrayList<SFPoint>();
        points6.add(p43);
        points6.add(p44);
        points6.add(p45);
        points6.add(p46);
        points6.add(p47);
        points6.add(p48);
        points6.add(p49);
        points6.add(p50);

        SFPoint p51 = new SFPoint(1, -1, 0);
        SFPoint p52 = new SFPoint(1, -2, 0);
        SFPoint p53 = new SFPoint(2, -2, 0);
        SFPoint p54 = new SFPoint(2, -1, 0);
        SFPoint p55 = new SFPoint(1, -1, 1);
        SFPoint p56 = new SFPoint(1, -2, 1);
        SFPoint p57 = new SFPoint(2, -2, 1);
        SFPoint p58 = new SFPoint(2, -1, 1);

        ArrayList<SFPoint> points7 = new ArrayList<SFPoint>();
        points7.add(p51);
        points7.add(p52);
        points7.add(p53);
        points7.add(p54);
        points7.add(p55);
        points7.add(p56);
        points7.add(p57);
        points7.add(p58);

        if (type == 0)
            return points1;
        else if (type == 1)
            return points2;
        else if (type == 2)
            return points3;
        else if (type == 3)
            return points4;
        else if (type == 4)
            return points5;
        else if (type == 5)
            return points6;
        else if (type == 6)
            return points7;

        return points7;
    }

    public static ArrayList<SFLineString> getLineStrings(int type) {
        ArrayList<SFPoint> points = getPoints(type);
        ArrayList<SFLineString> lineStrings = new ArrayList<SFLineString>();

        SFPoint p1 = points.get(0);
        SFPoint p5 = points.get(4);
        SFPoint p2 = points.get(1);
        SFPoint p6 = points.get(5);
        SFPoint p3 = points.get(2);
        SFPoint p7 = points.get(6);
        SFPoint p4 = points.get(3);
        SFPoint p8 = points.get(7);

        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p4);
        ls1.addPoint(p3);
        ls1.addPoint(p2);
        ls1.addPoint(p1);

        ArrayList<SFPoint> pointList = new ArrayList<SFPoint>();
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p8);
        pointList.add(p7);
        pointList.add(p3);
        SFLineString ls2 = new SFLineString(pointList);

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

        lineStrings.add(ls1);
        lineStrings.add(ls2);
        lineStrings.add(ls3);
        lineStrings.add(ls4);
        lineStrings.add(ls5);
        lineStrings.add(ls6);

        return lineStrings;
    }

    public static ArrayList<SFPolygon> getPolygons(int type) {
        ArrayList<SFLineString> lineStrings = getLineStrings(type);
        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();

        for (int i = 0; i < lineStrings.size(); i++) {
            polygons.add(new SFPolygon(lineStrings.get(i)));
        }

        return polygons;
    }

    public static ArrayList<SFSolid> getSolids() {
        ArrayList<SFSolid> solids = new ArrayList<SFSolid>();

        ArrayList<SFPolygon> polygons1 = getPolygons(0);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons1)));

        ArrayList<SFPolygon> polygons2 = getPolygons(1);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons2)));

        ArrayList<SFPolygon> polygons3 = getPolygons(2);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons3)));

        ArrayList<SFPolygon> polygons4 = getPolygons(3);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons4)));

        ArrayList<SFPolygon> polygons5 = getPolygons(4);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons5)));

        ArrayList<SFPolygon> polygons6 = getPolygons(5);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons6)));

        ArrayList<SFPolygon> polygons7 = getPolygons(6);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons7)));

        return solids;
    }

    public static void test2D() {
        ArrayList<SFPolygon> polygons = getPolygons(0);

        SFPolygon polygon1 = polygons.get(0);
        SFPolygon polygon2 = polygons.get(1);
        SFPolygon polygon3 = polygons.get(2);

        System.out.println("polygon1 area() : " + SFAlgorithm.area(polygon1));

        SFGeometry convexhull = SFAlgorithm.convexHull(polygon1);
        System.out.println("convexhull geometryType() : " + convexhull.geometryType());

        System.out.println("polygon1 distance() : " + SFAlgorithm.distance(polygon1, polygon3));

        SFGeometry extrudee = SFAlgorithm.extrude(polygon1, 2, -2, 2);
        System.out.println("extrude geometryType() : " + extrudee.geometryType());
        SFSolid extrude = new SFSolid(extrudee);
        System.out.println("extrude numShells() : " + extrude.numShells());
        System.out.println("extrude exteriorShell().numPolygons() : "
                + extrude.exteriorShell().numPolygons());
        System.out.println("extrude asText() : " + extrude.asText(0));

        SFPoint p1 = new SFPoint(1.0, 0, 0);
        SFPoint p2 = new SFPoint(1.0, 1.0, 0);
        SFPoint p3 = new SFPoint(3.0, 1.0, 0);
        SFPoint p4 = new SFPoint(3.0, 0, 0);
        SFLineString intersectionLS = new SFLineString();
        intersectionLS.addPoint(p1);
        intersectionLS.addPoint(p2);
        intersectionLS.addPoint(p3);
        intersectionLS.addPoint(p4);
        intersectionLS.addPoint(p1);
        SFPolygon intersectionPolygon = new SFPolygon(intersectionLS);
        SFGeometry intersection = SFAlgorithm.intersection(polygon1, intersectionPolygon);
        System.out.println("intersection geometryType() : " + intersection.geometryType());

        System.out.println("polygon1 <-> polygon3 intersects() : "
                + SFAlgorithm.intersects(polygon1, polygon3));
        System.out.println("polygon1 <-> intersectionPolygon intersects() : "
                + SFAlgorithm.intersects(polygon1, intersectionPolygon));

        System.out.println("polygon1 asText() : " + polygon1.asText(0));
        System.out.println("polygon1 hasPlane3D() : " + SFAlgorithm.hasPlane3D(polygon1));
        System.out.println("polygon2 asText() : " + polygon2.asText(0));
        System.out.println("polygon2 hasPlane3D() : " + SFAlgorithm.hasPlane3D(polygon2));

        SFMultiLineString skeleton = SFAlgorithm.straightSkeleton(polygon1);
        System.out.println("polygon1 skeleton numGeometries() " + skeleton.numGeometries());
        System.out.println("polygon1 skeleton numGeometries() " + skeleton.asText(0));
    }

    public static void test3D() {
        SFSolid solid1 = getSolids().get(0);

        System.out.println("Solid1 volume : " + SFAlgorithm.volume(solid1));
        System.out.println("solid1 asText() : " + solid1.asText(0));

        SFSolid solid2 = getSolids().get(1);
        SFSolid solid3 = getSolids().get(2);
        SFPolygon polygon = getPolygons(2).get(2);

        SFGeometry intersection = SFAlgorithm.intersection3D(solid2, solid3);
        System.out.println(intersection.asText(0));
        System.out.println(intersection.numGeometries());

        SFPolygon poly1 = SFAlgorithm.getPolygons(1).get(0);
        SFPolygon poly2 = SFAlgorithm.getPolygons(2).get(0);
        SFGeometry difference = SFAlgorithm.difference3D(poly1, poly2);
        System.out.println(poly1.asText(0));
        System.out.println(poly2.asText(0));
        System.out.println(difference.asText(0));

        System.out.println("aa");
        System.out.println(SFAlgorithm.covers3D(solid3.exteriorShell().polygonN(2), polygon));
        System.out.println(solid3.exteriorShell().polygonN(2).asText(0));
        System.out.println(polygon.asText(0));

    }

    public static void testSolid() {
        SFSolid solid1 = getSolids().get(0);
        SFSolid solid2 = getSolids().get(3);
        SFSolid solid3 = getSolids().get(1);
        SFSolid solid4 = getSolids().get(2);
        SFSolid solid5 = getSolids().get(4);
        SFSolid solid6 = getSolids().get(5);
        SFSolid solid7 = getSolids().get(6);

        SFPolyhedralSurface shell1 = solid1.exteriorShell();
        SFPolyhedralSurface shell2 = solid2.exteriorShell();
        SFPolyhedralSurface shell3 = solid3.exteriorShell();
        SFPolyhedralSurface shell4 = solid4.exteriorShell();
        SFPolyhedralSurface shell5 = solid5.exteriorShell();
        SFPolyhedralSurface shell6 = solid6.exteriorShell();
        SFPolyhedralSurface shell7 = solid7.exteriorShell();

        // Geometry intersectionII = (new Solid(intersection3D(solid3, solid4))).exteriorShell().toTriangulatedSurface();
        SFGeometry intersectionII = intersection3D(solid7, solid1);
        SFGeometry intersectionBI = intersection3D(shell7, solid1);
        SFGeometry intersectionIB = intersection3D(solid7, shell1);
        SFGeometry intersectionBB = intersection3D(shell7, shell1);

        System.out.println("solid1 : " + solid1.asText(1));
        System.out.println("solid2 : " + solid2.asText(1));
        System.out.println("solid3 : " + solid3.asText(1));
        System.out.println("solid4 : " + solid4.asText(1));
        System.out.println("solid5 : " + solid5.asText(1));
        System.out.println("solid6 : " + solid6.asText(1));
        System.out.println("solid7 : " + solid7.asText(1));

        System.out.println("shell1 : " + shell1.asText(1));
        System.out.println("shell2 : " + shell2.asText(1));
        System.out.println("shell3 : " + shell3.asText(1));
        System.out.println("shell4 : " + shell4.asText(1));
        System.out.println("shell5 : " + shell5.asText(1));
        System.out.println("shell6 : " + shell6.asText(1));
        System.out.println("shell7 : " + shell7.asText(1));

        System.out.println("------------ intersection ------------");
        System.out.println(intersectionII.asText(1));
        System.out.println(intersectionIB.asText(1));
        System.out.println(intersectionBI.asText(1));
        System.out.println(intersectionBB.asText(1));

        System.out.println(intersectionII.equals(intersectionIB));
        System.out.println(intersectionII.equals(intersectionBI));
        System.out.println(intersectionIB.equals(intersectionBB));

        System.out.println(intersectionII.dimension());
        System.out.println(intersectionIB.dimension());
        System.out.println(intersectionBI.dimension());
        System.out.println(intersectionBB.dimension());

        System.out.println(covers3D(solid1, solid1));

    }

    public static void testLineStringPolygon() {
        SFSolid solid1 = getSolids().get(0);
        SFPoint p1 = new SFPoint(2, 0, 0);
        SFPoint p2 = new SFPoint(4, 0, 0);
        SFLineString ls1 = new SFLineString(p1, p2);
        SFGeometry boundary = ls1.boundary();

        System.out.println(boundary.asText(1));
        System.out.println(intersects3D(solid1, boundary));
    }

    public static void main(String[] args) {
        /*
         * Point p = new Point(3.0, 5.0); Point p1 = new Point(0.0, 0.0); Point p2 = new Point(2.0, 0.0); Point p3 = new Point(4.0, 0.0);
         * 
         * LineString ls1 = new LineString(); ls1.addPoint(p1); ls1.addPoint(p2); ls1.addPoint(p3);
         * 
         * Point p4 = new Point(2.0, 3.0); Point p5 = new Point(4.0, 3.0); Point p6 = new Point(5.0, 3.0);
         * 
         * 
         * LineString ls2 = new LineString(); ls2.addPoint(p4); ls2.addPoint(p5); ls2.addPoint(p6);
         * 
         * System.out.println("p <-> ls1 distance : " + Algorithm.distance(p, ls1)); System.out.println("ls1 <-> ls2 distance : " +
         * Algorithm.distance(ls1, ls2)); System.out.println("ls1 geometryType : " + ls1.geometryType()); Geometry ls_geom = (Geometry)ls1;
         * System.out.println("ls_geom.geometryType() : " + ls_geom.geometryType());
         * 
         * Geometry convexhull = Algorithm.convexHull(ls1); System.out.println("ls1 convexhull geometry type : " + convexhull.geometryType());
         * LineString convex_ls = new LineString(convexhull); System.out.println("convexhull numPoints() : " + convex_ls.numPoints());
         * 
         * 
         * 
         * LineString exteriorRing = new LineString(ls1); exteriorRing.addPoint(p4); exteriorRing.addPoint(p5);
         * 
         * Polygon polygon = new Polygon(); polygon.setExteriorRing(exteriorRing); Geometry poly_geom = (Geometry)polygon;
         * System.out.println("poly_geom.geometryType() : " + poly_geom.geometryType());
         * 
         * convexhull = Algorithm.convexHull(polygon); System.out.println("ls1 convexhull geometry type : " + convexhull.geometryType()); Polygon
         * convex_poly = new Polygon(convexhull); System.out.println("convexhull numPoints() : " + convex_poly.exteriorRing().numPoints());
         */

        // test2D();
        // test3D();
        // testSolid();
        testLineStringPolygon();
    }
}
