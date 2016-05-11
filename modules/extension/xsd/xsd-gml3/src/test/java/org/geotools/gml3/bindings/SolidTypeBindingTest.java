/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.gml3.bindings;

import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.geotools.gml3.GML3TestSupportExtendedArcSurface;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.xml.Configuration;
import org.opengis.geometry.primitive.PrimitiveFactory;
import org.opengis.geometry.primitive.Solid;


/**
 * 
 *
 * @source $URL$
 */
public class SolidTypeBindingTest extends GML3TestSupportExtendedArcSurface {
/*
    public void testEncode() throws Exception {
        Geometry geometry = GML3MockData.multiPolygon();
        GML3EncodingUtils.setID(geometry, "geometry");
        Document dom = encode(geometry, GML.MultiSurface);
        // print(dom);
        assertEquals("geometry", getID(dom.getDocumentElement()));
        assertEquals(2, dom.getElementsByTagNameNS(GML.NAMESPACE, "surfaceMember").getLength());
        NodeList children = dom.getElementsByTagNameNS(GML.NAMESPACE, GML.Polygon.getLocalPart());
        assertEquals(2, children.getLength());
        assertEquals("geometry.1", getID(children.item(0)));
        assertEquals("geometry.2", getID(children.item(1)));
    }
*/
    @Override
    protected Configuration createConfiguration() {
        Hints hints = GeoTools.getDefaultHints();
        hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
        hints.put(Hints.GEOMETRY_VALIDATE, false);
        //GeometryBuilder geometryBuilder = new GeometryBuilder(hints);
        //PrimitiveFactory primitiveFactory = geometryBuilder.getPrimitiveFactory();
        PrimitiveFactory primitiveFactory = new PrimitiveFactoryImpl(hints);
        
        GMLConfiguration configuration = (GMLConfiguration) super.createConfiguration();
        configuration.setPrimitiveFactory(primitiveFactory);
        
        return configuration;
    }
    
    public void testParse() throws Exception {
        GML3MockData.solid(document, document);
        Solid solid = (Solid) parse();
        
        assertNotNull(solid);
        System.out.println(solid.toString());
        
        double volume = solid.getVolume();
        System.out.println(volume);
    }
    
    
}
