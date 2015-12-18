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

import org.geotools.geometry.iso.topograph2D.Dimension;
import org.geotools.geometry.iso.topograph2D.Location;

/**
 * @author Donguk Seo
 *
 */
public class IntersectionMatrix3D {
    private int[][] matrix;

    /**
         * 
         */
    public IntersectionMatrix3D() {
        matrix = new int[2][2];
        setAll(Dimension.FALSE);
    }

    /**
     * @param elements
     */
    public IntersectionMatrix3D(String elements) {
        this();
        set(elements);
    }

    /**
     * @param other
     */
    public IntersectionMatrix3D(IntersectionMatrix3D other) {
        this();
        matrix[Location.INTERIOR][Location.INTERIOR] = other.matrix[Location.INTERIOR][Location.INTERIOR];
        matrix[Location.INTERIOR][Location.BOUNDARY] = other.matrix[Location.INTERIOR][Location.BOUNDARY];
        matrix[Location.BOUNDARY][Location.INTERIOR] = other.matrix[Location.BOUNDARY][Location.INTERIOR];
        matrix[Location.BOUNDARY][Location.BOUNDARY] = other.matrix[Location.BOUNDARY][Location.BOUNDARY];
    }

    /**
     * @param row
     * @param col
     * @param dimensionValue
     */
    public void set(int row, int col, boolean dimensionValue) {
        if (dimensionValue) {
            set(row, col, Dimension.TRUE);
        } else {
            set(row, col, Dimension.FALSE);
        }
    }

    public void set(int row, int col, int dimensionValue) {
        matrix[row][col] = dimensionValue;
    }

    public void set(String dimensionSymbols) {
        for (int i = 0; i < dimensionSymbols.length(); i++) {
            int row = i / 2;
            int col = i % 2;
            matrix[row][col] = Dimension.toDimensionValue(dimensionSymbols.charAt(i));
        }
    }

    public void setAll(int dimensionValue) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = dimensionValue;
            }
        }
    }

    public static boolean matches(int actualDimensionValue, char requiredDimensionSymbol) {
        if (requiredDimensionSymbol == '*') {
            return true;
        }
        if (requiredDimensionSymbol == 'T'
                && (actualDimensionValue >= 0 || actualDimensionValue == Dimension.TRUE)) {
            return true;
        }
        if (requiredDimensionSymbol == 'F' && actualDimensionValue == Dimension.FALSE) {
            return true;
        }
        if (requiredDimensionSymbol == '0' && actualDimensionValue == Dimension.P) {
            return true;
        }
        if (requiredDimensionSymbol == '1' && actualDimensionValue == Dimension.L) {
            return true;
        }
        if (requiredDimensionSymbol == '2' && actualDimensionValue == Dimension.A) {
            return true;
        }
        if (requiredDimensionSymbol == '3' && actualDimensionValue == 3) {
            return true;
        }
        return false;
    }

    public boolean matches(String requiredDimensionSymbols) {
        if (requiredDimensionSymbols.length() != 4) {
            throw new IllegalArgumentException("Should be length 4: " + requiredDimensionSymbols);
        }
        for (int ai = 0; ai < 2; ai++) {
            for (int bi = 0; bi < 2; bi++) {
                if (!matches(matrix[ai][bi], requiredDimensionSymbols.charAt(2 * ai + bi))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int get(int row, int col) {
        return matrix[row][col];
    }
}
