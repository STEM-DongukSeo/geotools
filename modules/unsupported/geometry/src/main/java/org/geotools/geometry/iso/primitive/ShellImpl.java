/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2006-2008, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2006-2015, Spatio-temporal Databases Laboratory (STEMLab)
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
package org.geotools.geometry.iso.primitive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.geotools.geometry.iso.complex.CompositeSurfaceImpl;
import org.geotools.geometry.iso.io.GeometryToString;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.complex.Complex;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.geometry.primitive.Shell;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * 
 * A Shell is used to represent a single connected component of a SolidBoundary.
 * It consists of a number of references to OrientableSurfaces connected in a
 * topological cycle (an object whose boundary is empty). Unlike a Ring, a
 * Shell's elements have no natural sort order. Like Rings, Shells are simple.
 * Shell: {isSimple() = TRUE}
 * 
 * @author Jackson Roehrig & Sanjay Jena
 * @author Donguk Seo
 *
 *
 *
 *
 * @source $URL$
 */
public class ShellImpl extends CompositeSurfaceImpl implements Shell {

        private SolidBoundaryImpl solidBoundary;
        
	/**
	 * @param crs
	 * @param generator
	 */
	public ShellImpl(List<OrientableSurface> generator) {
		super(generator);
	}
	
	public ShellImpl clone() throws CloneNotSupportedException {
            Iterator<Primitive> elementIter = (Iterator<Primitive>) this.getElements().iterator();
            List<OrientableSurface> newElements = new ArrayList<OrientableSurface>();
            while(elementIter.hasNext()) {
                    newElements.add((Surface) elementIter.next().clone());
            }
            
            return new ShellImpl(newElements);
        }

	@Override
        public SurfaceBoundary getBoundary() {
                // TODO Auto-generated method stub
                return null;
        }

        @Override
        public Set<Complex> createBoundary() {
                // TODO Auto-generated method stub
                return null;
        }

        /**
         * @return Returns the solidBoundary.
         */
        public SolidBoundaryImpl getSolidBoundary() {
            return solidBoundary;
        }
        
        /**
         * @param solidBoundary
         *              The solidBoundary to set.
         */
        public void setSolidBoundary(SolidBoundaryImpl solidBoundary) {
            this.solidBoundary = solidBoundary;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.geotools.geometry.featgeom.complex.CompositeSurfaceImpl#isSimple()
         */
        public boolean isSimple() {
                // Implementation ok
                // Shells are always simple
                return true;
        }
        
        /* (non-Javadoc)
         * @see org.opengis.geometry.coordinate.#getRepresentativePoint()
         */
        public DirectPosition getRepresentativePoint() {
                return null;
        }
        
        public String toString() {
                return GeometryToString.getString(this);
        }
        
        @Override
        public int hashCode() {
                final int PRIME = 31;
                int result = 1;
                result = PRIME * result + ((solidBoundary == null) ? 0 : solidBoundary.hashCode());
                return result;
        }
        
        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                final ShellImpl other = (ShellImpl) obj;
                if (solidBoundary == null) {
                        if (other.solidBoundary != null)
                                return false;
                } else if (!solidBoundary.equals(other.solidBoundary))
                        return false;
                return true;
        }
}
