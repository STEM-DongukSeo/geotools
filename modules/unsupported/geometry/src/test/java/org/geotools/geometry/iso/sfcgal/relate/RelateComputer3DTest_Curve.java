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

import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.Solid;

import junit.framework.TestCase;

/**
 * @author Donguk Seo
 *
 */
public class RelateComputer3DTest_Curve extends TestCase {

    public static ArrayList<Curve> getCurves(GeometryBuilder builder) {
        ArrayList<Curve> curves = new ArrayList<Curve>();
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

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

        curves.add(makeCurve(builder, position7, position8));
        curves.add(makeCurve(builder, position2, position4));
        curves.add(makeCurve(builder, position2, position6));
        
        List<DirectPosition> positions1 = new ArrayList<DirectPosition>();
        positions1.add(position2);
        positions1.add(position5);
        positions1.add(position7);
        curves.add(pmF.createCurveByDirectPositions(positions1));
        curves.add(makeCurve(builder, position1, position6));

        List<DirectPosition> positions2 = new ArrayList<DirectPosition>();
        positions2.add(position1);
        positions2.add(position3);
        positions2.add(position6);
        curves.add(pmF.createCurveByDirectPositions(positions2));
        curves.add(makeCurve(builder, position9, position12));
        curves.add(makeCurve(builder, position9, position7));
        curves.add(makeCurve(builder, position10, position12));

        List<DirectPosition> positions3 = new ArrayList<DirectPosition>();
        positions3.add(position10);
        positions3.add(position2);
        positions3.add(position4);
        positions3.add(position12);
        curves.add(pmF.createCurveByDirectPositions(positions3));

        List<DirectPosition> positions5 = new ArrayList<DirectPosition>();
        positions5.add(position22);
        positions5.add(position20);
        positions5.add(position21);
        positions5.add(position23);
        curves.add(pmF.createCurveByDirectPositions(positions5));

        List<DirectPosition> positions6 = new ArrayList<DirectPosition>();
        positions6.add(position24);
        positions6.add(position2);
        positions6.add(position4);
        positions6.add(position25);
        curves.add(pmF.createCurveByDirectPositions(positions6));
        
        return curves;
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
    
    @Before
    protected void setUp() throws Exception {
        super.setUp();
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
