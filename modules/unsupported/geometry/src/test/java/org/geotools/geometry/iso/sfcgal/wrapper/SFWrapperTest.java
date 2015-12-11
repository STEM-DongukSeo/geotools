/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Spatio-temporal Databases Laboratory(STEMLab)
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

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Donguk Seo
 *
 */
public class SFWrapperTest extends TestCase {

    public void testMain() {
        this._testSFCoordiante();
        this._testSFGeometry();
        this._testSFPoint();
        this._testSFLineString();
        this._testSFSurface();
        this._testSFPolygon();
        this._testSFTriangle();
        this._testSFPolyhedralSurface();
    }

    public void _testSFCoordiante() {
        SFCoordinate c = new SFCoordinate(1.4, 2.1);

        if (c.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("c.x() : " + c.x());
        System.out.println("c.y() : " + c.y());
        System.out.println("c.z() : " + c.z());
        System.out.println("coordianteDimension : " + c.coordinateDimension());
    }
    
    public void _testSFGeometry() {
        SFGeometry geometry = new SFGeometry();

        System.out.println(geometry.is3D());
    }

    public void _testSFPoint() {
        SFPoint p = new SFPoint(2.0, 1.5, 0);
        SFPoint p2 = new SFPoint(p);
        SFPoint p3 = new SFPoint(1, 1, 1);

        if (p.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        p.setM(2.5);
        System.out.println("x : " + p.x());
        System.out.println("y : " + p.y());
        System.out.println("z : " + p.z());
        System.out.println("m : " + p.m());
        System.out.println("dimension : " + p.dimension());
        System.out.println("coordinateDimension : " + p.coordinateDimension());
        System.out.println("SFCGAL::Geometry::asText() = " + p.asText(-1));

        System.out.println("p == p2 ? " + p.equals(p2));
        System.out.println("p != p3 ? " + p.notEquals(p3));

        System.out.println("p1 <-> p3 distance : " + p.distance(p3));
        System.out.println("p1 <-> p3 distance3D : " + p.distance3D(p3));
    }

    public void _testSFLineString() {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFLineString ls = new SFLineString(p1, p2);

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        ls.addPoint(p3);

        if (ls.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        if (ls.isClosed()) {
            System.out.println("closed");
        } else {
            System.out.println("not closed");
        }

        System.out.println("p3 coodinateDemension : " + ls.pointN(2).coordinateDimension());
        System.out.println("p3 demension : " + ls.pointN(2).dimension());
        System.out.println("p3 m : " + ls.pointN(2).m());

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p2);
        points.add(p3);
        points.add(p1);

        SFLineString ls1 = new SFLineString(points);
        System.out.println("p3 coodinateDemension : " + ls1.pointN(2).coordinateDimension());
        System.out.println("p3 demension : " + ls1.pointN(2).dimension());
        System.out.println("p3 m : " + ls1.pointN(2).m());

        System.out.println("ls1 numpoins : " + ls1.numPoints() + " ls1 numsegments : "
                + ls1.numSegments());

        //
        SFGeometry boundary = ls1.boundary();
        System.out.println("ls1 boundary type : " + boundary.geometryType());
        System.out.println("ls1 boundary numGeometries : " + boundary.numGeometries());

        SFEnvelope envelope = ls1.envelope();
        System.out.println("envelope.is3D() : " + envelope.is3D());
        System.out.println("envelope.xmin() : " + envelope.xMin());
        System.out.println("envelope.ymin() : " + envelope.yMin());
        System.out.println("envelope.zmin() : " + envelope.zMin());
        System.out.println("envelope.xmax() : " + envelope.xMax());
        System.out.println("envelope.ymax() : " + envelope.yMax());
        System.out.println("envelope.zmax() : " + envelope.zMax());
        //

        System.out.println("ls1.clear");
        ls1.clear();
        System.out.println("ls1 numpoins : " + ls1.numPoints() + " ls1 numsegments : "
                + ls1.numSegments());
    }
    
    public void _testSFSurface() {
        SFSurface surface = new SFSurface();

        System.out.println(surface.geometryType());
    }

    public void _testSFPolygon() {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);
        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        SFLineString ls = new SFLineString(p1, p2);

        ls.addPoint(p3);
        ls.addPoint(p1);

        SFPolygon polygon = new SFPolygon(ls);

        ArrayList<SFPoint> points = new ArrayList<SFPoint>();
        points.add(p2);
        points.add(p3);
        points.add(p1);
        points.add(p2);
        SFLineString ls1 = new SFLineString(points);
        polygon.addInteriorRing(ls1);

        if (polygon.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        if (polygon.isCounterClockWiseOriented()) {
            System.out.println("counter clockwised");
        } else {
            System.out.println("not counterclockwised");
        }

        System.out.println("polygon coodinateDemension : " + polygon.coordinateDimension());
        System.out.println("geometryType : " + polygon.geometryType());
        System.out.println("numrings : " + polygon.numRings());
        System.out.println("interior numPoints : " + polygon.interiorRingN(0).numPoints());

        SFPolygon polygon2 = new SFPolygon(polygon);

        System.out.println(polygon2.geometryType());
    }
    
    public void _testSFTriangle() {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);
        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        SFTriangle tr = new SFTriangle(p1, p2, p3);

        if (tr.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("tr.vertex(2) coodinateDemension : "
                + tr.vertex(2).coordinateDimension());
        System.out.println("tr.vertex(2) demension : " + tr.vertex(2).dimension());
        System.out.println("tr.vertex(2) m : " + tr.vertex(2).m());

        SFTriangle tr2 = new SFTriangle(tr);
        System.out.println("tr2 = tr.reverse()");
        tr2.reverse();
        System.out.println("tr2.vertex(2) coodinateDemension : "
                + tr2.vertex(2).coordinateDimension());
        System.out.println("tr2.vertex(2) demension : " + tr2.vertex(2).dimension());
        System.out.println("tr2.vertex(2) m : " + tr2.vertex(2).m());

        SFPolygon polygon = tr.toPolygon();
        System.out.println("polygon.numRings() " + polygon.numRings());
    }

    public void _testSFPolyhedralSurface() {
        SFPoint p1 = new SFPoint(0, 0, 0);
        SFPoint p2 = new SFPoint(0, -2, 0);
        SFPoint p3 = new SFPoint(2, -2, 0);
        SFPoint p4 = new SFPoint(2, 0, 0);
        SFPoint p5 = new SFPoint(0, 0, 2);
        SFPoint p6 = new SFPoint(0, -2, 2);
        SFPoint p7 = new SFPoint(2, -2, 2);
        SFPoint p8 = new SFPoint(2, 0, 2);
        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p2);
        ls1.addPoint(p3);
        ls1.addPoint(p4);
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
        ls4.addPoint(p2);
        ls4.addPoint(p1);
        ls4.addPoint(p5);
        ls4.addPoint(p6);
        ls4.addPoint(p2);
        ArrayList<SFLineString> rings = new ArrayList<SFLineString>();
        rings.add(ls4);

        SFPolygon polygon1 = new SFPolygon();
        polygon1.setExteriorRing(ls1);
        SFPolygon polygon2 = new SFPolygon(ls2);
        SFPolygon polygon3 = new SFPolygon(ls3);
        SFPolygon polygon4 = new SFPolygon(rings);

        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();
        polygons.add(polygon1);
        polygons.add(polygon2);
        polygons.add(polygon3);

        SFPolyhedralSurface polyhedral = new SFPolyhedralSurface(polygons);
        polyhedral.addPolygon(polygon4);

        System.out.println("polygon1 <-> polygon3 distance : " + polygon1.distance(polygon3));
        System.out.println("polygon1 <-> polygon3 distance3D : " + polygon1.distance3D(polygon3));
        // System.out.println("polygon1 <-> polygon2 distance : " + polygon1.distance(polygon2)); // polygon2(2D polygon) is invalid
        System.out.println("polygon1 <-> polygon2 distance3D : " + polygon1.distance3D(polygon2));

        System.out.println("PolyhedralSurface toString() : " + polyhedral.asText(0));
        System.out.println("PolyhedralSurface boundary.toString() : " + polyhedral.boundary().asText(0));
        System.out.println("PolyhedralSurface GeometryType() : " + polyhedral.geometryType());
        System.out.println("PolyhedralSurface numPolygons() : " + polyhedral.numPolygons());
        System.out.println("PolyhedralSurface polygonN(3).geometryType() : "
                + polyhedral.polygonN(3).geometryType());
        System.out.println("PolyhedralSurface polygonN(3).asText() : "
                + polyhedral.polygonN(3).asText(0));
        System.out.println("PolyhedralSurface polygonN(3).numInteriorRings() : "
                + polyhedral.polygonN(3).numInteriorRings());
        System.out.println("PolyhedralSurface polygonN(3).numRings() : "
                + polyhedral.polygonN(3).numRings());
        System.out.println("PolyhedralSurface polygonN(3).numGeometries() : "
                + polyhedral.polygonN(3).numGeometries());
        System.out.println("PolyhedralSurface geometryN(3).numGeometries() : "
                + polyhedral.geometryN(3).numGeometries());
        System.out.println("PolyhedralSurface geometryN(3).hasInterioRings() : "
                + polyhedral.geometryN(3).hasInteriorRings());
        System.out.println("PolyhedralSurface geometryN(0).hasInterioRings() : "
                + polyhedral.geometryN(0).hasInteriorRings());
        System.out.println("PolyhedralSurface geometryN(3).isCounterClockWiseOriented() : "
                + polyhedral.geometryN(3).isCounterClockWiseOriented());
        System.out.println("PolyhedralSurface geometryN(0).isCOunterClockWiseOriented() : "
                + polyhedral.geometryN(0).isCounterClockWiseOriented());
    }

    public void _testSFTriangulatedSurface() {
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
    }

    public void _testSFGeometryCollection() {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFGeometryCollection collection = new SFGeometryCollection();

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        collection.addGeometry(p1);
        collection.addGeometry(p2);
        collection.addGeometry(p3);

        if (collection.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        SFLineString ls = new SFLineString();
        ls.addPoint(p1);
        ls.addPoint(p2);
        ls.addPoint(p3);

        collection.addGeometry(ls);

        System.out.println("collection geometryType() : " + collection.geometryType());
        System.out.println("collection geometryTypeId() : " + collection.geometryTypeId());
        System.out.println("collection asText() : " + collection.asText(0));
        System.out.println("collection numGeometries : " + collection.numGeometries());
        System.out.println("collection geometryN(1).geometryType() : "
                + collection.geometryN(1).geometryType());
        System.out.println("collection geometryN(1).asText() : "
                + collection.geometryN(1).asText(0));
        System.out.println("collection geometryN(1).address() : "
                + collection.geometryN(1).address());
        System.out.println("collection geometryN(3).geometryType() : "
                + collection.geometryN(3).geometryType());
        System.out.println("collection geometryN(3).pointN(1).asText() : "
                + new SFLineString(collection.geometryN(3)).pointN(1).asText(0));
        System.out.println("collection geometryN(3).pointN(1).address : "
                + new SFLineString(collection.geometryN(3)).pointN(1).address());
    }
    
    public void _testSFMultiPoint() {
        SFPoint p1 = new SFPoint(1.1, 2.2, 3.3, 1);
        SFPoint p2 = new SFPoint(0.5, 1.5, 2.5, 2);

        SFMultiPoint multiPoint = new SFMultiPoint();

        SFPoint p3 = new SFPoint(9.4, 10.2, 1.1, 3);

        multiPoint.addGeometry(p1);
        multiPoint.addGeometry(p2);
        multiPoint.addGeometry(p3);

        if (multiPoint.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("multiPoint.geomtryType() : " + multiPoint.geometryType());
        System.out.println("multiPoint.numGeometries() : " + multiPoint.numGeometries());
        System.out.println("multiPoint.pointN(2).coodinateDemension : "
                + multiPoint.pointN(2).coordinateDimension());
        System.out.println("multiPoint.pointN(2).demension : " + multiPoint.pointN(2).dimension());
        System.out.println("multiPoint.pointN(2).m : " + multiPoint.pointN(2).m());
    }
    
    public SFMultiLineString makeMultiLineString(ArrayList<SFPoint> pointList) {
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
    
    public void _testSFMultiLineString() {
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

        SFMultiLineString multiLineString = this.makeMultiLineString(points);

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
    
    public static SFMultiPolygon makeMultiPolygon(ArrayList<SFPoint> pointList) {
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

        SFPolygon polygon1 = new SFPolygon(ls1);
        SFPolygon polygon2 = new SFPolygon(ls2);
        SFPolygon polygon3 = new SFPolygon(ls3);
        SFPolygon polygon4 = new SFPolygon(ls4);
        SFPolygon polygon5 = new SFPolygon(ls5);
        SFPolygon polygon6 = new SFPolygon(ls6);

        SFMultiPolygon MultiPolygon = new SFMultiPolygon();
        MultiPolygon.addGeometry(polygon1);
        MultiPolygon.addGeometry(polygon2);
        MultiPolygon.addGeometry(polygon3);
        MultiPolygon.addGeometry(polygon4);
        MultiPolygon.addGeometry(polygon5);
        MultiPolygon.addGeometry(polygon1);

        return MultiPolygon;
    }

    public void _testMultiPolygon() {
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

        SFMultiPolygon multiPolygon = this.makeMultiPolygon(points);

        if (multiPolygon.isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println("not empty");
        }

        System.out.println("multiPolygon.geomtryType() : " + multiPolygon.geometryType());
        System.out.println("multiPolygon.numGeometries() : " + multiPolygon.numGeometries());
        System.out.println("multiPolygon.lineStringN(0).geometryType() : "
                + multiPolygon.polygonN(0).geometryType());
        System.out.println("multiPolygon.lineStringN(0).asText(-1) : "
                + multiPolygon.polygonN(0).asText(-1));
        System.out.println("multiPolygon.lineStringN(0).numRings() : "
                + multiPolygon.polygonN(0).numRings());
        System.out.println("multiPolygon.lineStringN(0).numGeometries() : "
                + multiPolygon.polygonN(0).numGeometries());
    }
    
    public SFMultiSolid makeMultiSolid() {
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

    public void _testSFMultiSolid() {
        SFMultiSolid multiSolid = this.makeMultiSolid();

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
    
    public void _testSFTriangulate() {
        SFSolid solid = SFAlgorithm.getSolids().get(0);

        SFPoint p4 = new SFPoint(0.5, -1.5, 2);
        SFPoint p6 = new SFPoint(2, -1.5, 2);
        SFPoint p23 = new SFPoint(2, -1.5, 0.5);
        SFPoint p24 = new SFPoint(2, -0.5, 0.5);
        SFPoint p9 = new SFPoint(2, -0.5, 2);
        SFPoint p7 = new SFPoint(0.5, -0.5, 2);

        ArrayList<SFPoint> positions4 = new ArrayList<SFPoint>();
        positions4.add(p4);
        positions4.add(p6);
        positions4.add(p23);
        positions4.add(p24);
        positions4.add(p9);
        positions4.add(p7);
        positions4.add(p4);
        SFLineString exteriorRing = new SFLineString(positions4);
        SFPolygon polygon = new SFPolygon(exteriorRing);
        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();
        polygons.add(polygon);

        SFTriangulatedSurface triangulatedSurface = new SFTriangulatedSurface();
        SFTriangulate.triangulatePolygon3D(polygon, triangulatedSurface);
        System.out.println(triangulatedSurface.asText(1));
    }
    
    public static ArrayList<SFPoint> getPoints(int type) {
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

        //
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

        //
        SFPoint p19 = new SFPoint(5, 0, 0);
        SFPoint p20 = new SFPoint(5, -2, 0);
        SFPoint p21 = new SFPoint(7, -2, 0);
        SFPoint p22 = new SFPoint(7, 0, 0);
        SFPoint p23 = new SFPoint(5, 0, 2);
        SFPoint p24 = new SFPoint(5, -2, 2);
        SFPoint p25 = new SFPoint(7, -2, 2);
        SFPoint p26 = new SFPoint(7, 0, 2);

        ArrayList<SFPoint> points3 = new ArrayList<SFPoint>();
        points3.add(p19);
        points3.add(p20);
        points3.add(p21);
        points3.add(p22);
        points3.add(p23);
        points3.add(p24);
        points3.add(p25);
        points3.add(p26);

        SFPoint p27 = new SFPoint(2, 0, 0);
        SFPoint p28 = new SFPoint(2, -2, 0);
        SFPoint p29 = new SFPoint(4, -2, 0);
        SFPoint p30 = new SFPoint(4, 0, 0);
        SFPoint p31 = new SFPoint(2, 0, 2);
        SFPoint p32 = new SFPoint(2, -2, 2);
        SFPoint p33 = new SFPoint(4, -2, 2);
        SFPoint p34 = new SFPoint(4, 0, 2);

        ArrayList<SFPoint> points4 = new ArrayList<SFPoint>();
        points4.add(p27);
        points4.add(p28);
        points4.add(p29);
        points4.add(p30);
        points4.add(p31);
        points4.add(p32);
        points4.add(p33);
        points4.add(p34);

        SFPoint p35 = new SFPoint(0.5, -0.5, 0.5);
        SFPoint p36 = new SFPoint(0.5, -1.5, 0.5);
        SFPoint p37 = new SFPoint(1.5, -1.5, 0.5);
        SFPoint p38 = new SFPoint(1.5, -0.5, 0.5);
        SFPoint p39 = new SFPoint(0.5, -0.5, 1.5);
        SFPoint p40 = new SFPoint(0.5, -1.5, 1.5);
        SFPoint p41 = new SFPoint(1.5, -1.5, 1.5);
        SFPoint p42 = new SFPoint(1.5, -0.5, 1.5);

        ArrayList<SFPoint> points5 = new ArrayList<SFPoint>();
        points5.add(p35);
        points5.add(p36);
        points5.add(p37);
        points5.add(p38);
        points5.add(p39);
        points5.add(p40);
        points5.add(p41);
        points5.add(p42);

        SFPoint p43 = new SFPoint(2, 0, 2);
        SFPoint p44 = new SFPoint(2, -2, 2);
        SFPoint p45 = new SFPoint(4, -2, 2);
        SFPoint p46 = new SFPoint(4, 0, 2);
        SFPoint p47 = new SFPoint(2, 0, 4);
        SFPoint p48 = new SFPoint(2, -2, 4);
        SFPoint p49 = new SFPoint(4, -2, 4);
        SFPoint p50 = new SFPoint(4, 0, 4);

        ArrayList<SFPoint> points6 = new ArrayList<SFPoint>();
        points6.add(p43);
        points6.add(p44);
        points6.add(p45);
        points6.add(p46);
        points6.add(p47);
        points6.add(p48);
        points6.add(p49);
        points6.add(p50);

        SFPoint p51 = new SFPoint(1, -1, 0);
        SFPoint p52 = new SFPoint(1, -2, 0);
        SFPoint p53 = new SFPoint(2, -2, 0);
        SFPoint p54 = new SFPoint(2, -1, 0);
        SFPoint p55 = new SFPoint(1, -1, 1);
        SFPoint p56 = new SFPoint(1, -2, 1);
        SFPoint p57 = new SFPoint(2, -2, 1);
        SFPoint p58 = new SFPoint(2, -1, 1);

        ArrayList<SFPoint> points7 = new ArrayList<SFPoint>();
        points7.add(p51);
        points7.add(p52);
        points7.add(p53);
        points7.add(p54);
        points7.add(p55);
        points7.add(p56);
        points7.add(p57);
        points7.add(p58);

        if (type == 0)
            return points1;
        else if (type == 1)
            return points2;
        else if (type == 2)
            return points3;
        else if (type == 3)
            return points4;
        else if (type == 4)
            return points5;
        else if (type == 5)
            return points6;
        else if (type == 6)
            return points7;

        return points7;
    }

    public static ArrayList<SFLineString> getLineStrings(int type) {
        ArrayList<SFPoint> points = getPoints(type);
        ArrayList<SFLineString> lineStrings = new ArrayList<SFLineString>();

        SFPoint p1 = points.get(0);
        SFPoint p2 = points.get(1);
        SFPoint p3 = points.get(2);
        SFPoint p4 = points.get(3);
        SFPoint p5 = points.get(4);
        SFPoint p6 = points.get(5);
        SFPoint p7 = points.get(6);
        SFPoint p8 = points.get(7);

        SFLineString ls1 = new SFLineString();
        ls1.addPoint(p1);
        ls1.addPoint(p4);
        ls1.addPoint(p3);
        ls1.addPoint(p2);
        ls1.addPoint(p1);

        ArrayList<SFPoint> pointList = new ArrayList<SFPoint>();
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p8);
        pointList.add(p7);
        pointList.add(p3);
        SFLineString ls2 = new SFLineString(pointList);

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

        lineStrings.add(ls1);
        lineStrings.add(ls2);
        lineStrings.add(ls3);
        lineStrings.add(ls4);
        lineStrings.add(ls5);
        lineStrings.add(ls6);

        return lineStrings;
    }

    public static ArrayList<SFPolygon> getPolygons(int type) {
        ArrayList<SFLineString> lineStrings = getLineStrings(type);
        ArrayList<SFPolygon> polygons = new ArrayList<SFPolygon>();

        for (int i = 0; i < lineStrings.size(); i++) {
            polygons.add(new SFPolygon(lineStrings.get(i)));
        }

        return polygons;
    }

    public static ArrayList<SFSolid> getSolids() {
        ArrayList<SFSolid> solids = new ArrayList<SFSolid>();

        ArrayList<SFPolygon> polygons1 = getPolygons(0);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons1)));

        ArrayList<SFPolygon> polygons2 = getPolygons(1);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons2)));

        ArrayList<SFPolygon> polygons3 = getPolygons(2);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons3)));

        ArrayList<SFPolygon> polygons4 = getPolygons(3);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons4)));

        ArrayList<SFPolygon> polygons5 = getPolygons(4);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons5)));

        ArrayList<SFPolygon> polygons6 = getPolygons(5);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons6)));

        ArrayList<SFPolygon> polygons7 = getPolygons(6);
        solids.add(new SFSolid(new SFPolyhedralSurface(polygons7)));

        return solids;
    }
}
