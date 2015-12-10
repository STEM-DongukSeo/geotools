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
@Platform(include = "cpp/SFMultiSolid.h")
public class SFMultiSolid extends SFGeometryCollection {
    static {
        Loader.load();
    }

    public SFMultiSolid() {
        allocate();
    }

    public SFMultiSolid(Pointer p) {
        super(p);
    }

    private native void allocate();

    @Name("operator=")
    public native @ByRef SFMultiSolid assign(@ByRef SFMultiSolid other);

    public native SFMultiSolid clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native @ByRef SFSolid solidN(@Cast("size_t") int n);

    public static SFMultiSolid makeMultiSolid() {
        SFPoint p1 = new SFPoint(0, 0, 0);
        SFPoint p2 = new SFPoint(0, -2, 0);
        SFPoint p3 = new SFPoint(2, -2, 0);
        SFPoint p4 = new SFPoint(2, 0, 0);
        SFPoint p5 = new SFPoint(0, 0, 2);
        SFPoint p6 = new SFPoint(0, -2, 2);
        SFPoint p7 = new SFPoint(2, -2, 2);
        SFPoint p8 = new SFPoint(2, 0, 2);

        ArrayList<SFPoint> points1 = new ArrayList<SFPoint>();
        points1.add(p1);
        points1.add(p2);
        points1.add(p3);
        points1.add(p4);
        points1.add(p5);
        points1.add(p6);
        points1.add(p7);
        points1.add(p8);

        SFPoint p11 = new SFPoint(4, 0, 0);
        SFPoint p12 = new SFPoint(4, -2, 0);
        SFPoint p13 = new SFPoint(6, -2, 0);
        SFPoint p14 = new SFPoint(6, 0, 0);
        SFPoint p15 = new SFPoint(4, 0, 2);
        SFPoint p16 = new SFPoint(4, -2, 2);
        SFPoint p17 = new SFPoint(6, -2, 2);
        SFPoint p18 = new SFPoint(6, 0, 2);

        ArrayList<SFPoint> points2 = new ArrayList<SFPoint>();
        points2.add(p11);
        points2.add(p12);
        points2.add(p13);
        points2.add(p14);
        points2.add(p15);
        points2.add(p16);
        points2.add(p17);
        points2.add(p18);

        SFSolid solid1 = SFSolid.makeSolid(points1);
        SFSolid solid2 = SFSolid.makeSolid(points2);

        SFMultiSolid multiSolid = new SFMultiSolid();
        multiSolid.addGeometry(solid1);
        multiSolid.addGeometry(solid2);

        return multiSolid;
    }

    public static void main(String[] args) {
        SFMultiSolid multiSolid = SFMultiSolid.makeMultiSolid();

        if (multiSolid.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("multiSolid.geomtryType() : " + multiSolid.geometryType());
        System.out.println("multiSolid.numGeometries() : " + multiSolid.numGeometries());
        System.out.println("multiSolid.solidN(0).geometryType() : "
                + multiSolid.solidN(0).geometryType());
        System.out.println("multiSolid.solidN(0).asText(-1) : " + multiSolid.solidN(0).asText(-1));
        System.out
                .println("multiSolid.solidN(0).numShells() : " + multiSolid.solidN(0).numShells());
        System.out.println("multiSolid.solidN(1).asText(-1) : " + multiSolid.solidN(1).asText(-1));
        System.out.println("multiSolid.solidN(1).numShells(): " + multiSolid.solidN(1).numShells());

    }
}
