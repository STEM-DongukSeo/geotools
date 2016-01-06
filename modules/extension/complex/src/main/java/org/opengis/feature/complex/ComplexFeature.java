/**
 * 
 */
package org.opengis.feature.complex;

import org.geotools.feature.type.ComplexFeatureType;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;

/**
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public interface ComplexFeature extends Feature {

    String getID();
    
    /**
     * Override and type narrow to FeatureType.
     * @return The feature type
     */
    FeatureType getType();
    

}
