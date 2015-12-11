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
package org.geotools.geometry.iso.sfcgal.relate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.geotools.geometry.iso.primitive.SurfaceImpl;
import org.geotools.geometry.iso.root.GeometryImpl;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.Shell;
import org.opengis.geometry.primitive.Solid;
import org.opengis.geometry.primitive.SolidBoundary;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;

/**
 * @author Donguk Seo
 *
 */
public class RelateComputer3DTest extends TestCase {
    private static GeometryBuilder builder = null;
    
    public void testMain() {
        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        builder = new GeometryBuilder(hints);
        
        //_testPointPoint();
        //_testPointCurve();
        _testPointSurface();
        //_testPointSolid();
        //_testCurveCurve();
        //_testCurveSurface();
        //_testCurveSolid();
        //_testSurfaceSurface();
        //_testSurfaceSolid();
        //_testSolidSolid();
    }
    
    public void _testPointPoint() {
        Point point1 = builder.createPoint(0, 0, 0);
        Point point2 = builder.createPoint(0, 0, 0);
        Point point3 = builder.createPoint(0, 1, 0);
                
        System.out.println(point1.toString());
        System.out.println(point2.toString());
        relateTest(point1, point2);
        
        System.out.println(point3.toString());
        relateTest(point1, point3);
    }
    
    public void _testPointCurve() {
        Point point = builder.createPoint(0, -1,  2);
        ArrayList<Curve> curves = getCurves(builder);
        
        System.out.println(point.toString());
        for(Curve curve : curves) {
            System.out.println(curve.toString());
            relateTest(point, curve);
        }
    }
    
    public void _testPointSurface() {
        Point point = builder.createPoint(0, -1, 2);
        ArrayList<Surface> surfaces = getSurfaces(builder);
        
        System.out.println(point.toString());
        for(Surface surface : surfaces) {
            System.out.println(surface.toString());
            relateTest(point, surface);
        }
    }
    
    public void _testPointSolid() {
        Point point = builder.createPoint(0, -1,  0);
        ArrayList<Solid> solids = getSolids(builder);
        
        System.out.println(point.toString());
        for(Solid solid : solids) {
            System.out.println(solid.toString());
            relateTest(point, solid);
        }
    }
    
    public void _testCurveCurve() {
        ArrayList<Curve> curves = getCurves(builder);
        Curve curve = curves.get(0);
        
        System.out.println(curve.toString());
        for(int i = 0; i < curves.size(); i++) {
            System.out.println("------- Test -------");
            System.out.println(curves.get(i).toString());
            relateTest(curve, curves.get(i));
        }
    }
    
    public void _testCurveSurface() {
        Surface surface = getSurfaces(builder).get(0);
        ArrayList<Curve> curves = getCurves(builder);
        
        System.out.println(surface.toString());
        for(Curve curve : curves) {
            System.out.println("------- Test -------");
            System.out.println(curve.toString());
            relateTest(curve, surface);
        }
    }
    
    public void _testCurveSolid() {
        Solid solid = getSolids(builder).get(0);
        ArrayList<Curve> curves = getCurves(builder);
        
        System.out.println(solid.toString());
        for(Curve curve : curves) {
            System.out.println("------- Test -------");
            System.out.println(curve.toString());
            relateTest(curve, solid);
        }
    }
    
    public void _testSurfaceSurface() {
        ArrayList<Surface> surfaces = getSurfaces(builder);
        Surface surface = surfaces.get(0);
        
        System.out.println(surface.toString());
        for(int i = 0; i < surfaces.size(); i++) {
            System.out.println("------- Test -------");            
            System.out.println(surfaces.get(i).toString());
            relateTest(surface, surfaces.get(i));
        }
    }
    
    public void _testSurfaceSolid() {
        Solid solid = getSolids(builder).get(0);
        ArrayList<Surface> surfaces = getSurfaces(builder);
        
        System.out.println(solid.toString());
        for(Surface surface : surfaces) {
            System.out.println("------- Test -------");
            System.out.println(surface.toString());
            relateTest(surface, solid);
        }
        
    }
    
