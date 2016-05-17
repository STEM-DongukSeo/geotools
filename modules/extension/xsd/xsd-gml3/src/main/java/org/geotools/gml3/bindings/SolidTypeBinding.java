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

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl;
import org.geotools.geometry.jts.JTSUtils;
import org.geotools.gml3.GML;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.UnsupportedImplementationException;
import org.geotools.xml.AbstractComplexBinding;
import org.geotools.xml.ElementInstance;
import org.geotools.xml.Node;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.PrimitiveFactory;
import org.opengis.geometry.primitive.Shell;
import org.opengis.geometry.primitive.Solid;
import org.opengis.geometry.primitive.SolidBoundary;
import org.opengis.geometry.primitive.Surface;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Binding object for the type http://www.opengis.net/gml:SolidType.
 *
 * @author Donguk Seo
 *
 * @source $URL$
 */
public class SolidTypeBinding extends AbstractComplexBinding {

    protected GeometryFactory gf;
    protected PrimitiveFactory pf;
    
    /*
    public SolidTypeBinding(GeometryFactory gf) {
        this.gf = gf;
    }
    */
    public SolidTypeBinding(PrimitiveFactory pf) {
        this.pf = pf;
    }
    
    /**
     * @generated
     */
    public QName getTarget() {
        return GML.SolidType;
    }
    
    @Override
    public int getExecutionMode() {
        return BEFORE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Class getType() {
        return Solid.class;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated modifiable
     */
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        if (!(pf instanceof PrimitiveFactoryImpl)) {
            throw new UnsupportedImplementationException("This binding class depends on org.geotools.geometry.iso.primitive.PrimitiveFactoryImpl");
        }
        PrimitiveFactoryImpl pfImpl = (PrimitiveFactoryImpl) pf;
        
        com.vividsolutions.jts.geom.MultiPolygon exterior = (com.vividsolutions.jts.geom.MultiPolygon) node.getChildValue("exterior");
        com.vividsolutions.jts.geom.MultiPolygon[] interiors = null;
        
        if (node.hasChild("interior")) {
            List list = node.getChildValues("interior");
            interiors = (com.vividsolutions.jts.geom.MultiPolygon[]) list.toArray(new com.vividsolutions.jts.geom.MultiPolygon[list.size()]);
        }
        
        CoordinateReferenceSystem crs = (CoordinateReferenceSystem) exterior.getUserData();
        if (crs == null || !(crs instanceof CoordinateReferenceSystem)) {
            crs = (CoordinateReferenceSystem) DefaultGeographicCRS.WGS84_3D;
        }
        
        /**
         * Convert a Polygon from the JTSGeometry to a Surface of ISOGeometry.
         * Create the exterior Shell by the converted surfaces.
         */
        List<OrientableSurface> exteriorSurfaces = new ArrayList<OrientableSurface>();
        for (int i = 0; i < exterior.getNumGeometries(); i++) {
            Surface surface = JTSUtils.polygonToSurface((com.vividsolutions.jts.geom.Polygon) exterior.getGeometryN(i), crs);
            exteriorSurfaces.add(surface);
        }
        Shell exteriorShell = pfImpl.createShell(exteriorSurfaces);
        
        // Create the interior Shells
        List<Shell> interiorShells = null;
        if (interiors != null) {
            for (int i = 0; i < interiors.length; i++) {
                List<OrientableSurface> interiorSurfaces = new ArrayList<OrientableSurface>();
                com.vividsolutions.jts.geom.MultiPolygon interior = interiors[i];
                for (int j = 0; j < interior.getNumGeometries(); j++) {
                    Surface surface = JTSUtils.polygonToSurface((com.vividsolutions.jts.geom.Polygon) interior.getGeometryN(j), crs);
                    interiorSurfaces.add(surface);
                }
                Shell interiorShell = pfImpl.createShell(interiorSurfaces);
                interiorShells.add(interiorShell);
            }
        }
        
        // Create a SolidBoundary and a Solid Object of ISOGeometry
        SolidBoundary solidBoundary = pfImpl.createSolidBoundary(exteriorShell, interiorShells);
        Solid solid = pfImpl.createSolid(solidBoundary);
        
        return solid;
    }

    @Override
    public Object getProperty(Object object, QName name) throws Exception {
        Solid solid = (Solid) object;
        SolidBoundary boundary = solid.getBoundary();

        if ("exterior".equals(name.getLocalPart())) {
            Shell exterior = boundary.getExterior();
            List<OrientableSurface> generators = (List<OrientableSurface>) exterior.getGenerators();
            
            return boundary.getExterior();
        }

        if ("interior".equals(name.getLocalPart())) {
            return boundary.getInteriors();
        }

        return null;
    }
}
