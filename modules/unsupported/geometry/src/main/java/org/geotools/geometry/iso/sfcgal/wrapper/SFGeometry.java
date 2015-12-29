/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Spatio-temporal Databases Laboratory (STEMLab)
 *
 *    the library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    the library is distributed in the hope that it will be useful,
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
@Platform(include = "cpp/SFGeometry.h")
public class SFGeometry extends Pointer {
        static {
                Loader.load();
        }

        /**
         * Default constructor (empty geometry)
         */
        public SFGeometry() {
                allocate();
        }

        /**
         * Copy constructor
         * @param p
         */
        public SFGeometry(Pointer p) {
                super(p);
        }

        /**
         * Call the native empty geometry constructor
         */
        private native void allocate();

        /**
         * @return Returns the clone of the geometry
         */
        public native SFGeometry clone();

        /**
         * @return Returns the type of the geometry as String
         */
        public native @StdString String geometryType();

        /**
         * @return Returns the type of the geometry as Integer
         */
        public native int geometryTypeId();

        /**
         * @return Returns the dimension of the geometry
         */
        public native int dimension();

        /**
         * @return Returns the coordinate dimension of the geometry
         */
        public native int coordinateDimension();

        /**
         * @return True, if the geometry is empty
         */
        public native @Cast("bool") boolean isEmpty();

        /**
         * @return True, if coordinate dimension of the geometry is 3D
         */
        public native @Cast("bool") boolean is3D();

        /**
         * @return True, if the geometry is measured (has an m)
         */
        public native @Cast("bool") boolean isMeasured();

        /**
         * @return Returns the WKT string (numDecimals = -1)
         */
        public String asText() {
                return asText(-1);
        }

        /**
         * @param numDecimals
         * @return Returns the WKT string
         */
        public native @StdString String asText(int numDecimals);

        /**
         * @return Returns a polygon representing the BBOX of the geometry
         */
        public native @ByRef SFEnvelope envelope();

        /**
         * @return Returns the boundary of the geometry
         */
        public native @ByRef SFGeometry boundary();

        /**
         * Compute the distance to another geometry
         * @param other
         * @return 
         */
        public native double distance(@ByRef SFGeometry other);

        public native double distance3D(@ByRef SFGeometry other);

        public native void round(long scale);

        public native int numGeometries();

        public native @ByRef SFGeometry geometryN(int n);

        @Name("operator==")
        public native @Cast("bool") boolean equals(@ByRef SFGeometry other);

}