    public void _testSolidSolid() {
        ArrayList<Solid> solids = getSolids(builder);
        Solid solid = solids.get(0);
        
        System.out.println(solid.toString());
        for(int i = 0; i < solids.size(); i++) {
            System.out.println("------- Test " + i + " -------");
            System.out.println("Solid " + i + " : " + solids.get(i).toString());
            relateTest(solid, solids.get(i));
        }
        
    }    
    
    public void relateTest(Geometry gA, Geometry gB) {
        System.out.println("equals : " + gA.equals(gB));
        System.out.println("intersects : " + gA.intersects(gB));
        System.out.println("disjoint : " + ((GeometryImpl) gA).disjoint(gB));
        System.out.println("touches : " + ((GeometryImpl) gA).touches(gB));
        System.out.println("contains : " + gA.contains(gB));
        System.out.println("within : " + ((GeometryImpl) gA).within(gB));
        System.out.println("crosses : " + ((GeometryImpl) gA).crosses(gB));
        System.out.println("overlaps : " + ((GeometryImpl) gA).overlaps(gB));
        
        
        System.out.println("union : " + gA.union(gB).toString());
        System.out.println("difference : " + gA.difference(gB).toString());
        System.out.println("intersection : " + gA.intersection(gB).toString());
        System.out.println("symmetric difference : " + gA.symmetricDifference(gB).toString());
    }    
    
