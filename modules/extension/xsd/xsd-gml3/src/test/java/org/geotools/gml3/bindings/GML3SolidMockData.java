/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2016, Open Source Geospatial Foundation (OSGeo)
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

import org.opengis.geometry.primitive.Solid;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Donguk
 *
 */
public class GML3SolidMockData extends GML3MockData {
    //static PrimitiveFactoryImpl pf;
    
    /* 
     * for SolidTypeBindingTest
     * */
    public static Element compositeSurfaceWithValidity(Document document, Node parent) {
        Element compositeSurface = element(qName("CompositeSurface"), document, parent);
        Element surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* right surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("2 0 0 2 2 0 2 2 2 2 0 2 2 0 0"));
        }
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* back surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("2 2 0 0 2 0 0 2 2 2 2 2 2 2 0"));
        }
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* left surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("0 0 0 0 0 2 0 2 2 0 2 0 0 0 0"));
        }
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* front surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("0 0 0 2 0 0 2 0 2 0 0 2 0 0 0"));
        }
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* lower surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("0 0 0 0 2 0 2 2 0 2 0 0 0 0 0"));
        }
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        {
            /* upper surface */
            Element polygon = element(qName("Polygon"), document, surfaceMember);
            polygon.setAttribute("srsDimension", "3");
    
            Element exterior = element(qName("exterior"), document, polygon);
            Element linearRing = element(qName("LinearRing"), document, exterior);
            linearRing.setAttribute("srsDimension", "3");
            Element posList = element(qName("posList"), document, linearRing);
            linearRing.appendChild(posList);
            posList.appendChild(document.createTextNode("0 0 2 2 0 2 2 2 2 0 2 2 0 0 2"));
        }
        
        
        return compositeSurface;
    }
    
    public static Element compositeSurface(Document document, Node parent) {
        Element compositeSurface = element(qName("CompositeSurface"), document, parent);
        Element surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        polygonWithPosList3D(document, surfaceMember, true);
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        polygonWithPosList3D(document, surfaceMember, true);
        
        surfaceMember = element(qName("surfaceMember"), document, compositeSurface);
        polygonWithPosList3D(document, surfaceMember, true);
        
        return compositeSurface;
    }
    
    public static Element solid(Document document, Node parent) {
        return solid(document, parent, false);
    }
    
    public static Element solid(Document document, Node parent, boolean withInterior) {
        Element solid = element(qName("Solid"), document, parent);
        solid.setAttribute("srsDimension", "3");
        
        Element exterior = element(qName("exterior"), document, solid);
        //compositeSurface(document, exterior);
        compositeSurfaceWithValidity(document, exterior);
        
        if(withInterior) {
            Element interior = element(qName("interior"), document, solid);
            compositeSurface(document, interior);
        }
        
        return solid;
    }
    
    public static Solid solid() {
        
        return null;
    }
    /* */
}
