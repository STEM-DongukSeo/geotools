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
import org.opengis.geometry.Geometry;
import org.opengis.geometry.TransfiniteSet;

/**
 * @author Donguk Seo
 *
 */
public class Geometry3DOperation {

        /**
         * Computes the distance between two geometries using distance3D operation of SFCGAL
         * @param gA
         * @param gB
         * @return distance between two geometry objects
         */
        public static double distance(Geometry gA, Geometry gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

                return SFAlgorithm.distance3D(geometryA, geometryB);
        }

        /**
         * @param geom
         * @return ConvexHull of geometry
         */
        public static Geometry getConvexHull(GeometryImpl geom) {
                SFGeometry g = SFCGALConvertor.geometryToSFCGALGeometry((Geometry) geom);
                SFGeometry convex = SFAlgorithm.convexHull3D(g);

                return SFCGALConvertor.geometryFromSFCGALGeometry(convex);
        }

        public static boolean relate(GeometryImpl gA, GeometryImpl gB,
                        String intersectionPatternMatrix) {
                return false;
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA is equal to gB
         */
        public static boolean equals(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

                return geometryA.equals(geometryB);
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA is disjoint with gB
         */
        public static boolean disjoint(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

                return !SFAlgorithm.intersects3D(geometryA, geometryB);
        }

        
        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA is intersect with gB
         */
        public static boolean intersects(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

                return SFAlgorithm.intersects3D(geometryA, geometryB);
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA touches gB.
         */
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

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA is spatially contain gB.
         */
        public static boolean contains(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);

                return !geometryA.equals(geometryB) && !touches(gA, gB)
                                && SFAlgorithm.covers3D(geometryA, geometryB);
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA is spatially within gB.
         */
        public static boolean within(GeometryImpl gA, GeometryImpl gB) {
                return contains(gB, gA);
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA overlaps with gB.
         */
        public static boolean overlaps(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
                boolean rValue = false;

                if (equals(gA, gB))
                        return rValue;
                if (!intersection.isEmpty() && !contains(gA, gB) && !contains(gB, gA)
                                && !touches(gA, gB)) {
                        if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                                if (!intersection.isEmpty() && intersection.dimension() == 1) {
                                        rValue = true;
                                }
                        }
                        rValue = true;
                }

                return rValue;
        }

        /**
         * @param gA
         * @param gB
         * @return TRUE, if the gA crosses with gB.
         */
        public static boolean crosses(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
                boolean rValue = false;

                if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                        IntersectionMatrix3D tIM = null;
                        try {
                                tIM = RelateOp3D.relate(gA, gB);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                        if (tIM.matches("0***")) {
                                rValue = true;
                        }

                        return rValue;
                } else if (!intersection.isEmpty() && !touches(gA, gB) && !contains(gA, gB)
                                && !contains(gB, gA)) {
                        rValue = true;
                }

                return rValue;
        }

        /**
         * Computes the union between two geometries using union3D operation of SFCGAL
         * @param gA
         * @param gB
         * @return union between two geometry objects
         */
        public static TransfiniteSet union(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry union = SFAlgorithm.union3D(geometryA, geometryB);

                return SFCGALConvertor.geometryFromSFCGALGeometry(union);
        }

        /**
         * Computes the union between two geometries using intersection3D operation of SFCGAL
         * @param gA
         * @param gB
         * @return intersection between two geometry objects
         */
        public static TransfiniteSet intersection(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);

                return SFCGALConvertor.geometryFromSFCGALGeometry(intersection);
        }

        /**
         * Computes the difference between two geometries using difference3D operation of SFCGAL
         * @param gA
         * @param gB
         * @return difference between two geometry objects
         */
        public static TransfiniteSet difference(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry difference = SFAlgorithm.difference3D(geometryA, geometryB);

                return SFCGALConvertor.geometryFromSFCGALGeometry(difference);
        }

        /**
         * Computes the symmetric Difference between two geometries using 3D operations of SFCGAL
         * @param gA
         * @param gB
         * @return symmetric Difference between two geometry objects
         */
        public static TransfiniteSet symmetricDifference(GeometryImpl gA, GeometryImpl gB) {
                SFGeometry geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                SFGeometry geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                SFGeometry union = SFAlgorithm.union3D(geometryA, geometryB);
                SFGeometry intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
                SFGeometry symDifference = SFAlgorithm.difference3D(union, intersection);

                return SFCGALConvertor.geometryFromSFCGALGeometry(symDifference);
        }
}
