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
import org.bytedeco.javacpp.annotation.Platform;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFTriangulate.h")
public class SFTriangulate {
    static {
        Loader.load();
    }

    public static native void triangulatePolygon3D(@ByRef SFGeometry g,
            @ByRef SFTriangulatedSurface triangulateSurface);

    public static void testTriangulate() {
        SFSolid solid = SFAlgorithm.getSolids().get(0);

        SFPoint p4 = new SFPoint(0.5, -1.5, 2);
        SFPoint p6 = new SFPoint(2, -1.5, 2);
        SFPoint p23 = new SFPoint(2, -1.5, 0.5);
        SFPoint p24 = new SFPoint(2, -0.5, 0.5);
        SFPoint p9 = new SFPoint(2, -0.5, 2);
        SFPoint p7 = new SFPoint(0.5, -0.5, 2);

        ArrayList<SFPoint> positions4 = new ArrayList<SFPoint>();
        positions4.add(p4);
        positions4.add(p6);
        positions4.add(p23);
        positions4.add(p24);
        positions4.add(p9);
        positions4.add(p7);
        positions4.add(p4);
        SFLineString exteriorRing = new SFLineString(positions4);
        SFPolygon polygon = new SFPolygon(exteriorRing);
        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();
        polygons.add(polygon);

        SFTriangulatedSurface triangulatedSurface = new SFTriangulatedSurface();
        triangulatePolygon3D(polygon, triangulatedSurface);
        System.out.println(triangulatedSurface.asText(1));
        /*
         * PolyhedralSurface polyhedral = new PolyhedralSurface(polygons);
         * 
         * Geometry intersection = Algorithm.intersection3D(solid, polyhedral); System.out.println(intersection.asText(1));
         */
    }

    public static void main(String[] args) {
        testTriangulate();
    }
}
