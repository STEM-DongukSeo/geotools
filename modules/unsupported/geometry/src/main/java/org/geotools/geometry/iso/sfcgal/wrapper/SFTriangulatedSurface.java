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
@Platform(include = "cpp/SFTriangulatedSurface.h")
public class SFTriangulatedSurface extends SFSurface {
    static {
        Loader.load();
    }

    public SFTriangulatedSurface() {
        allocate();
    }

    public SFTriangulatedSurface(ArrayList<SFTriangle> triangle) {
        PointerVector vector = new PointerVector(triangle.size());

        for (int i = 0; i < triangle.size(); i++) {
            vector.get(i).put(triangle.get(i));
        }

        allocate(vector);
    }

    public SFTriangulatedSurface(Pointer p) {
        super(p);
    }

    private native void allocate();

    private native void allocate(@ByRef PointerVector p);

    @Name("operator=")
    public native @ByRef SFTriangulatedSurface assign(@ByRef SFTriangulatedSurface tr);

    public native SFTriangulatedSurface clone();

    public native @StdString String geometryType();

    public native int geometryTypeId();

    public native int dimension();

    public native int coordinateDimension();

    public native @Cast("bool") boolean isEmpty();

    public native @Cast("bool") boolean is3D();

    public native @Cast("bool") boolean isMeasured();

    public native @Cast("size_t") int numTriangles();

    public native @ByRef SFTriangle triangleN(@Cast("size_t") int n);

    public native void addTriangle(SFTriangle triangle);

    public native void addTriangles(@ByRef SFTriangulatedSurface other);

    public native @Cast("size_t") int numGeometries();

    public native @ByRef SFTriangle geometryN(@Cast("size_t") int n);

    public native void reserve(@Cast("size_t") int n);

    public static void main(String[] args) {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);
        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        SFPoint p4 = new SFPoint(2.1, 3.2, 4.3, 1);
        SFPoint p5 = new SFPoint(1.5, 2.5, 3.5, 2);
        SFPoint p6 = new SFPoint(10.4, 11.2, 2.1, 3);

        SFPoint p7 = new SFPoint(3.1, 4.2, 5.3, 1);
        SFPoint p8 = new SFPoint(2.5, 3.5, 4.5, 2);
        SFPoint p9 = new SFPoint(11.4, 12.2, 3.1, 3);

        SFPoint p10 = new SFPoint(4.1, 5.2, 6.3, 1);
        SFPoint p11 = new SFPoint(3.5, 4.5, 5.5, 2);
        SFPoint p12 = new SFPoint(12.4, 13.2, 4.1, 3);

        SFPoint p13 = new SFPoint(5.1, 6.2, 7.3, 1);
        SFPoint p14 = new SFPoint(4.5, 5.5, 6.5, 2);
        SFPoint p15 = new SFPoint(13.4, 14.2, 5.1, 3);

        SFTriangle tr1 = new SFTriangle(p1, p2, p3);
        SFTriangle tr2 = new SFTriangle(p4, p5, p6);
        SFTriangle tr3 = new SFTriangle(p7, p8, p9);
        SFTriangle tr4 = new SFTriangle(p10, p11, p12);
        SFTriangle tr5 = new SFTriangle(p13, p14, p15);

        ArrayList<SFTriangle> triangles = new ArrayList<SFTriangle>();
        triangles.add(tr1);
        triangles.add(tr2);

        ArrayList<SFTriangle> triangles2 = new ArrayList<SFTriangle>();
        triangles2.add(tr3);
        triangles2.add(tr4);

        SFTriangulatedSurface trs = new SFTriangulatedSurface(triangles);

        if (trs.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("trs coodinateDemension : " + trs.coordinateDimension());
        System.out.println("trs demension : " + trs.dimension());
        System.out.println("trs numTriangles : " + trs.numTriangles());
        System.out.println("trs numGeometries : " + trs.numGeometries());
        System.out.println("trs toString : " + trs.toString());

        SFTriangulatedSurface trs2 = new SFTriangulatedSurface(trs);

        trs2.addTriangle(tr5);
        System.out.println("\ntrs2.addTriangle(tr5)");
        System.out.println("trs2 numTriangles : " + trs2.numTriangles());
        System.out.println("trs2 numGeometries : " + trs2.numGeometries());

        System.out.println("trs numTriangles()" + trs.numTriangles());
        trs2.addTriangles(trs);
        System.out.println("\ntrs2.addTriangles(trs)");
        System.out.println("trs2 numTriangles : " + trs2.numTriangles());
        System.out.println("trs2 numGeometries : " + trs2.numGeometries());
        System.out.println("trs2.triangle(2) toString : " + trs2.triangleN(2).toString());
        System.out.println("trs2.triangle(2) vertex(2).m() : " + trs2.triangleN(2).vertex(2).m());

        // System.out.println("trs boundary " + trs.boundary().geometryType());
    }
}
