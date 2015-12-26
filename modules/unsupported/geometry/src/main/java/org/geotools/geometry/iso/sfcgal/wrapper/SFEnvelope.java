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
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = { "cpp/SFEnvelope.h", "cpp/SFEnvelope.cpp" })
public class SFEnvelope extends Pointer {
        static {
                Loader.load();
        }

        public SFEnvelope() {
                allocate();
        }

        public SFEnvelope(double xmin, double xmax, double ymin, double ymax) {
                allocate(xmin, xmax, ymin, ymax);
        }

        public SFEnvelope(double xmin, double xmax, double ymin, double ymax, double zmin,
                        double zmax) {
                allocate(xmin, xmax, ymin, ymax, zmin, zmax);
        }

        public SFEnvelope(SFCoordinate p) {
                allocate(p);
        }

        public SFEnvelope(SFCoordinate p1, SFCoordinate p2) {
                allocate(p1, p2);
        }

        public SFEnvelope(Pointer p) {
                super(p);
        }

        private native void allocate();

        private native void allocate(double xmin, double xmax, double ymin, double ymax);

        private native void allocate(double xmin, double xmax, double ymin, double ymax,
                        double zmin, double zmax);

        private native void allocate(@ByRef SFCoordinate p);

        private native void allocate(@ByRef SFCoordinate p1, @ByRef SFCoordinate p2);

        @Name("operator=")
        public native @ByRef SFEnvelope assign(@ByRef SFEnvelope c);

        public native @Cast("bool") boolean isEmpty();

        public native @Cast("bool") boolean is3D();

        public native void expandToInclude(@ByRef SFCoordinate coordinate);

        public native @ByRef double xMin();

        public native @ByRef double yMin();

        public native @ByRef double zMin();

        public native @ByRef double xMax();

        public native @ByRef double yMax();

        public native @ByRef double zMax();

        public static native @Cast("bool") boolean contains(@ByRef SFEnvelope a, @ByRef SFEnvelope b);

        public static native @Cast("bool") boolean overlaps(@ByRef SFEnvelope a, @ByRef SFEnvelope b);

        public native @ByRef SFLineString toRing();

        public native @ByRef SFPolygon toPolygon();

        public native @ByRef SFSolid toSolid();

        @Name("operator==")
        public native @Cast("bool") boolean equals(@ByRef SFEnvelope other);

}
