/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.geometry.iso.sfcgal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.GeometryFactoryFinder;
import org.geotools.geometry.iso.coordinate.GeometryFactoryImpl;
import org.geotools.geometry.iso.coordinate.PositionImpl;
import org.geotools.geometry.iso.primitive.CurveImpl;
import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.geotools.geometry.iso.primitive.ShellImpl;
import org.geotools.geometry.iso.primitive.SolidBoundaryImpl;
import org.geotools.geometry.iso.primitive.SolidImpl;
import org.geotools.geometry.iso.primitive.SurfaceImpl;
import org.geotools.geometry.iso.sfcgal.wrapper.SFAlgorithm;
import org.geotools.geometry.iso.sfcgal.wrapper.SFGeometry;
import org.geotools.geometry.iso.sfcgal.wrapper.SFLineString;
import org.geotools.geometry.iso.sfcgal.wrapper.SFPoint;
import org.geotools.geometry.iso.sfcgal.wrapper.SFPolygon;
import org.geotools.geometry.iso.sfcgal.wrapper.SFPolyhedralSurface;
import org.geotools.geometry.iso.sfcgal.wrapper.SFSolid;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.PositionFactory;
import org.opengis.geometry.TransfiniteSet;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.PrimitiveFactory;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.Shell;
import org.opengis.geometry.primitive.Solid;
import org.opengis.geometry.primitive.SolidBoundary;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;

public class SFCGALConvertorTest extends TestCase {

    public void testMain() {
        // GeometryBuilder builder = new GeometryBuilder(DefaultGeographicCRS.WGS84_3D);

        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        GeometryBuilder builder = new GeometryBuilder(hints);

        // this._testTriangle1(tGeomFactory);
        // this._testSolid2(builder);
        // this._testSolid3(builder);
        // this._testSolid4(builder);
        // this.testSolidPointQuery(builder);
        this.testSolidLineStringQuery(builder);
        this.testSolidPolygonQuery(builder);
        this.testSolidSolidQuery(builder);
        // testUnion(builder);
    }

    private void testUnion(GeometryBuilder builder) {
        SFPolygon polygon1 = SFAlgorithm.getPolygons(0).get(0);
        SFPolygon polygon2 = SFAlgorithm.getPolygons(0).get(1);

        System.out.println("polygon1 : " + polygon1.asText(1));
        System.out.println("polygon2 : " + polygon2.asText(1));

        SFGeometry union = SFAlgorithm.union3D(polygon1, polygon2);
        SFGeometry convex_union = SFAlgorithm.convexHull3D(union);
        System.out.println("union : " + union.asText(1));
        System.out.println("convex_union : " + convex_union.asText(1));
    }

