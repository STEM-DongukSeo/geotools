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

import junit.framework.TestCase;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;

/**
 * @author Donguk Seo
 *
 */
public class RelateComputer3DTest_Point extends TestCase {
    private static GeometryBuilder builder = null;
    
    public static ArrayList<DirectPosition> getPoints(GeometryBuilder builder, int type) {
        builder.createDirectPosition(new double[] { 0, 0, 0 });
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
        DirectPosition p11 = builder.createDirectPosition(new double[] { 4, 0, 0 });
        DirectPosition p12 = builder.createDirectPosition(new double[] { 4, -2, 0 });
        DirectPosition p13 = builder.createDirectPosition(new double[] { 6, -2, 0 });
        DirectPosition p14 = builder.createDirectPosition(new double[] { 6, 0, 0 });
        DirectPosition p15 = builder.createDirectPosition(new double[] { 4, 0, 2 });
        DirectPosition p16 = builder.createDirectPosition(new double[] { 4, -2, 2 });
        DirectPosition p17 = builder.createDirectPosition(new double[] { 6, -2, 2 });
        DirectPosition p18 = builder.createDirectPosition(new double[] { 6, 0, 2 });

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
        DirectPosition p19 = builder.createDirectPosition(new double[] { 5, 0, 0 });
        DirectPosition p20 = builder.createDirectPosition(new double[] { 5, -2, 0 });
        DirectPosition p21 = builder.createDirectPosition(new double[] { 7, -2, 0 });
        DirectPosition p22 = builder.createDirectPosition(new double[] { 7, 0, 0 });
        DirectPosition p23 = builder.createDirectPosition(new double[] { 5, 0, 2 });
        DirectPosition p24 = builder.createDirectPosition(new double[] { 5, -2, 2 });
        DirectPosition p25 = builder.createDirectPosition(new double[] { 7, -2, 2 });
        DirectPosition p26 = builder.createDirectPosition(new double[] { 7, 0, 2 });

        ArrayList<DirectPosition> points3 = new ArrayList<DirectPosition>();
        points3.add(p19);
        points3.add(p20);
        points3.add(p21);
        points3.add(p22);
        points3.add(p23);
        points3.add(p24);
        points3.add(p25);
        points3.add(p26);

        DirectPosition p27 = builder.createDirectPosition(new double[] { 2, 0, 0 });
        DirectPosition p28 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition p29 = builder.createDirectPosition(new double[] { 4, -2, 0 });
        DirectPosition p30 = builder.createDirectPosition(new double[] { 4, 0, 0 });
        DirectPosition p31 = builder.createDirectPosition(new double[] { 2, 0, 2 });
        DirectPosition p32 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition p33 = builder.createDirectPosition(new double[] { 4, -2, 2 });
        DirectPosition p34 = builder.createDirectPosition(new double[] { 4, 0, 2 });

        ArrayList<DirectPosition> points4 = new ArrayList<DirectPosition>();
        points4.add(p27);
        points4.add(p28);
        points4.add(p29);
        points4.add(p30);
        points4.add(p31);
        points4.add(p32);
        points4.add(p33);
        points4.add(p34);

        DirectPosition p35 = builder.createDirectPosition(new double[] { 0.5, -0.5, 0.5 });
        DirectPosition p36 = builder.createDirectPosition(new double[] { 0.5, -1.5, 0.5 });
        DirectPosition p37 = builder.createDirectPosition(new double[] { 1.5, -1.5, 0.5 });
        DirectPosition p38 = builder.createDirectPosition(new double[] { 1.5, -0.5, 0.5 });
        DirectPosition p39 = builder.createDirectPosition(new double[] { 0.5, -0.5, 1.5 });
        DirectPosition p40 = builder.createDirectPosition(new double[] { 0.5, -1.5, 1.5 });
        DirectPosition p41 = builder.createDirectPosition(new double[] { 1.5, -1.5, 1.5 });
        DirectPosition p42 = builder.createDirectPosition(new double[] { 1.5, -0.5, 1.5 });

        ArrayList<DirectPosition> points5 = new ArrayList<DirectPosition>();
        points5.add(p35);
        points5.add(p36);
        points5.add(p37);
        points5.add(p38);
        points5.add(p39);
        points5.add(p40);
        points5.add(p41);
        points5.add(p42);

        DirectPosition p43 = builder.createDirectPosition(new double[] { 2, 0, 2 });
        DirectPosition p44 = builder.createDirectPosition(new double[] { 2, -2, 2 });
        DirectPosition p45 = builder.createDirectPosition(new double[] { 4, -2, 2 });
        DirectPosition p46 = builder.createDirectPosition(new double[] { 4, 0, 2 });
        DirectPosition p47 = builder.createDirectPosition(new double[] { 2, 0, 4 });
        DirectPosition p48 = builder.createDirectPosition(new double[] { 2, -2, 4 });
        DirectPosition p49 = builder.createDirectPosition(new double[] { 4, -2, 4 });
        DirectPosition p50 = builder.createDirectPosition(new double[] { 4, 0, 4 });

        ArrayList<DirectPosition> points6 = new ArrayList<DirectPosition>();
        points6.add(p43);
        points6.add(p44);
        points6.add(p45);
        points6.add(p46);
        points6.add(p47);
        points6.add(p48);
        points6.add(p49);
        points6.add(p50);

        DirectPosition p51 = builder.createDirectPosition(new double[] { 1, -1, 0 });
        DirectPosition p52 = builder.createDirectPosition(new double[] { 1, -2, 0 });
        DirectPosition p53 = builder.createDirectPosition(new double[] { 2, -2, 0 });
        DirectPosition p54 = builder.createDirectPosition(new double[] { 2, -1, 0 });
        DirectPosition p55 = builder.createDirectPosition(new double[] { 1, -1, 1 });
        DirectPosition p56 = builder.createDirectPosition(new double[] { 1, -2, 1 });
        DirectPosition p57 = builder.createDirectPosition(new double[] { 2, -2, 1 });
        DirectPosition p58 = builder.createDirectPosition(new double[] { 2, -1, 1 });

        ArrayList<DirectPosition> points7 = new ArrayList<DirectPosition>();
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

    public void testMain() {
        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        builder = new GeometryBuilder(hints);
        
        _testPoint(builder);
        _testCurve(builder);
        _testSurface(builder);
        _testSolid(builder);
    }
    
    public void _testPoint(GeometryBuilder builder) {
        
    }
    
    public void _testCurve(GeometryBuilder builder) {
        ArrayList<Curve> curves = RelateComputer3DTest_Curve.getCurves(builder);
    }
    
    public void _testSurface(GeometryBuilder builder) {
        
    }
    
    public void _testSolid(GeometryBuilder builder) {
        
    }
    
}
