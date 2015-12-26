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
@Platform(include = "cpp/SFCoordinate.h")
public class SFCoordinate extends Pointer {
        static {
                Loader.load();
        }

        public SFCoordinate() {
                allocate();
        }

        public SFCoordinate(double x, double y) {
                allocate(x, y);
        }

        public SFCoordinate(double x, double y, double z) {
                allocate(x, y, z);
        }

        public SFCoordinate(Pointer p) {
                super(p);
        }

        private native void allocate();

        private native void allocate(double x, double y, double z);

        private native void allocate(double x, double y);

        @Name("operator=")
        public native @ByRef SFCoordinate assign(@ByRef SFCoordinate c);

        public native int coordinateDimension();

        public native @Cast("bool") boolean isEmpty();

        public native @Cast("bool") boolean is3D();

        public native double x();

        public native double y();

        public native double z();

        public native @ByRef SFCoordinate round(@ByRef long scaleFactor);

        @Name("operator<")
        public native @Cast("bool") boolean isSmallerThan(@ByRef SFCoordinate c);

        @Name("operator==")
        public native @Cast("bool") boolean equals(@ByRef SFCoordinate c);

        @Name("operator!=")
        public native @Cast("bool") boolean notEquals(@ByRef SFCoordinate c);

}
