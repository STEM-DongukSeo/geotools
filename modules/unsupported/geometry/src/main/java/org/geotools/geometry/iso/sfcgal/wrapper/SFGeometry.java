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
@Platform(include = "cpp/SFGeometry.h")
public class SFGeometry extends Pointer {
    static {
        Loader.load();
    }

    public SFGeometry() {
        allocate();
    }

    public SFGeometry(Pointer p) {
        super(p);
    }

    private native void allocate();

    public native SFGeometry clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public String asText() {
        return asText(1);
    }

    public native @StdString String asText(int numDcimals);

    public native @ByRef SFEnvelope envelope();

    public native @ByRef SFGeometry boundary();

    public native double distance(@ByRef SFGeometry other);

    public native double distance3D(@ByRef SFGeometry other);

    public native void round(long scale);

    public native int numGeometries();

    public native @ByRef SFGeometry geometryN(int n);

    @Name("operator==")
    public native @Cast("bool") boolean equals(@ByRef SFGeometry other);

}
