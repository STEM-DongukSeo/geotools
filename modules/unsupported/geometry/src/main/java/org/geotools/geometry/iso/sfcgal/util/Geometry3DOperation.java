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

        private GeometryImpl gA, gB;

        private Geometry unionGeometry = null;

        private Geometry differenceGeometry = null;

        private Geometry symDifferenceGeometry = null;

        private Geometry intersectionGeometry = null;

        private SFGeometry geometryA = null;

        private SFGeometry geometryB = null;

        private IntersectionMatrix3D tIM = null;

        private SFGeometry intersection = null;

        private SFGeometry union = null;

        private SFGeometry difference = null;

        private boolean calledEquals = false;

        private boolean calledIntersects = false;

        private boolean calledTouches = false;

        private boolean calledContains = false;

        private boolean calledWithin = false;

        private boolean calledCrosses = false;

        private boolean calledOverlaps = false;

        private boolean isEquals = false;

        private boolean isIntersects = false;

        private boolean isTouches = false;

        private boolean isContains = false;

        private boolean isWithin = false;

        private boolean isCrosses = false;

        private boolean isOverlaps = false;


        /**
         * @param geom
         * @return ConvexHull of the geometry
         */
        public static Geometry getConvexHull(GeometryImpl geom) {
                SFGeometry g = SFCGALConvertor.geometryToSFCGALGeometry((Geometry) geom);
                SFGeometry convex = SFAlgorithm.convexHull3D(g);

                return SFCGALConvertor.geometryFromSFCGALGeometry(convex);
        }
        
        public Geometry3DOperation(GeometryImpl gA, GeometryImpl gB) {
                this.gA = gA;
                this.gB = gB;
                geometryA = SFCGALConvertor.geometryToSFCGALGeometry(gA);
                geometryB = SFCGALConvertor.geometryToSFCGALGeometry(gB);
                intersection = SFAlgorithm.intersection3D(geometryA, geometryB);
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

        /**
         * Compute the distance between two geometries using distance3D operation of SFCGAL
         * 
         * @param gA
         * @param gB
         * @return distance between two geometry objects
         */
        public double distance() {
                return SFAlgorithm.distance3D(geometryA, geometryB);
        }


        public boolean relate(String intersectionPatternMatrix) {
                IntersectionMatrix3D tIM = getIntersectionMatrix();
                return tIM.matches(intersectionPatternMatrix);
        }

        /**
         * @return TRUE, if the gA is equal to gB
         */
        public boolean equals() {
                if (!calledEquals) {
                        calledEquals = true;
                        isEquals = geometryA.equals(geometryB);
                }
                return isEquals;
        }

        /**
         * @return TRUE, if the gA is disjoint with gB
         */
        public boolean disjoint() {
                if (!calledIntersects) {
                        calledIntersects = true;
                        isIntersects = SFAlgorithm.intersects3D(geometryA, geometryB);
                }
                return isIntersects;
        }

        /**
         * @return TRUE, if the gA is intersect with gB
         */
        public boolean intersects() {
                return !this.intersects();
        }

        /**
         * @return TRUE, if the gA touches gB.
         */
        public boolean touches() {
                if (!calledTouches) {
                        calledTouches = true;
                        IntersectionMatrix3D tIM = getIntersectionMatrix();

                        isTouches = false;
                        if (tIM.matches("FT**") || tIM.matches("F*T*") || tIM.matches("F**T")) {
                                isTouches = true;
                        }
                }
                return isTouches;
        }

        /**
         * @return TRUE, if the gA is spatially contain gB.
         */
        public boolean contains() {
                if (!calledContains) {
                        calledContains = true;
                        isContains = !equals() && !touches()
                                        && SFAlgorithm.covers3D(geometryA, geometryB);
                }
                return isContains;
        }

        /**
         * @return TRUE, if the gA is spatially within gB.
         */
        public boolean within() {
                if (!calledWithin) {
                        calledWithin = true;
                        isWithin = !equals() && !touches()
                                        && SFAlgorithm.covers3D(geometryB, geometryA);
                }
                return isWithin;
        }

        /**
         * @return TRUE, if the gA overlaps with gB.
         */
        public boolean overlaps() {
                if (!calledOverlaps) {
                        calledOverlaps = true;
                        if (equals())
                                isOverlaps = false;
                        else if (!intersection.isEmpty() && !contains() && !within() && !touches()) {
                                if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                                        if (!intersection.isEmpty()
                                                        && intersection.dimension() == 1) {
                                                isOverlaps = true;
                                        }
                                } else
                                        isOverlaps = true;
                        }
                }
                return isOverlaps;
        }

        /**
         * @return TRUE, if the gA crosses with gB.
         */
        public boolean crosses() {
                if (!calledCrosses) {
                        calledCrosses = true;

                        if (geometryA.dimension() == 1 && geometryB.dimension() == 1) {
                                IntersectionMatrix3D tIM = getIntersectionMatrix();

                                if (tIM.matches("0***")) {
                                        isCrosses = true;
                                }

                                return isCrosses;
                        } else if (!intersection.isEmpty() && !touches() && !contains()
                                        && !within()) {
                                isCrosses = true;
                        }
                }
                return isCrosses;
        }

        /**
         * Compute the union between two geometries using union3D operation of SFCGAL
         * @return union between two geometry objects
         */
        public TransfiniteSet union() {
                if (union == null) {
                        union = SFAlgorithm.union3D(geometryA, geometryB);
                }
                if (unionGeometry == null) {

                        unionGeometry = SFCGALConvertor.geometryFromSFCGALGeometry(union);
                }
                return unionGeometry;
        }

        /**
         * Compute the union between two geometries using intersection3D operation of SFCGAL
         * @return intersection between two geometry objects
         */
        public TransfiniteSet intersection() {
                if (intersectionGeometry == null) {
                        intersectionGeometry = SFCGALConvertor
                                        .geometryFromSFCGALGeometry(intersection);
                }
                return intersectionGeometry;
        }

        /**
         * Compute the difference between two geometries using difference3D operation of SFCGAL
         * @return difference between two geometry objects
         */
        public TransfiniteSet difference() {
                if (difference == null) {
                        difference = SFAlgorithm.difference3D(geometryA, geometryB);
                }
                if (differenceGeometry == null) {

                        differenceGeometry = SFCGALConvertor.geometryFromSFCGALGeometry(difference);
                }
                return differenceGeometry;
        }

        /**
         * Compute the symmetric Difference between two geometries using 3D operations of SFCGAL
         * @return symmetric Difference between two geometry objects
         */
        public TransfiniteSet symmetricDifference() {
                if (union == null) {
                        union = SFAlgorithm.union3D(geometryA, geometryB);
                }
                if (difference == null) {
                        difference = SFAlgorithm.difference3D(geometryA, geometryB);
                }
                if (symDifferenceGeometry == null) {
                        SFGeometry symDifference = SFAlgorithm.difference3D(union, intersection);
                        symDifferenceGeometry = SFCGALConvertor
                                        .geometryFromSFCGALGeometry(symDifference);
                }
                return symDifferenceGeometry;
        }

}
