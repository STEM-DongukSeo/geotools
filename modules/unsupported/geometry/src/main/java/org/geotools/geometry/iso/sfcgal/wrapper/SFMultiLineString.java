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
@Platform(include = "cpp/SFMultiLineString.h")
public class SFMultiLineString extends SFGeometryCollection {
    static {
        Loader.load();
    }

    public SFMultiLineString() {
        allocate();
    }

    public SFMultiLineString(Pointer p) {
        super(p);
    }

    private native void allocate();

    @Name("operator=")
    public native @ByRef SFMultiLineString assign(@ByRef SFMultiLineString other);

    public native SFMultiLineString clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native @ByRef SFLineString lineStringN(@Cast("size_t") int n);

    public static SFMultiLineString makeMultiLineString(ArrayList<SFPoint> pointList) {
        SFPoint p1 = pointList.get(0);
        SFPoint p5 = pointList.get(4);
        SFPoint p2 = pointList.get(1);
        SFPoint p6 = pointList.get(5);
        SFPoint p3 = pointList.get(2);
        SFPoint p7 = pointList.get(6);
        SFPoint p4 = pointList.get(3);
        SFPoint p8 = pointList.get(7);

        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p4);
        ls1.addPoint(p3);
        ls1.addPoint(p2);
        ls1.addPoint(p1);

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p3);
        points.add(p4);
        points.add(p8);
        points.add(p7);
        points.add(p3);
        SFLineString ls2 = new SFLineString(points);

        SFLineString ls3 = new SFLineString();
        ls3.addPoint(p5);
        ls3.addPoint(p6);
        ls3.addPoint(p7);
        ls3.addPoint(p8);
        ls3.addPoint(p5);

        SFLineString ls4 = new SFLineString();
        ls4.addPoint(p6);
        ls4.addPoint(p5);
        ls4.addPoint(p1);
        ls4.addPoint(p2);
        ls4.addPoint(p6);
        ArrayList<SFLineString> rings = new ArrayList<SFLineString>();
        rings.add(ls4);

        SFLineString ls5 = new SFLineString();
        ls5.addPoint(p2);
        ls5.addPoint(p3);
        ls5.addPoint(p7);
        ls5.addPoint(p6);
        ls5.addPoint(p2);

        SFLineString ls6 = new SFLineString();
        ls6.addPoint(p4);
        ls6.addPoint(p1);
        ls6.addPoint(p5);
        ls6.addPoint(p8);
        ls6.addPoint(p4);

        SFMultiLineString multiLineString = new SFMultiLineString();
        multiLineString.addGeometry(ls1);
        multiLineString.addGeometry(ls2);
        multiLineString.addGeometry(ls3);
        multiLineString.addGeometry(ls4);
        multiLineString.addGeometry(ls5);
        multiLineString.addGeometry(ls6);

        return multiLineString;
    }

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(0, 0, 0);
        SFPoint p2 = new SFPoint(0, -2, 0);
        SFPoint p3 = new SFPoint(2, -2, 0);
        SFPoint p4 = new SFPoint(2, 0, 0);
        SFPoint p5 = new SFPoint(0, 0, 2);
        SFPoint p6 = new SFPoint(0, -2, 2);
        SFPoint p7 = new SFPoint(2, -2, 2);
        SFPoint p8 = new SFPoint(2, 0, 2);

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);

        SFMultiLineString multiLineString = SFMultiLineString.makeMultiLineString(points);

        if (multiLineString.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("multiLineString.geomtryType() : " + multiLineString.geometryType());
        System.out.println("multiLineString.numGeometries() : " + multiLineString.numGeometries());
        System.out.println("multiLineString.lineStringN(0).geometryType() : "
                + multiLineString.lineStringN(0).geometryType());
        System.out.println("multiLineString.lineStringN(0).asText(-1) : "
                + multiLineString.lineStringN(0).asText(-1));
        System.out.println("multiLineString.lineStringN(0).asText(0) : "
                + multiLineString.lineStringN(0).asText(0));
        System.out.println("multiLineString.lineStringN(0).asText(1) : "
                + multiLineString.lineStringN(0).asText(1));
        System.out.println("multiLineString.lineStringN(0).asText(2) : "
                + multiLineString.lineStringN(0).asText(2));
        System.out.println("multiLineString.lineStringN(0).numPoint() : "
                + multiLineString.lineStringN(0).numPoints());
        System.out.println("multiLineString.lineStringN(0).numGeometries() : "
                + multiLineString.lineStringN(0).numGeometries());
    }
}