    private void _testSolid1(GeometryBuilder builder) {
        GeometryFactoryImpl tCoordFactory = (GeometryFactoryImpl) builder.getGeometryFactory();
        PrimitiveFactoryImpl tPrimFactory = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        /* Defining Positions for LineStrings */
        ArrayList<Position> line1 = new ArrayList<Position>();
        line1.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 50, 20 })));
        line1.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 30, 30 })));
        line1.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 20, 50 })));
        line1.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 20, 70 })));

        ArrayList<Position> line2 = new ArrayList<Position>();
        line2.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 20, 70 })));
        line2.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 40, 80 })));
        line2.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 70, 80 })));

        ArrayList<Position> line3 = new ArrayList<Position>();
        line3.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 70, 80 })));
        line3.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 90, 70 })));
        line3.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 100, 60 })));
        line3.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 100, 40 })));

        ArrayList<Position> line4 = new ArrayList<Position>();
        line4.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 100, 40 })));
        line4.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 80, 30 })));
        line4.add(new PositionImpl(tCoordFactory.createDirectPosition(new double[] { 50, 20 })));

        /* Setting up Array of these LineStrings */
        ArrayList<CurveSegment> tLineList1 = new ArrayList<CurveSegment>();
        tLineList1.add(tCoordFactory.createLineString(line1));
        tLineList1.add(tCoordFactory.createLineString(line2));

        ArrayList<CurveSegment> tLineList2 = new ArrayList<CurveSegment>();
        tLineList2.add(tCoordFactory.createLineString(line3));
        tLineList2.add(tCoordFactory.createLineString(line4));

        /* Build Curve */
        CurveImpl curve1 = tPrimFactory.createCurve(tLineList1);
        CurveImpl curve2 = tPrimFactory.createCurve(tLineList2);

        /* Build Ring */
        ArrayList<OrientableCurve> curveList = new ArrayList<OrientableCurve>();
        curveList.add(curve1);
        curveList.add(curve2);

        Ring ring1 = tPrimFactory.createRing(curveList);

        // System.out.println(ring1);

        // System.out.println(ring1.getEnvelope());

        // Rings should be simple
        assertTrue(ring1.isSimple());

        // Rings should be cyclic (=closed)
        assertTrue(ring1.isCycle());

        // ***** getRepresentativePoint()
        double[] dp = ring1.getRepresentativePoint().getCoordinate();
        assertTrue(dp[0] == 50);
        assertTrue(dp[1] == 20);

        assertTrue(ring1.isCycle() == true);

        // PaintGMObject.paint(curve1);
    }

    public static void example() {
        GeometryBuilder builder = new GeometryBuilder(DefaultGeographicCRS.WGS84);
        PositionFactory posF = builder.getPositionFactory();
        PrimitiveFactory primFF = builder.getPrimitiveFactory();
        GeometryFactory geomF = builder.getGeometryFactory();

        Point point = builder.createPoint(48.44, -123.37);

        // create a list of connected positions
        List<Position> dps = new ArrayList<Position>();
        dps.add(builder.createDirectPosition(new double[] { 20, 10 }));
        dps.add(builder.createDirectPosition(new double[] { 40, 10 }));
        dps.add(builder.createDirectPosition(new double[] { 50, 40 }));
        dps.add(builder.createDirectPosition(new double[] { 30, 50 }));
        dps.add(builder.createDirectPosition(new double[] { 10, 30 }));
        dps.add(builder.createDirectPosition(new double[] { 20, 10 }));

        // create linestring from directpositions
        LineString line = builder.createLineString(dps);

        // create curvesegments from line
        ArrayList<CurveSegment> segs = new ArrayList<CurveSegment>();
        segs.add(line);

        // Create list of OrientableCurves that make up the surface
        OrientableCurve curve = builder.createCurve(segs);
        List<OrientableCurve> orientableCurves = new ArrayList<OrientableCurve>();
        orientableCurves.add(curve);

        // create the interior ring and a list of empty interior rings (holes)
        Ring extRing = builder.createRing(orientableCurves);
        List<Ring> intRings = new ArrayList<Ring>();

        // create the surfaceboundary from the rings
        SurfaceBoundary sb = builder.createSurfaceBoundary(extRing, intRings);

        // create the surface
        Surface surface = builder.createSurface(sb);
    }

    private void _testSolid2(GeometryBuilder builder) {
        PositionFactory posF = builder.getPositionFactory();
        PrimitiveFactory primFF = builder.getPrimitiveFactory();
        GeometryFactory geomF = builder.getGeometryFactory();

        Point point = builder.createPoint(48.44, -123.37, 111.1);

        DirectPosition position1 = builder.createDirectPosition(new double[] { 0, 0, 0 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 0, -2, 0 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 2, 0, 0 });
        DirectPosition position5 = builder.createDirectPosition(new double[] { 0, 0, 2 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 0, -2, 2 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 2, 0, 2 });

        // create a list of connected positions
        List<Position> dps1 = new ArrayList<Position>();
        dps1.add(position1);
        dps1.add(position4);
        dps1.add(position3);
        dps1.add(position2);
        dps1.add(position1);

        List<Position> dps2 = new ArrayList<Position>();
        dps2.add(position3);
        dps2.add(position4);
        dps2.add(position8);
        dps2.add(position7);
        dps2.add(position3);

        List<Position> dps3 = new ArrayList<Position>();
        dps3.add(position5);
        dps3.add(position6);
        dps3.add(position7);
        dps3.add(position8);
        dps3.add(position5);

        List<Position> dps4 = new ArrayList<Position>();
        dps4.add(position6);
        dps4.add(position5);
        dps4.add(position1);
        dps4.add(position2);
        dps4.add(position6);

        List<Position> dps5 = new ArrayList<Position>();
        dps5.add(position2);
        dps5.add(position3);
        dps5.add(position7);
        dps5.add(position6);
        dps5.add(position2);

        List<Position> dps6 = new ArrayList<Position>();
        dps6.add(position1);
        dps6.add(position5);
        dps6.add(position8);
        dps6.add(position4);
        dps6.add(position1);

        // create linestring from directpositions
        LineString line1 = builder.createLineString(dps1);
        LineString line2 = builder.createLineString(dps2);
        LineString line3 = builder.createLineString(dps3);
        LineString line4 = builder.createLineString(dps4);
        LineString line5 = builder.createLineString(dps5);
        LineString line6 = builder.createLineString(dps6);

        // create curvesegments from line
        ArrayList<CurveSegment> segs1 = new ArrayList<CurveSegment>();
        segs1.add(line1);
        ArrayList<CurveSegment> segs2 = new ArrayList<CurveSegment>();
        segs2.add(line2);
        ArrayList<CurveSegment> segs3 = new ArrayList<CurveSegment>();
        segs3.add(line3);
        ArrayList<CurveSegment> segs4 = new ArrayList<CurveSegment>();
        segs4.add(line4);
        ArrayList<CurveSegment> segs5 = new ArrayList<CurveSegment>();
        segs5.add(line5);
        ArrayList<CurveSegment> segs6 = new ArrayList<CurveSegment>();
        segs6.add(line6);

        // Create list of OrientableCurves that make up the surface
        OrientableCurve curve1 = builder.createCurve(segs1);
        List<OrientableCurve> orientableCurves1 = new ArrayList<OrientableCurve>();
        orientableCurves1.add(curve1);
        OrientableCurve curve2 = builder.createCurve(segs2);
        List<OrientableCurve> orientableCurves2 = new ArrayList<OrientableCurve>();
        orientableCurves2.add(curve2);
        OrientableCurve curve3 = builder.createCurve(segs3);
        List<OrientableCurve> orientableCurves3 = new ArrayList<OrientableCurve>();
        orientableCurves3.add(curve3);
        OrientableCurve curve4 = builder.createCurve(segs4);
        List<OrientableCurve> orientableCurves4 = new ArrayList<OrientableCurve>();
        orientableCurves4.add(curve4);
        OrientableCurve curve5 = builder.createCurve(segs5);
        List<OrientableCurve> orientableCurves5 = new ArrayList<OrientableCurve>();
        orientableCurves5.add(curve5);
        OrientableCurve curve6 = builder.createCurve(segs6);
        List<OrientableCurve> orientableCurves6 = new ArrayList<OrientableCurve>();
        orientableCurves6.add(curve6);

        // create the interior ring and a list of empty interior rings (holes)
        PrimitiveFactoryImpl _pmFF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        Map _hints = (Map) _pmFF.getImplementationHints();
        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, _hints.get(Hints.CRS));
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        PrimitiveFactory pmFF = GeometryFactoryFinder.getPrimitiveFactory(hints);

        Ring extRing1 = pmFF.createRing(orientableCurves1);
        Ring extRing2 = pmFF.createRing(orientableCurves2);
        Ring extRing3 = pmFF.createRing(orientableCurves3);
        Ring extRing4 = pmFF.createRing(orientableCurves4);
        Ring extRing5 = pmFF.createRing(orientableCurves5);
        Ring extRing6 = pmFF.createRing(orientableCurves6);
        List<Ring> intRings = new ArrayList<Ring>();

        // create the surfaceboundary from the rings
        SurfaceBoundary sb1 = pmFF.createSurfaceBoundary(extRing1, intRings);
        SurfaceBoundary sb2 = pmFF.createSurfaceBoundary(extRing2, intRings);
        SurfaceBoundary sb3 = pmFF.createSurfaceBoundary(extRing3, intRings);
        SurfaceBoundary sb4 = pmFF.createSurfaceBoundary(extRing4, intRings);
        SurfaceBoundary sb5 = pmFF.createSurfaceBoundary(extRing5, intRings);
        SurfaceBoundary sb6 = pmFF.createSurfaceBoundary(extRing6, intRings);

        // create the surface
        Surface surface1 = pmFF.createSurface(sb1);
        Surface surface2 = pmFF.createSurface(sb2);
        Surface surface3 = pmFF.createSurface(sb3);
        Surface surface4 = pmFF.createSurface(sb4);
        Surface surface5 = pmFF.createSurface(sb5);
        Surface surface6 = pmFF.createSurface(sb6);

        List<OrientableSurface> surfaces = new ArrayList<OrientableSurface>();
        surfaces.add(surface1);
        surfaces.add(surface2);
        surfaces.add(surface3);
        surfaces.add(surface4);
        surfaces.add(surface5);
        surfaces.add(surface6);
        Shell exteriorShell = new ShellImpl(surfaces);
        List<Shell> interiors = new ArrayList<Shell>();
        SolidBoundary solidBoundary = new SolidBoundaryImpl(
                exteriorShell.getCoordinateReferenceSystem(), exteriorShell, interiors);
        Solid solid = pmFF.createSolid(solidBoundary);

        SFSolid sfcgalSolid = SFCGALConvertor.solidToSFCGALSolid(solid);
        SFPoint sfcgalPoint1 = SFCGALConvertor.pointToSFCGALPoint(point);
        SFPoint sfcgalPoint2 = SFCGALConvertor.directPositionToSFCGALPoint(position1);
        SFLineString sfcgalLineString1 = SFCGALConvertor.lineStringToSFCGALLineString(line1);
        SFLineString sfcgalLineString2 = SFCGALConvertor.curveToSFCGALLineString((Curve) curve1);
        SFPolygon sfcgalPolygon = SFCGALConvertor.surfaceToSFCGALPolygon(surface2);

        System.out.println(solid.getArea());
        System.out.println(solid.getVolume());
        Envelope envelope = solid.getEnvelope();
        System.out.println(envelope.getLowerCorner().toString());
        System.out.println(envelope.getUpperCorner().toString());

        System.out.println(sfcgalSolid.asText(1));
        System.out.println("-- sfcgalPoint1 --");
        System.out.println(sfcgalPoint1.asText());
        System.out.println(point.toString());
        System.out.println("-- sfcgalPoint2 --");
        System.out.println(sfcgalPoint2.asText());
        System.out.println(position1.toString());
        System.out.println("-- sfcgalLineString1 --");
        System.out.println(sfcgalLineString1.asText());
        System.out.println(line1.toString());
        System.out.println("-- sfcgalLineString2 --");
        System.out.println(sfcgalLineString2.asText());
        System.out.println(curve1.toString());
        System.out.println("-- sfcgalPolygon --");
        System.out.println(sfcgalPolygon.asText());
        System.out.println(surface1.toString());

        System.out.println("intersects : " + solid.intersects(curve1));

        SFSolid sfcgalSolid1 = SFAlgorithm.getSolids().get(1);
        SFSolid sfcgalSolid2 = SFAlgorithm.getSolids().get(2);
        SFSolid sfcgalSolid1_2 = sfcgalSolid1.clone();
        Solid solid1 = SFCGALConvertor.solidFromSFCGALSolid(sfcgalSolid1);
        Solid solid2 = SFCGALConvertor.solidFromSFCGALSolid(sfcgalSolid2);
        Solid solid1_2 = SFCGALConvertor.solidFromSFCGALSolid(sfcgalSolid1_2);
        TransfiniteSet intersection = solid1.intersection(solid2);

        System.out.println(((Solid) intersection).getArea());
        System.out.println(((Solid) intersection).getVolume());
        System.out.println("solid1 == solid1_2 ? " + ((SolidImpl) solid1).equals(solid1_2));
        System.out.println("solid1 == solid2 ? " + solid1.equals(solid2));

        SFPoint sfcgalPoint = SFAlgorithm.getPoints(1).get(0);
        SFLineString sfcgalLineString = SFAlgorithm.getLineStrings(1).get(0);

        Geometry p = SFCGALConvertor.geometryFromSFCGALGeometry(sfcgalPoint);
        Geometry curve = SFCGALConvertor.geometryFromSFCGALGeometry(sfcgalLineString);
        Geometry surface = SFCGALConvertor.geometryFromSFCGALGeometry(sfcgalPolygon);
        Geometry _solid = SFCGALConvertor.geometryFromSFCGALGeometry(sfcgalSolid1);
        System.out.println(p.toString());
        System.out.println(curve.toString());
        System.out.println(surface.toString());

        System.out.println(_solid.distance(p));
        System.out.println("p, solid disjoint : " + ((SolidImpl) _solid).disjoint(p));
        System.out.println(_solid.distance(curve));
        System.out.println(_solid.toString());
        System.out.println(_solid.getConvexHull().toString());
    }

    private void _testSolid3(GeometryBuilder builder) {
        SFSolid solid = SFAlgorithm.getSolids().get(0);
        System.out.println(solid.asText(0));

        SFGeometry geometry = SFAlgorithm.convexHull3D(solid);
        System.out.println(geometry.asText(0));

        SFPolyhedralSurface polyhedral = new SFPolyhedralSurface(geometry);
        System.out.println(polyhedral.asText(0));
        System.out.println(polyhedral.numPolygons());

        SFSolid solid2 = new SFSolid(polyhedral);
        System.out.println(solid2.asText(0));
        System.out.println(solid2.exteriorShell().numPolygons());

        System.out.println(solid2.exteriorShell().polygonN(0).exteriorRing().asText(0));
    }

    private void _testSolid4(GeometryBuilder builder) {
        Solid solid1 = getGeotoolsSolid(builder);
        SFSolid _solid2 = SFAlgorithm.getSolids().get(1);
        Solid solid2 = SFCGALConvertor.solidFromSFCGALSolid(_solid2);
        SFSolid _solid3 = SFAlgorithm.getSolids().get(2);
        Solid solid3 = SFCGALConvertor.solidFromSFCGALSolid(_solid3);
        Point point = builder.createPoint(0, 0, 0);

        SFGeometry union = SFAlgorithm.union3D(_solid2, _solid3);
        System.out.println(union.asText(1));

        SFGeometry _intersection = SFAlgorithm.intersection3D(_solid2, _solid3);
        Geometry intersection = SFCGALConvertor.geometryFromSFCGALGeometry(_intersection);

        System.out.println(solid1.toString());
        System.out.println(solid2.toString());

        System.out.println(((SolidImpl) solid1).touches(solid2));

        SFPolygon poly = _solid2.exteriorShell().polygonN(1);
        Surface surface = SFCGALConvertor.surfaceFromSFCGALPolygon(poly);
        System.out.println(surface.toString());
        System.out.println(((SolidImpl) solid2).touches(surface));

        System.out.println(intersection.toString());
        System.out.println(solid2.contains(intersection));

        System.out.println(((SolidImpl) solid1).touches(point));

        // builder = new GeometryBuilder(DefaultGeographicCRS.WGS84);
        DirectPosition position1 = builder.createDirectPosition(new double[] { 0, 0, 0 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 4, 0, 0 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 4, 4, 0 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 0, 4, 0 });
        DirectPosition position5 = builder.createDirectPosition(new double[] { 1, 0, 0 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 3, 0, 0 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 3, 3, 0 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 1, 2, 0 });
        System.out.println("dimension : "
                + position1.getCoordinateReferenceSystem().getCoordinateSystem().getDimension());

        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        List<DirectPosition> directPositions1 = new ArrayList<DirectPosition>();
        directPositions1.add(position1);
        directPositions1.add(position2);
        directPositions1.add(position3);
        directPositions1.add(position4);
        directPositions1.add(position1);
        SurfaceImpl surf = pmF.createSurfaceByDirectPositions(directPositions1);

        List<DirectPosition> directPositions2 = new ArrayList<DirectPosition>();
        directPositions2.add(position5);
        directPositions2.add(position6);
        directPositions2.add(position7);
        directPositions2.add(position8);
        Curve curve = pmF.createCurveByDirectPositions(directPositions2);

        System.out.println(surf.contains(curve));
        System.out.println("end");

    }

    private void testSolidPointQuery(GeometryBuilder builder) {
        Solid solid1 = getGeotoolsSolid(builder);
        Point point1 = builder.createPoint(0, 0, 0);
        Point point2 = builder.createPoint(1, 0, 0);
        Point point3 = builder.createPoint(1, -1, 0);
        Point point4 = builder.createPoint(1, -1, 1);
        Point point5 = builder.createPoint(1, 0, 1);
        Point point6 = builder.createPoint(1, 1, 1);

        // System.out.println(point1.distance(solid1));
        // testQuery(solid1, point1);
        // testQuery(solid1, point2);
        // testQuery(solid1, point3);
        // testQuery(solid1, point4);
        // testQuery(solid1, point5);
        // testQuery(solid1, point6);
    }

    private void testSolidPolygonQuery(GeometryBuilder builder) {
        ArrayList<Surface> surfaces = getGeotoolsSurface(builder);
        Solid solid = getGeotoolsSolid(builder);

        for (int i = 0; i < surfaces.size(); i++) {
            Surface surface = surfaces.get(i);
            
            System.out.println("------------------------");
            System.out.println((i+1) + ". ");


            testQuery(solid, surface);
        }

    }

    private void testSolidSolidQuery(GeometryBuilder builder) {
        SFSolid sol1 = SFAlgorithm.getSolids().get(0);
        SFSolid sol2 = SFAlgorithm.getSolids().get(3);
        SFSolid sol3 = SFAlgorithm.getSolids().get(1);
        SFSolid sol4 = SFAlgorithm.getSolids().get(2);
        SFSolid sol5 = SFAlgorithm.getSolids().get(5);
        SFSolid sol6 = SFAlgorithm.getSolids().get(4);
        SFSolid sol7 = SFAlgorithm.getSolids().get(6);

        // Solid solid1 = SFCGALConvertor.solidFromSFCGALSolid(sol1);
        Solid solid1 = getGeotoolsSolid(builder);
        Solid solid2 = SFCGALConvertor.solidFromSFCGALSolid(sol2);
        Solid solid3 = SFCGALConvertor.solidFromSFCGALSolid(sol3);
        Solid solid4 = SFCGALConvertor.solidFromSFCGALSolid(sol4);
        Solid solid5 = SFCGALConvertor.solidFromSFCGALSolid(sol5);
        Solid solid6 = SFCGALConvertor.solidFromSFCGALSolid(sol6);
        Solid solid7 = SFCGALConvertor.solidFromSFCGALSolid(sol7);

        System.out.println("solid 1 : " + solid1.toString());
        System.out.println("solid 2 : " + solid2.toString());
        System.out.println("solid 3 : " + solid3.toString());
        System.out.println("solid 4 : " + solid4.toString());
        System.out.println("solid 5 : " + solid5.toString());
        System.out.println("solid 6 : " + solid6.toString());
        System.out.println("solid 7 : " + solid7.toString());

        testQuery(solid6, solid7);

        // SFGeometry g = Algorithm.convexHull3D(sol1);
        // SFSolid g2 = (SFSolid) g;
        // testQuery(solid2, solid3);
        // testQuery(solid1, solid4);
        // testQuery(solid1, solid5);
        // testQuery(solid5, solid1);
    }

    private void testQuery(Solid solid1, Geometry geom) {
        System.out.println("-------------- query test ----------");
        System.out.println(((SolidImpl) solid1).toString());
        System.out.println(geom.toString());

        System.out.println("disjoint : " + ((SolidImpl) solid1).disjoint(geom));
        System.out.println("intersects : " + ((SolidImpl) solid1).intersects(geom));
        System.out.println("contains : " + ((SolidImpl) solid1).contains(geom));
        System.out.println("within : " + ((SolidImpl) solid1).within(geom));
        System.out.println("equal : " + ((SolidImpl) solid1).equals(geom));

        System.out.println("touches : " + ((SolidImpl) solid1).touches(geom));

        System.out.println("overlaps : " + ((SolidImpl) solid1).overlaps(geom));
        System.out.println("crosses : " + ((SolidImpl) solid1).crosses(geom));

        System.out.println("distance3D : " + ((SolidImpl) solid1).distance(geom));

        if (solid1.intersects(geom)) {
            // System.out.println("intersection : ");
            // System.out.println("intersection : " + ((SolidImpl)solid1).intersection(geom).toString());
        } else {
            // System.out.println("intersection : null");
        }
    }

    private void testSolidLineStringQuery(GeometryBuilder builder) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        Solid solid = getGeotoolsSolid(builder);

        DirectPosition position1 = builder.createDirectPosition(new double[] { -2, -1, 2 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 0.5, -1, 2 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 1, -1, 2 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 1.5, -1, 2 });
        DirectPosition position5 = builder.createDirectPosition(new double[] { 2, -1, 2 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 4, -1, 2 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 2, -1, 1 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 4, -1, 1 });
        DirectPosition position9 = builder.createDirectPosition(new double[] { 0, -1, 1 });
        DirectPosition position10 = builder.createDirectPosition(new double[] { 0.5, -1, 1 });
        DirectPosition position11 = builder.createDirectPosition(new double[] { 1, -1, 1 });
        DirectPosition position12 = builder.createDirectPosition(new double[] { 1.5, -1, 1 });
        DirectPosition position20 = builder.createDirectPosition(new double[] { 0, -1, 2 });
        DirectPosition position21 = builder.createDirectPosition(new double[] { 2, -1, 2 });
        DirectPosition position22 = builder.createDirectPosition(new double[] { 0, -1, -1 });
        DirectPosition position23 = builder.createDirectPosition(new double[] { 2, -1, -1 });
        DirectPosition position24 = builder.createDirectPosition(new double[] { 0.5, -1, 0 });
        DirectPosition position25 = builder.createDirectPosition(new double[] { 1.5, -1, 0 });

        System.out.println("curve1");
        Curve curve1 = getGeotoolsCurve(builder, position7, position8);
        testQuery(solid, curve1);

        System.out.println("curve2");
        Curve curve2 = getGeotoolsCurve(builder, position2, position4);
        testQuery(solid, curve2);

        System.out.println("curve3");
        Curve curve3 = getGeotoolsCurve(builder, position2, position6);
        testQuery(solid, curve3);

        System.out.println("curve4");
        List<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position2);
        positions1.add(position5);
        positions1.add(position7);
        Curve curve4 = pmF.createCurveByDirectPositions(positions1);
        testQuery(solid, curve4);

        System.out.println("curve5");
        Curve curve5 = getGeotoolsCurve(builder, position1, position6);
        testQuery(solid, curve5);

        List<DirectPosition> positions4 = new ArrayList<DirectPosition>();
        positions4.add(position1);
        positions4.add(position6);
        // Curve curve5 = pmF.createCurveByDirectPositions(positions4);
        // testQuery(solid, curve5);

        System.out.println("curve6");
        List<DirectPosition> positions2 = new ArrayList<DirectPosition>();
        positions2.add(position1);
        positions2.add(position3);
        positions2.add(position6);
        Curve curve6 = pmF.createCurveByDirectPositions(positions2);
        testQuery(solid, curve6);

        System.out.println("curve7");
        Curve curve7 = getGeotoolsCurve(builder, position9, position12);
        testQuery(solid, curve7);

        System.out.println("curve8");
        Curve curve8 = getGeotoolsCurve(builder, position9, position7);
        testQuery(solid, curve8);

        System.out.println("curve9");
        Curve curve9 = getGeotoolsCurve(builder, position10, position12);
        testQuery(solid, curve9);

        System.out.println("curve10");
        List<DirectPosition> positions3 = new ArrayList<DirectPosition>();
        positions3.add(position10);
        positions3.add(position2);
        positions3.add(position4);
        positions3.add(position12);
        Curve curve10 = pmF.createCurveByDirectPositions(positions3);
        testQuery(solid, curve10);

        System.out.println("curve11");
        List<DirectPosition> positions5 = new ArrayList<DirectPosition>();
        positions5.add(position22);
        positions5.add(position20);
        positions5.add(position21);
        positions5.add(position23);
        Curve curve11 = pmF.createCurveByDirectPositions(positions5);
        testQuery(solid, curve11);

        System.out.println("curve12");
        List<DirectPosition> positions6 = new ArrayList<DirectPosition>();
        positions6.add(position24);
        positions6.add(position2);
        positions6.add(position4);
        positions6.add(position25);
        Curve curve12 = pmF.createCurveByDirectPositions(positions6);
        testQuery(solid, curve12);
    }

    private Curve getGeotoolsCurve(GeometryBuilder builder, DirectPosition position1,
            DirectPosition position2) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        List<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position1);
        positions1.add(position2);
        Curve curve = pmF.createCurveByDirectPositions(positions1);

        return curve;
    }

    private ArrayList<Surface> getGeotoolsSurface(GeometryBuilder builder) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        Solid solid = getGeotoolsSolid(builder);

        DirectPosition position1 = builder.createDirectPosition(new double[] { -2, -1.5, 2 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { -2, -0.5, 2 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 0, -1.5, 2 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 0.5, -1.5, 2 });
        DirectPosition position5 = builder.createDirectPosition(new double[] { 1.5, -1.5, 2 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 2, -1.5, 2 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 0.5, -0.5, 2 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 1.5, -0.5, 2 });
        DirectPosition position9 = builder.createDirectPosition(new double[] { 2, -0.5, 2 });
        DirectPosition position10 = builder.createDirectPosition(new double[] { 0.5, -0.5, 4 });
        DirectPosition position11 = builder.createDirectPosition(new double[] { 1.5, -0.5, 4 });
        DirectPosition position12 = builder.createDirectPosition(new double[] { 4, -0.5, 2 });
        DirectPosition position13 = builder.createDirectPosition(new double[] { 4, -1.5, 2 });
        DirectPosition position14 = builder.createDirectPosition(new double[] { -2, -1.5, 1.5 });
        DirectPosition position15 = builder.createDirectPosition(new double[] { -2, -1.5, 0.5 });
        DirectPosition position16 = builder.createDirectPosition(new double[] { 0, -1.5, 1.5 });
        DirectPosition position17 = builder.createDirectPosition(new double[] { 0.5, -1.5, 1.5 });
        DirectPosition position18 = builder.createDirectPosition(new double[] { 1.5, -1.5, 1.5 });
        DirectPosition position19 = builder.createDirectPosition(new double[] { 2, -1.5, 1.5 });
        DirectPosition position20 = builder.createDirectPosition(new double[] { 0, -1.5, 0.5 });
        DirectPosition position21 = builder.createDirectPosition(new double[] { 0.5, -1.5, 0.5 });
        DirectPosition position22 = builder.createDirectPosition(new double[] { 1.5, -1.5, 0.5 });
        DirectPosition position23 = builder.createDirectPosition(new double[] { 2, -1.5, 0.5 });
        DirectPosition position24 = builder.createDirectPosition(new double[] { 2, -0.5, 0.5 });
        DirectPosition position25 = builder.createDirectPosition(new double[] { 4, -1.5, 1.5 });
        DirectPosition position26 = builder.createDirectPosition(new double[] { 4, -1.5, 0.5 });
        DirectPosition position27 = builder.createDirectPosition(new double[] { 0, -1.5, 0 });
        DirectPosition position28 = builder.createDirectPosition(new double[] { 2, -1.5, 0 });

        // touches
        ArrayList<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position7);
        positions1.add(position8);
        positions1.add(position11);
        positions1.add(position10);
        positions1.add(position7);
        ArrayList<DirectPosition> positions2 = new ArrayList<DirectPosition>();
        positions2.add(position4);
        positions2.add(position5);
        positions2.add(position8);
        positions2.add(position7);
        positions2.add(position4);
        ArrayList<DirectPosition> positions3 = new ArrayList<DirectPosition>();
        positions3.add(position1);
        positions3.add(position13);
        positions3.add(position12);
        positions3.add(position2);
        positions3.add(position1);
        ArrayList<DirectPosition> positions4 = new ArrayList<DirectPosition>();
        positions4.add(position4);
        positions4.add(position6);
        positions4.add(position23);
        positions4.add(position24);
        positions4.add(position9);
        positions4.add(position7);
        positions4.add(position4);
        ArrayList<DirectPosition> positions5 = new ArrayList<DirectPosition>();
        positions5.add(position4);
        positions5.add(position13);
        positions5.add(position12);
        positions5.add(position7);
        positions5.add(position4);
        // contains
        ArrayList<DirectPosition> positions6 = new ArrayList<DirectPosition>();
        positions6.add(position20);
        positions6.add(position23);
        positions6.add(position19);
        positions6.add(position16);
        positions6.add(position20);
        ArrayList<DirectPosition> positions7 = new ArrayList<DirectPosition>();
        positions7.add(position20);
        positions7.add(position22);
        positions7.add(position18);
        positions7.add(position16);
        positions7.add(position20);
        ArrayList<DirectPosition> positions8 = new ArrayList<DirectPosition>();
        positions8.add(position21);
        positions8.add(position22);
        positions8.add(position18);
        positions8.add(position17);
        positions8.add(position21);
        ArrayList<DirectPosition> positions9 = new ArrayList<DirectPosition>();
        positions9.add(position27);
        positions9.add(position28);
        positions9.add(position6);
        positions9.add(position3);
        positions9.add(position27);
        // crosses
        ArrayList<DirectPosition> positions10 = new ArrayList<DirectPosition>();
        positions10.add(position15);
        positions10.add(position26);
        positions10.add(position25);
        positions10.add(position14);
        positions10.add(position15);
        ArrayList<DirectPosition> positions11 = new ArrayList<DirectPosition>();
        positions11.add(position21);
        positions11.add(position26);
        positions11.add(position25);
        positions11.add(position17);
        positions11.add(position21);

        ArrayList<Surface> surfaces = new ArrayList<Surface>();
        surfaces.add(makeGeotoolsSurface(builder, positions1));
        surfaces.add(makeGeotoolsSurface(builder, positions2));
        surfaces.add(makeGeotoolsSurface(builder, positions3));
        surfaces.add(makeGeotoolsSurface(builder, positions4));
        surfaces.add(makeGeotoolsSurface(builder, positions5));
        surfaces.add(makeGeotoolsSurface(builder, positions6));
        surfaces.add(makeGeotoolsSurface(builder, positions7));
        surfaces.add(makeGeotoolsSurface(builder, positions8));
        surfaces.add(makeGeotoolsSurface(builder, positions9));
        surfaces.add(makeGeotoolsSurface(builder, positions10));
        surfaces.add(makeGeotoolsSurface(builder, positions11));

        return surfaces;
    }

    private Surface makeGeotoolsSurface(GeometryBuilder builder, List<DirectPosition> positions) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        SurfaceImpl surf = pmF.createSurfaceByDirectPositions(positions);

        return surf;
    }

    private Solid getGeotoolsSolid(GeometryBuilder builder) {
        DirectPosition position1 = builder.createDirectPosition(new double[] { 0, 0, 0 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 0, -2, 0 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 2, 0, 0 });
        DirectPosition position5 = builder.createDirectPosition(new double[] { 0, 0, 2 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 0, -2, 2 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 2, 0, 2 });

        // create a list of connected positions
        List<Position> dps1 = new ArrayList<Position>();
        dps1.add(position1);
        dps1.add(position4);
        dps1.add(position3);
        dps1.add(position2);
        dps1.add(position1);

        List<Position> dps2 = new ArrayList<Position>();
        dps2.add(position3);
        dps2.add(position4);
        dps2.add(position8);
        dps2.add(position7);
        dps2.add(position3);

        List<Position> dps3 = new ArrayList<Position>();
        dps3.add(position5);
        dps3.add(position6);
        dps3.add(position7);
        dps3.add(position8);
        dps3.add(position5);

        List<Position> dps4 = new ArrayList<Position>();
        dps4.add(position6);
        dps4.add(position5);
        dps4.add(position1);
        dps4.add(position2);
        dps4.add(position6);

        List<Position> dps5 = new ArrayList<Position>();
        dps5.add(position2);
        dps5.add(position3);
        dps5.add(position7);
        dps5.add(position6);
        dps5.add(position2);

        List<Position> dps6 = new ArrayList<Position>();
        dps6.add(position1);
        dps6.add(position5);
        dps6.add(position8);
        dps6.add(position4);
        dps6.add(position1);

        // create linestring from directpositions
        LineString line1 = builder.createLineString(dps1);
        LineString line2 = builder.createLineString(dps2);
        LineString line3 = builder.createLineString(dps3);
        LineString line4 = builder.createLineString(dps4);
        LineString line5 = builder.createLineString(dps5);
        LineString line6 = builder.createLineString(dps6);

        // create curvesegments from line
        ArrayList<CurveSegment> segs1 = new ArrayList<CurveSegment>();
        segs1.add(line1);
        ArrayList<CurveSegment> segs2 = new ArrayList<CurveSegment>();
        segs2.add(line2);
        ArrayList<CurveSegment> segs3 = new ArrayList<CurveSegment>();
        segs3.add(line3);
        ArrayList<CurveSegment> segs4 = new ArrayList<CurveSegment>();
        segs4.add(line4);
        ArrayList<CurveSegment> segs5 = new ArrayList<CurveSegment>();
        segs5.add(line5);
        ArrayList<CurveSegment> segs6 = new ArrayList<CurveSegment>();
        segs6.add(line6);
        
        // Create list of OrientableCurves that make up the surface
        OrientableCurve curve1 = builder.createCurve(segs1);
        List<OrientableCurve> orientableCurves1 = new ArrayList<OrientableCurve>();
        orientableCurves1.add(curve1);
        OrientableCurve curve2 = builder.createCurve(segs2);
        List<OrientableCurve> orientableCurves2 = new ArrayList<OrientableCurve>();
        orientableCurves2.add(curve2);
        OrientableCurve curve3 = builder.createCurve(segs3);
        List<OrientableCurve> orientableCurves3 = new ArrayList<OrientableCurve>();
        orientableCurves3.add(curve3);
        OrientableCurve curve4 = builder.createCurve(segs4);
        List<OrientableCurve> orientableCurves4 = new ArrayList<OrientableCurve>();
        orientableCurves4.add(curve4);
        OrientableCurve curve5 = builder.createCurve(segs5);
        List<OrientableCurve> orientableCurves5 = new ArrayList<OrientableCurve>();
        orientableCurves5.add(curve5);
        OrientableCurve curve6 = builder.createCurve(segs6);
        List<OrientableCurve> orientableCurves6 = new ArrayList<OrientableCurve>();
        orientableCurves6.add(curve6);

        // create the interior ring and a list of empty interior rings (holes)
        PrimitiveFactoryImpl pmFF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        Ring extRing1 = pmFF.createRing(orientableCurves1);
        Ring extRing2 = pmFF.createRing(orientableCurves2);
        Ring extRing3 = pmFF.createRing(orientableCurves3);
        Ring extRing4 = pmFF.createRing(orientableCurves4);
        Ring extRing5 = pmFF.createRing(orientableCurves5);
        Ring extRing6 = pmFF.createRing(orientableCurves6);

        // create surfaceboundary by rings
        SurfaceBoundary sb1 = pmFF.createSurfaceBoundary(extRing1, new ArrayList<Ring>());
        SurfaceBoundary sb2 = pmFF.createSurfaceBoundary(extRing2, new ArrayList<Ring>());
        SurfaceBoundary sb3 = pmFF.createSurfaceBoundary(extRing3, new ArrayList<Ring>());
        SurfaceBoundary sb4 = pmFF.createSurfaceBoundary(extRing4, new ArrayList<Ring>());
        SurfaceBoundary sb5 = pmFF.createSurfaceBoundary(extRing5, new ArrayList<Ring>());
        SurfaceBoundary sb6 = pmFF.createSurfaceBoundary(extRing6, new ArrayList<Ring>());

        // create the surface
        Surface surface1 = pmFF.createSurface(sb1);
        Surface surface2 = pmFF.createSurface(sb2);
        Surface surface3 = pmFF.createSurface(sb3);
        Surface surface4 = pmFF.createSurface(sb4);
        Surface surface5 = pmFF.createSurface(sb5);
        Surface surface6 = pmFF.createSurface(sb6);

        List<OrientableSurface> surfaces = new ArrayList<OrientableSurface>();
        surfaces.add(surface1);
        surfaces.add(surface2);
        surfaces.add(surface3);
        surfaces.add(surface4);
        surfaces.add(surface5);
        surfaces.add(surface6);
        
        Shell exteriorShell = pmFF.createShell(surfaces);
        List<Shell> interiors = new ArrayList<Shell>();
        
        SolidBoundary solidBoundary = pmFF.createSolidBoundary(exteriorShell, interiors);
        Solid solid = pmFF.createSolid(solidBoundary);

        return solid;
    }
}
