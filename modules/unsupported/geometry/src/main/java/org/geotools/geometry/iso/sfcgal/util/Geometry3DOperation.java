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
package org.geotools.geometry.iso.sfcgal.util;

import org.geotools.geometry.iso.root.GeometryImpl;
import org.geotools.geometry.iso.sfcgal.relate.IntersectionMatrix3D;
import org.geotools.geometry.iso.sfcgal.relate.RelateOp3D;
import org.geotools.geometry.iso.sfcgal.wrapper.SFAlgorithm;
import org.geotools.geometry.iso.sfcgal.wrapper.SFGeometry;
import org.geotools.geometry.iso.sfcgal.wrapper.SFPoint;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.TransfiniteSet;

/**
 * @author Donguk Seo
 *
 */
public class Geometry3DOperation {

    public static double distance(Geometry gA, Geometry gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

        return SFAlgorithm.distance3D(geometryA, geometryB);
    }

    public static Geometry getConvexHull(GeometryImpl GeometryImpl) {
        SFGeometry g = SFCGALConvertor.geometryToSFCGALGeometry(GeometryImpl);
        SFGeometry convex = SFAlgorithm.convexHull3D(g);

        return SFCGALConvertor.geometryFromSFCGALGeometry(convex);
    }

    public static boolean relate(GeometryImpl gA, GeometryImpl gB, String intersectionPatternMatrix) {
        return false;
    }

    public static boolean equals(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

        return geometryA.equals(geometryB);
    }

    public static boolean disjoint(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

        return !SFAlgorithm.intersects3D(geometryA, geometryB);
    }

    public static boolean intersects(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

        return SFAlgorithm.intersects3D(geometryA, geometryB);
    }

    public static boolean touches(GeometryImpl gA, GeometryImpl gB) {
        IntersectionMatrix3D tIM = null;
        try {
            tIM = RelateOp3D.relate(gA, gB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean rValue = false;
        if (tIM.matches("FT**") || tIM.matches("F*T*") || tIM.matches("F**T")) {
            rValue = true;
        }

        return rValue;
    }

    public static boolean contains(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

        return !geometryA.equals(geometryB) && !touches(gA, gB)
                && SFAlgorithm.covers3D(geometryA, geometryB);
    }

    public static boolean within(GeometryImpl gA, GeometryImpl gB) {
        return contains(gB, gA);
    }

    public static boolean overlaps(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
        boolean rValue = false;

        if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
            if (intersection.dimension() == 1) {
                rValue = true;
            }
        } else if (!intersection.isEmpty() && !contains(gA, gB) && !contains(gB, gA)
                && !touches(gA, gB)) {
            rValue = true;
        }

        return rValue;
    }

    public static boolean crosses(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
        boolean rValue = false;

        if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
            if (intersection.dimension() == 0) {
                rValue = true;
            }
        } else if (!intersection.isEmpty() && !touches(gA, gB) && !contains(gA, gB)
                && !contains(gB, gA)) {
            rValue = true;
        }

        return rValue;
    }

    public static TransfiniteSet union(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry union = SFAlgorithm.union3D(geometryA, geometryB);

        return SFCGALConvertor.geometryFromSFCGALGeometry(union);
    }

    public static TransfiniteSet intersection(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
        SFGeometry boundary = intersection.boundary();
        System.out.println("boundary : " + boundary.asText(1));

        return SFCGALConvertor.geometryFromSFCGALGeometry(intersection);
    }

    public static TransfiniteSet difference(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry difference = SFAlgorithm.difference3D(geometryA, geometryB);

        return SFCGALConvertor.geometryFromSFCGALGeometry(difference);
    }

    public static TransfiniteSet symmetricDifference(GeometryImpl gA, GeometryImpl gB) {
        SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
        SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
        SFGeometry union = SFAlgorithm.union3D(geometryA, geometryB);
        SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
        SFGeometry symDifference = SFAlgorithm.difference3D(union, intersection);

        return SFCGALConvertor.geometryFromSFCGALGeometry(symDifference);
    }
}
