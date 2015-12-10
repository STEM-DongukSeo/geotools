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

    public SFEnvelope(double xmin, double xmax, double ymin, double ymax, double zmin, double zmax) {
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

    private native void allocate(double xmin, double xmax, double ymin, double ymax, double zmin,
            double zmax);

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

    public static void main(String[] args) {
        SFCoordinate c1 = new SFCoordinate(0.0, 0.0, 0.0);
        SFCoordinate c2 = new SFCoordinate(2.0, 2.0, 2.0);
        SFEnvelope envelope1 = new SFEnvelope(c1, c2);

        SFCoordinate c3 = new SFCoordinate(4.0, 0.0, 0.0);
        SFCoordinate c4 = new SFCoordinate(6.0, 2.0, 2.0);
        SFEnvelope envelope2 = new SFEnvelope(c3);
        envelope2.expandToInclude(c4);

        SFCoordinate c5 = new SFCoordinate(1.0, 0.0, 0.0);
        SFCoordinate c6 = new SFCoordinate(3.0, 3.0, 3.0);
        SFEnvelope envelope3 = new SFEnvelope();
        envelope3.expandToInclude(c5);
        envelope3.expandToInclude(c6);

        SFCoordinate c7 = new SFCoordinate(4.5, 0.5, 0.5);
        SFCoordinate c8 = new SFCoordinate(5.5, 1.5, 1.5);
        SFEnvelope envelope4 = new SFEnvelope(c7);
        envelope2.expandToInclude(c8);

        if (envelope1.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("envelope1.is3D() : " + envelope1.is3D());
        System.out.println("envelope1.xmin() : " + envelope1.xMin());
        System.out.println("envelope1.ymin() : " + envelope1.yMin());
        System.out.println("envelope1.zmin() : " + envelope1.zMin());
        System.out.println("envelope1.xmax() : " + envelope1.xMax());
        System.out.println("envelope1.ymax() : " + envelope1.yMax());
        System.out.println("envelope1.zmax() : " + envelope1.zMax());
        System.out.println("Envelope.contains(envelope1, envelope2) : "
                + SFEnvelope.contains(envelope1, envelope2));
        System.out.println("Envelope.overlaps(envelope1, envelope2) : "
                + SFEnvelope.overlaps(envelope1, envelope2));
        System.out.println("Envelope.contains(envelope1, envelope3) : "
                + SFEnvelope.contains(envelope1, envelope3));
        System.out.println("Envelope.overlaps(envelope1, envelope3) : "
                + SFEnvelope.overlaps(envelope1, envelope3));
        System.out.println("Envelope.contains(envelope2, envelope4) : "
                + SFEnvelope.contains(envelope2, envelope4));
        System.out.println("Envelope.overlaps(envelope2, envelope4) : "
                + SFEnvelope.overlaps(envelope2, envelope4));

        SFSolid solid1 = envelope1.toSolid();
        System.out.println("solid1.exteriorShell().polygon(0).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(0).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(1).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(1).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(2).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(2).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(3).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(3).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(4).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(4).exteriorRing().asText(0));
        System.out.println("solid1.exteriorShell().polygon(5).exteriorRing().asText() : \n\t"
                + solid1.exteriorShell().polygonN(5).exteriorRing().asText(0));
    }
}
