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
import org.bytedeco.javacpp.annotation.StdString;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFCAPI.h")
public class SFCAPI {
    static {
        Loader.load();
    }

    public static native @ByRef SFGeometry SFCGAL_io_read_wkt(@ByRef @StdString String str,
            @Cast("size_t") int len);

    public static void SFCGAL_io_write_binary_prepared(SFPreparedGeometry geom, String buffer,
            int len) {
        System.out.println("call1");
        SFCGAL_io_write_binary_prepared(geom, buffer.toCharArray(), len);
        System.out.println("call2");
    }

    public static native void SFCGAL_io_write_binary_prepared(SFPreparedGeometry geom,
            @Cast("char *") char[] buffer, @Cast("size_t") int len);

    public static native @ByRef SFPreparedGeometry SFCGAL_io_read_binary_prepared(
            @StdString String str, @Cast("size_t") int len);

    public static native @ByRef SFPreparedGeometry SFCGAL_io_read_ewkt(@StdString String str,
            @Cast("size_t") int len);

    public static native @ByRef SFGeometry SFCGAL_geometry_force_lhr(@ByRef SFGeometry g);

    public static native @ByRef SFGeometry SFCGAL_geometry_force_rhr(@ByRef SFGeometry g);

    public static native @ByRef SFTriangulatedSurface SFCGAL_geometry_triangulate_2dz(
            @ByRef SFGeometry g);

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

        if (type == 1)
            return points1;
        return points2;
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

        ArrayList<SFPolygon> polygons1 = getPolygons(1);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons1)));

        ArrayList<SFPolygon> polygons2 = getPolygons(2);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons2)));

        return solids;
    }

    public static void test2D() {
        ArrayList<SFPolygon> polygons = getPolygons(1);

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

    public static void main(String[] args) {
        SFPolygon polygon = getPolygons(1).get(0);
        SFPreparedGeometry pg = new SFPreparedGeometry(polygon, 5);
        SFPolygon polygon2 = new SFPolygon(pg.geometry());

        String wkt = polygon2.asText();

        System.out.println("polygon2.asText() : " + wkt);
        SFGeometry g = SFCAPI.SFCGAL_io_read_wkt(wkt, wkt.length());
        System.out.println("g asText() : " + g.asText());
        /*
         * int len = 0; String binary = null; C_api.SFCGAL_io_write_binary_prepared(pg, binary, len); System.out.println("binary : " + binary);
         */
        String ewkt = pg.asEWKT();
        System.out.println("pg.asEWKT() : " + ewkt);
        SFPreparedGeometry pgFromEWKT = SFCAPI.SFCGAL_io_read_ewkt(ewkt, ewkt.length());
        System.out.println("pgFromEWKT.asEWKT() : " + pgFromEWKT.asEWKT());

        SFGeometry forcelhr = SFCAPI.SFCGAL_geometry_force_lhr(polygon);
        System.out.println("polygon1 forcelhr.asText() : " + forcelhr.asText());
        SFGeometry forcerhr = SFCAPI.SFCGAL_geometry_force_rhr(polygon);
        System.out.println("polygon1 forcerhr.asText() : " + forcerhr.asText());

        SFTriangulatedSurface surf = SFCAPI.SFCGAL_geometry_triangulate_2dz(polygon);
        System.out.println("polygon1 triangulated.asText() : " + surf.asText());

        SFPolygon polygon3 = getPolygons(1).get(1);
        surf = SFCAPI.SFCGAL_geometry_triangulate_2dz(polygon3);
        System.out.println("polygon2 triangulated.asText() : " + surf.asText(0));
    }
}