    public static Curve makeCurve(GeometryBuilder builder, DirectPosition position1,
            DirectPosition position2) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        List<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position1);
        positions1.add(position2);
        Curve curve = pmF.createCurveByDirectPositions(positions1);

        return curve;
    }
    
    public static ArrayList<Curve> getCurves(GeometryBuilder builder) {
        ArrayList<Curve> curves = new ArrayList<Curve>();
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        DirectPosition position1 = builder.createDirectPosition(new double[] { -1, -1, 2 });
        DirectPosition tp1 = builder.createDirectPosition(new double[] { 0, -1, 2 });
        DirectPosition tp2 = builder.createDirectPosition(new double[] { 2, -1, 2 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 3, -1, 2 });
        
        DirectPosition position3 = builder.createDirectPosition(new double[] { 0, -1, 1 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 2, -1, 1 });
        
        DirectPosition position5 = builder.createDirectPosition(new double[] { 1, -1, 3 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 1, -1, -1 });
        
        DirectPosition position7 = builder.createDirectPosition(new double[] { 1, -1, 3 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { -1, -1, 1 });
        DirectPosition position9 = builder.createDirectPosition(new double[] { 1, -1, -1 });
        
        DirectPosition position10 = builder.createDirectPosition(new double[] { 0, -1, 1 });
        DirectPosition position11 = builder.createDirectPosition(new double[] { 1, -1, 2 });
        DirectPosition position12 = builder.createDirectPosition(new double[] { 2, -1, 1 });
        
        DirectPosition position13 = builder.createDirectPosition(new double[] { -0.5, -1, 0 });
        DirectPosition position14 = builder.createDirectPosition(new double[] { 1, -1, 3 });
        DirectPosition position15 = builder.createDirectPosition(new double[] { 2.5, -1, 0 });
        
        DirectPosition position16 = builder.createDirectPosition(new double[] { 0.5, -1, 2 });
        DirectPosition position17 = builder.createDirectPosition(new double[] { 1.5, -1, 2 });

        List<DirectPosition> tps1 = new ArrayList<DirectPosition>();
        tps1.add(position1);
        tps1.add(tp1);
        tps1.add(tp2);
        tps1.add(position2);
        curves.add(makeCurve(builder, position1, position2));
        //curves.add(pmF.createCurveByDirectPositions(tps1));
        curves.add(makeCurve(builder, position3, position4));
        curves.add(makeCurve(builder, position5, position6));
        
        List<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position7);
        positions1.add(position8);
        positions1.add(position9);
        curves.add(pmF.createCurveByDirectPositions(positions1));

        List<DirectPosition> positions2 = new ArrayList<DirectPosition>();
        positions2.add(position10);
        positions2.add(position11);
        positions2.add(position12);
        curves.add(pmF.createCurveByDirectPositions(positions2));

        List<DirectPosition> positions3 = new ArrayList<DirectPosition>();
        positions3.add(position13);
        positions3.add(position14);
        positions3.add(position15);
        curves.add(pmF.createCurveByDirectPositions(positions3));
        
        curves.add(makeCurve(builder, position16, position17));

        return curves;
    }

    public static Surface makeSurface(GeometryBuilder builder, List<DirectPosition> positions) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        SurfaceImpl surf = pmF.createSurfaceByDirectPositions(positions);

        return surf;
    }
    
    public static ArrayList<Surface> getSurfaces(GeometryBuilder builder) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

        DirectPosition position1 = builder.createDirectPosition(new double[] { 0, 0, 2 });
        DirectPosition position2 = builder.createDirectPosition(new double[] { 0, -2, 2 });
        DirectPosition position3 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition position4 = builder.createDirectPosition(new double[] { 2, 0, 2 });
        
        DirectPosition position5 = builder.createDirectPosition(new double[] { 0, 0, 1 });
        DirectPosition position6 = builder.createDirectPosition(new double[] { 0, -2, 1 });
        DirectPosition position7 = builder.createDirectPosition(new double[] { 2, -2, 1 });
        DirectPosition position8 = builder.createDirectPosition(new double[] { 2, 0, 1 });
        
        DirectPosition position9 = builder.createDirectPosition(new double[] { 1, 0, 1 });        
        DirectPosition position10 = builder.createDirectPosition(new double[] { 1, -2, 1 });
        DirectPosition position11 = builder.createDirectPosition(new double[] { 3, -2, 1 });
        DirectPosition position12 = builder.createDirectPosition(new double[] { 3, 0, 1 });
        
        DirectPosition position13 = builder.createDirectPosition(new double[] { -1, -1, 1 });
        DirectPosition position14 = builder.createDirectPosition(new double[] { 1, -1, 3 });
        DirectPosition position15 = builder.createDirectPosition(new double[] { 1, 0.5, 3 });
        DirectPosition position16 = builder.createDirectPosition(new double[] { -1, 0.5, 1 });
        
        DirectPosition position17 = builder.createDirectPosition(new double[] { 0, -0.5, 0 });
        DirectPosition position18 = builder.createDirectPosition(new double[] { 0, -1.5, 0 });
        DirectPosition position19 = builder.createDirectPosition(new double[] { 2, -1.5, 2 });
        DirectPosition position20 = builder.createDirectPosition(new double[] { 2, -0.5, 2 });
        
        DirectPosition position21 = builder.createDirectPosition(new double[] { 1.5, -0.5, 0.5 });
        DirectPosition position22 = builder.createDirectPosition(new double[] { 1.5, -1.5, 0.5 });
        DirectPosition position23 = builder.createDirectPosition(new double[] { 3, -1.5, 2 });
        DirectPosition position24 = builder.createDirectPosition(new double[] { 3, -0.5, 2 });

        // touches
        ArrayList<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position1);
        positions1.add(position2);
        positions1.add(position3);
        positions1.add(position4);
        positions1.add(position1);
        ArrayList<DirectPosition> positions2 = new ArrayList<DirectPosition>();
        positions2.add(position5);
        positions2.add(position6);
        positions2.add(position7);
        positions2.add(position8);
        positions2.add(position5);
        ArrayList<DirectPosition> positions3 = new ArrayList<DirectPosition>();
        positions3.add(position9);
        positions3.add(position10);
        positions3.add(position11);
        positions3.add(position12);
        positions3.add(position9);
        ArrayList<DirectPosition> positions4 = new ArrayList<DirectPosition>();
        positions4.add(position13);
        positions4.add(position14);
        positions4.add(position15);
        positions4.add(position16);
        positions4.add(position13);
        // contains
        ArrayList<DirectPosition> positions5 = new ArrayList<DirectPosition>();
        positions5.add(position17);
        positions5.add(position18);
        positions5.add(position19);
        positions5.add(position20);
        positions5.add(position17);
        ArrayList<DirectPosition> positions6 = new ArrayList<DirectPosition>();
        positions6.add(position21);
        positions6.add(position22);
        positions6.add(position23);
        positions6.add(position24);
        positions6.add(position21);
        
        ArrayList<Surface> surfaces = new ArrayList<Surface>();
        surfaces.add(makeSurface(builder, positions1));
        surfaces.add(makeSurface(builder, positions2));
        surfaces.add(makeSurface(builder, positions3));
        surfaces.add(makeSurface(builder, positions4));
        surfaces.add(makeSurface(builder, positions5));
        surfaces.add(makeSurface(builder, positions6));

        return surfaces;
    }
    
    public static ArrayList<ArrayList<DirectPosition>> getSolidPoints(GeometryBuilder builder) {
        ArrayList<ArrayList<DirectPosition>> solidPoints = new ArrayList<ArrayList<DirectPosition>>();
        
        DirectPosition p1 = builder.createDirectPosition(new double[] { 0, 0, 0 });
        DirectPosition p2 = builder.createDirectPosition(new double[] { 0, -2, 0 });
        DirectPosition p3 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition p4 = builder.createDirectPosition(new double[] { 2, 0, 0 });
        DirectPosition p5 = builder.createDirectPosition(new double[] { 0, 0, 2 });
        DirectPosition p6 = builder.createDirectPosition(new double[] { 0, -2, 2 });
        DirectPosition p7 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition p8 = builder.createDirectPosition(new double[] { 2, 0, 2 });

        ArrayList<DirectPosition> points1 = new ArrayList<DirectPosition>();
        points1.add(p1);
        points1.add(p2);
        points1.add(p3);
        points1.add(p4);
        points1.add(p5);
        points1.add(p6);
        points1.add(p7);
        points1.add(p8);

        //
        DirectPosition p11 = builder.createDirectPosition(new double[] { 2, 0, 0 });
        DirectPosition p12 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition p13 = builder.createDirectPosition(new double[] { 4, -2, 0 });
        DirectPosition p14 = builder.createDirectPosition(new double[] { 4, 0, 0 });
        DirectPosition p15 = builder.createDirectPosition(new double[] { 2, 0, 2 });
        DirectPosition p16 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition p17 = builder.createDirectPosition(new double[] { 4, -2, 2 });
        DirectPosition p18 = builder.createDirectPosition(new double[] { 4, 0, 2 });

        ArrayList<DirectPosition> points2 = new ArrayList<DirectPosition>();
        points2.add(p11);
        points2.add(p12);
        points2.add(p13);
        points2.add(p14);
        points2.add(p15);
        points2.add(p16);
        points2.add(p17);
        points2.add(p18);

        //
        DirectPosition p21 = builder.createDirectPosition(new double[] { 2, 0, 2 });
        DirectPosition p22 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition p23 = builder.createDirectPosition(new double[] { 4, -2, 2 });
        DirectPosition p24 = builder.createDirectPosition(new double[] { 4, 0, 2 });
        DirectPosition p25 = builder.createDirectPosition(new double[] { 2, 0, 4 });
        DirectPosition p26 = builder.createDirectPosition(new double[] { 2, -2, 4 });
        DirectPosition p27 = builder.createDirectPosition(new double[] { 4, -2, 4 });
        DirectPosition p28 = builder.createDirectPosition(new double[] { 4, 0, 4 });
        
        ArrayList<DirectPosition> points3 = new ArrayList<DirectPosition>();
        points3.add(p21);
        points3.add(p22);
        points3.add(p23);
        points3.add(p24);
        points3.add(p25);
        points3.add(p26);
        points3.add(p27);
        points3.add(p28);
                
        
        DirectPosition p31 = builder.createDirectPosition(new double[] { 1, 0, 0 });
        DirectPosition p32 = builder.createDirectPosition(new double[] { 1, -2, 0 });
        DirectPosition p33 = builder.createDirectPosition(new double[] { 3, -2, 0 });
        DirectPosition p34 = builder.createDirectPosition(new double[] { 3, 0, 0 });
        DirectPosition p35 = builder.createDirectPosition(new double[] { 1, 0, 2 });
        DirectPosition p36 = builder.createDirectPosition(new double[] { 1, -2, 2 });
        DirectPosition p37 = builder.createDirectPosition(new double[] { 3, -2, 2 });
        DirectPosition p38 = builder.createDirectPosition(new double[] { 3, 0, 2 });    

        ArrayList<DirectPosition> points4 = new ArrayList<DirectPosition>();
        points4.add(p31);
        points4.add(p32);
        points4.add(p33);
        points4.add(p34);
        points4.add(p35);
        points4.add(p36);
        points4.add(p37);
        points4.add(p38);
        

        DirectPosition p41 = builder.createDirectPosition(new double[] { -1, 0, -1 });
        DirectPosition p42 = builder.createDirectPosition(new double[] { -1, -2, -1 });
        DirectPosition p43 = builder.createDirectPosition(new double[] { 3, -2, -1 });
        DirectPosition p44 = builder.createDirectPosition(new double[] { 3, 0, -1 });
        DirectPosition p45 = builder.createDirectPosition(new double[] { -1, 0, 3 });
        DirectPosition p46 = builder.createDirectPosition(new double[] { -1, -2, 3 });
        DirectPosition p47 = builder.createDirectPosition(new double[] { 3, -2, 3 });
        DirectPosition p48 = builder.createDirectPosition(new double[] { 3, 0, 3 });
        
        ArrayList<DirectPosition> points5 = new ArrayList<DirectPosition>();
        points5.add(p41);
        points5.add(p42);
        points5.add(p43);
        points5.add(p44);
        points5.add(p45);
        points5.add(p46);
        points5.add(p47);
        points5.add(p48);
        
        DirectPosition p51 = builder.createDirectPosition(new double[] { 0.5, -0.5, 0.5 });
        DirectPosition p52 = builder.createDirectPosition(new double[] { 0.5, -1.5, 0.5 });
        DirectPosition p53 = builder.createDirectPosition(new double[] { 1.5, -1.5, 0.5 });
        DirectPosition p54 = builder.createDirectPosition(new double[] { 1.5, -0.5, 0.5 });
        DirectPosition p55 = builder.createDirectPosition(new double[] { 0.5, -0.5, 1.5 });
        DirectPosition p56 = builder.createDirectPosition(new double[] { 0.5, -1.5, 1.5 });
        DirectPosition p57 = builder.createDirectPosition(new double[] { 1.5, -1.5, 1.5 });
        DirectPosition p58 = builder.createDirectPosition(new double[] { 1.5, -0.5, 1.5 });
        
        ArrayList<DirectPosition> points6 = new ArrayList<DirectPosition>();
        points6.add(p51);
        points6.add(p52);
        points6.add(p53);
        points6.add(p54);
        points6.add(p55);
        points6.add(p56);
        points6.add(p57);
        points6.add(p58);

        DirectPosition p61 = builder.createDirectPosition(new double[] { 1, -1, 0 });
        DirectPosition p62 = builder.createDirectPosition(new double[] { 1, -2, 0 });
        DirectPosition p63 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition p64 = builder.createDirectPosition(new double[] { 2, -1, 0 });
        DirectPosition p65 = builder.createDirectPosition(new double[] { 1, -1, 1 });
        DirectPosition p66 = builder.createDirectPosition(new double[] { 1, -2, 1 });
        DirectPosition p67 = builder.createDirectPosition(new double[] { 2, -2, 1 });
        DirectPosition p68 = builder.createDirectPosition(new double[] { 2, -1, 1 });

        ArrayList<DirectPosition> points7 = new ArrayList<DirectPosition>();
        points7.add(p61);
        points7.add(p62);
        points7.add(p63);
        points7.add(p64);
        points7.add(p65);
        points7.add(p66);
        points7.add(p67);
        points7.add(p68);

        solidPoints.add(points1);
        solidPoints.add(points2);
        solidPoints.add(points3);
        solidPoints.add(points4);
        solidPoints.add(points5);
        solidPoints.add(points6);
        solidPoints.add(points7);
        
        return solidPoints;
    }
    
    public static Solid makeSolid(GeometryBuilder builder, ArrayList<DirectPosition> points) {
        DirectPosition position1 = points.get(0);
        DirectPosition position2 = points.get(1);
        DirectPosition position3 = points.get(2);
        DirectPosition position4 = points.get(3);
        DirectPosition position5 = points.get(4);
        DirectPosition position6 = points.get(5);
        DirectPosition position7 = points.get(6);
        DirectPosition position8 = points.get(7);
        
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
    
    public static ArrayList<Solid> getSolids(GeometryBuilder builder) {
        ArrayList<Solid> solids = new ArrayList<Solid>();
        ArrayList<ArrayList<DirectPosition>> solidPoints = getSolidPoints(builder);
        
        for(int i = 0; i < 7; i++) {
            solids.add(makeSolid(builder, solidPoints.get(i)));
        }
        
        return solids;
    }
}
