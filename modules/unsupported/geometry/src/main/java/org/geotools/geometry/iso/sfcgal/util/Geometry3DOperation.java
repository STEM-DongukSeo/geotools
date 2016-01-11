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
        
        private static OperationResource operationResource = null;
        
        private static void init(GeometryImpl gA, GeometryImpl gB, String initFrom) {
                if (operationResource == null) {
                        operationResource = new OperationResource(gA, gB, initFrom);
                }
        }

        private static void remove(String removeFrom) {
                if (operationResource != null
                                && operationResource.initFrom.equalsIgnoreCase(removeFrom)) {
                        operationResource = null;
                }
        }

        /**
         * *
         * 
         * @param geom
         * @return ConvexHull of the geometry
         */
        public static Geometry getConvexHull(GeometryImpl geom) {
                SFGeometry g = SFCGALConvertor.geometryToSFCGALGeometry((Geometry) geom);
                SFGeometry convex = SFAlgorithm.convexHull3D(g);

                return SFCGALConvertor.geometryFromSFCGALGeometry(convex);
        }

        /**
         * Compute the distance between two geometries using distance3D operation of SFCGAL
         * 
         * @param gA
         * @param gB
         * @return distance between two geometry objects
         */
        public static double distance(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "distance");
                double distance = SFAlgorithm.distance3D(operationResource.geometryA,
                                operationResource.geometryB);
                remove("distance");

                return distance;
        }

        public static boolean relate(GeometryImpl gA, GeometryImpl gB, String intersectionPatternMatrix) {
                init(gA, gB, "relate");
                IntersectionMatrix3D tIM = operationResource.getIntersectionMatrix();
                remove("relate");
                return tIM.matches(intersectionPatternMatrix);
        }

        /**
         * @return TRUE, if the gA is equal to gB
         */
        public static boolean equals(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "equals");
                boolean result = operationResource.geometryA.equals(operationResource.geometryB);
                remove("equals");

                return result;
        }

        /**
         * @return TRUE, if the gA is disjoint with gB
         */
        public static boolean disjoint(GeometryImpl gA, GeometryImpl gB) {
                return !intersects(gA, gB);
        }

        /**
         * @return TRUE, if the gA is intersect with gB
         */
        public static boolean intersects(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "intersects");
                boolean result = SFAlgorithm.intersects3D(operationResource.geometryA,
                                operationResource.geometryB);
                remove("intersects");

                return result;
        }

        /**
         * @return TRUE, if the gA touches gB.
         */
        public static boolean touches(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "touches");
                IntersectionMatrix3D tIM = operationResource.getIntersectionMatrix();

                boolean isTouches = false;
                if (tIM.matches("FT**") || tIM.matches("F*T*") || tIM.matches("F**T")) {
                        isTouches = true;
                }
                remove("touches");

                return isTouches;
        }

        /**
         * @return TRUE, if the gA is spatially contain gB.
         */
        public static boolean contains(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "contains");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                boolean result = !geometryA.equals(geometryB) && !touches(gA, gB)
                                && SFAlgorithm.covers3D(geometryA, geometryB);
                remove("contains");

                return result;
        }

        /**
         * @return TRUE, if the gA is spatially within gB.
         */
        public static boolean within(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "within");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                boolean result = !geometryA.equals(geometryB) && !touches(gB, gA)
                                && SFAlgorithm.covers3D(geometryB, geometryA);
                remove("within");

                return result;
        }

        /**
         * @return TRUE, if the gA overlaps with gB.
         */
        public static boolean overlaps(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "overlaps");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                SFGeometry intersection = operationResource.getIntersection();
                boolean result = false;
                
                if (equals(gA, gB))
                        result = false;
                else if (!intersection.isEmpty() && !contains(gA, gB) && !within(gA, gB)
                                && !touches(gA, gB)) {
                        if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                                if (!intersection.isEmpty() && intersection.dimension() == 1) {
                                        result = true;
                                }
                        } else
                                result = true;
                }
                remove("overlaps");

                return result;
        }

        /**
         * @return TRUE, if the gA crosses with gB.
         */
        public static boolean crosses(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "crosses");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                SFGeometry intersection = operationResource.getIntersection();
                boolean result = false;
                
                if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                        IntersectionMatrix3D tIM = operationResource.getIntersectionMatrix();

                        if (tIM.matches("0***")) {
                                result = true;
                        }

                        return result;
                } else if (!intersection.isEmpty() && !touches(gA, gB) && !contains(gA, gB)
                                && !within(gA, gB)) {
                        result = true;
                }
                remove("crosses");
                
                return result;
        }

        /**
         * Compute the union between two geometries using union3D operation of SFCGAL
         * 
         * @return union between two geometry objects
         */
        public static TransfiniteSet union(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "union");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                SFGeometry union  = SFAlgorithm.union3D(geometryA, geometryB);

                remove("union");
                return SFCGALConvertor.geometryFromSFCGALGeometry(union);
        }

        /**
         * Compute the union between two geometries using intersection3D operation of SFCGAL
         * 
         * @return intersection between two geometry objects
         */
        public static TransfiniteSet intersection(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "intersection");
                SFGeometry intersection = operationResource.getIntersection();

                remove("intersection");
                return SFCGALConvertor.geometryFromSFCGALGeometry(intersection);
        }

        /**
         * Compute the difference between two geometries using difference3D operation of SFCGAL
         * 
         * @return difference between two geometry objects
         */
        public static TransfiniteSet difference(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "difference");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                SFGeometry difference  = SFAlgorithm.difference3D(geometryA, geometryB);

                return SFCGALConvertor.geometryFromSFCGALGeometry(difference);
        }

        /**
         * Compute the symmetric Difference between two geometries using 3D operations of SFCGAL
         * 
         * @return symmetric Difference between two geometry objects
         */
        public static TransfiniteSet symmetricDifference(GeometryImpl gA, GeometryImpl gB) {
                init(gA, gB, "symmetricDifference");
                SFGeometry geometryA = operationResource.geometryA;
                SFGeometry geometryB = operationResource.geometryB;
                SFGeometry union  = SFAlgorithm.union3D(geometryA, geometryB);
                SFGeometry intersection = operationResource.getIntersection();
                SFGeometry symDifference  = SFAlgorithm.difference3D(union, intersection);

                remove("symmetricDifference");
                return SFCGALConvertor.geometryFromSFCGALGeometry(symDifference);
        }

        private static class OperationResource {
                private String initFrom = null;

                private GeometryImpl gA, gB;

                private SFGeometry geometryA = null;

                private SFGeometry geometryB = null;

                private IntersectionMatrix3D tIM = null;

                private SFGeometry intersection = null;

                public OperationResource(GeometryImpl gA, GeometryImpl gB, String initFrom) {
                        this.initFrom = initFrom;
                        this.gA = gA;
                        this.gB = gB;
                        geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                        geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                }

                public IntersectionMatrix3D getIntersectionMatrix() {
                        if (tIM == null) {
                                try {
                                        tIM = RelateOp3D.relate(gA, gB);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        return tIM;
                }

                public SFGeometry getIntersection() {
                        if (intersection == null) {
                                intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
                        }
                        return intersection;
                }
        }

}
>>>>>>> refs/remotes/origin/parsing_3d
