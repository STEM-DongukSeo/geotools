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
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFLineString.h")
public class SFLineString extends SFGeometry {
        static {
                Loader.load();
        }

        public SFLineString() {
                allocate();
        }

        public SFLineString(ArrayList<SFPoint> p) {
                PointerVector vector = new PointerVector(p.size());

                for (int i = 0; i < p.size(); i++) {
                        vector.get(i).put(p.get(i));
                }

                allocate(vector);
        }

        public SFLineString(SFPoint startPoint, SFPoint endPoint) {
                allocate(startPoint, endPoint);
        }

        public SFLineString(Pointer p) {
                super(p);
        }

        private native void allocate();

        private native void allocate(@ByRef PointerVector p);

        private native void allocate(@ByRef SFPoint startPoint, @ByRef SFPoint endPoint);

        @Name("operator=")
        public native @ByRef SFLineString assign(@ByRef SFLineString ls);

        public native SFLineString clone();

        public native @StdString String geometryType();

        public native int geometryTypeId();

        public native int dimension();

        public native int coordinateDimension();

        public native @Cast("bool") boolean isEmpty();

        public native @Cast("bool") boolean is3D();

        public native @Cast("bool") boolean isMeasured();

        public native void clear();

        public native void reverse();

        public native @Cast("size_t") int numPoints();

        public native @Cast("size_t") int numSegments();

        public native @ByRef SFPoint pointN(@Cast("size_t") int n);

        public native @ByRef SFPoint startPoint();

        public native @ByRef SFPoint endPoint();

        public native void addPoint(SFPoint p);

        public native @Cast("bool") boolean isClosed();

        public native void reserve(@Cast("size_t") int n);

}
