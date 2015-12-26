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

        public static native @Cast("bool") boolean covers3D(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

        public static native @ByRef SFGeometry difference(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

        public static native @ByRef SFGeometry difference3D(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

        public static native double distance(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

        public static native double distance3D(@ByRef SFGeometry gA, @ByRef SFGeometry gB);

        public static native @ByRef SFGeometry extrude(@ByRef SFGeometry g, double dx, double dy,
                        double dz);

        public static native @ByRef SFGeometry intersection(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

        public static native @ByRef SFGeometry intersection3D(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

        public static native @Cast("bool") boolean intersects(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

        public static native @Cast("bool") boolean intersects3D(@ByRef SFGeometry gA,
                        @ByRef SFGeometry gB);

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

}
