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
import org.bytedeco.javacpp.annotation.StdString;

/**
 * @author Donguk Seo
 *
 */
@Platform(include = "cpp/SFGeometryCollection.h")
public class SFGeometryCollection extends SFGeometry {
        static {
                Loader.load();
        }

        public SFGeometryCollection() {
                allocate();
        }

        public SFGeometryCollection(Pointer p) {
                super(p);
        }

        private native void allocate();

        @Name("operator=")
        public native @ByRef SFGeometryCollection assign(@ByRef SFGeometryCollection other);

        public native SFGeometryCollection clone();

        public native @StdString String geometryType();

        public native int geometryTypeId();

        public native int dimension();

        public native int coordinateDimension();

        public native @Cast("bool") boolean isEmpty();

        public native @Cast("bool") boolean is3D();

        public native @Cast("bool") boolean isMeasured();

        public native @Cast("size_t") int numGeometries();

        public native @ByRef SFGeometry geometryN(@Cast("size_t") int n);

        public native void addGeometry(@ByRef SFGeometry geometry);

}
