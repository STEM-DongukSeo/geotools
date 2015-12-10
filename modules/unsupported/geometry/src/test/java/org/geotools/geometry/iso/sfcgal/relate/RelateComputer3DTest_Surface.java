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

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.geotools.geometry.iso.primitive.SurfaceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Solid;
import org.opengis.geometry.primitive.Surface;

/**
 * @author Donguk Seo
 *
 */
public class RelateComputer3DTest_Surface extends TestCase {

    public static ArrayList<Surface> getSurfaces(GeometryBuilder builder) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();

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
        positions4.add(position13);
        positions4.add(position12);
        positions4.add(position7);
        positions4.add(position4);
        // contains
        ArrayList<DirectPosition> positions5 = new ArrayList<DirectPosition>();
        positions5.add(position20);
        positions5.add(position23);
        positions5.add(position19);
        positions5.add(position16);
        positions5.add(position20);
        ArrayList<DirectPosition> positions6 = new ArrayList<DirectPosition>();
        positions6.add(position20);
        positions6.add(position22);
        positions6.add(position18);
        positions6.add(position16);
        positions6.add(position20);
        ArrayList<DirectPosition> positions7 = new ArrayList<DirectPosition>();
        positions7.add(position21);
        positions7.add(position22);
        positions7.add(position18);
        positions7.add(position17);
        positions7.add(position21);
        ArrayList<DirectPosition> positions8 = new ArrayList<DirectPosition>();
        positions8.add(position27);
        positions8.add(position28);
        positions8.add(position6);
        positions8.add(position3);
        positions8.add(position27);
        // crosses
        ArrayList<DirectPosition> positions9 = new ArrayList<DirectPosition>();
        positions9.add(position15);
        positions9.add(position26);
        positions9.add(position25);
        positions9.add(position14);
        positions9.add(position15);
        ArrayList<DirectPosition> positions10 = new ArrayList<DirectPosition>();
        positions10.add(position21);
        positions10.add(position26);
        positions10.add(position25);
        positions10.add(position17);
        positions10.add(position21);

        ArrayList<Surface> surfaces = new ArrayList<Surface>();
        surfaces.add(makeSurface(builder, positions1));
        surfaces.add(makeSurface(builder, positions2));
        surfaces.add(makeSurface(builder, positions3));
        surfaces.add(makeSurface(builder, positions4));
        surfaces.add(makeSurface(builder, positions5));
        surfaces.add(makeSurface(builder, positions6));
        surfaces.add(makeSurface(builder, positions7));
        surfaces.add(makeSurface(builder, positions8));
        surfaces.add(makeSurface(builder, positions9));
        surfaces.add(makeSurface(builder, positions10));

        return surfaces;
    }

    public static Surface makeSurface(GeometryBuilder builder, List<DirectPosition> positions) {
        PrimitiveFactoryImpl pmF = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        SurfaceImpl surf = pmF.createSurfaceByDirectPositions(positions);

        return surf;
    }
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        //fail("Not yet implemented");
        
    }

}
