/**
 * 
 */
package org.geotools.feature.complex;

import java.util.SortedMap;
import java.util.TreeMap;

import org.geotools.feature.FeatureIterator;
import org.geotools.feature.collection.BaseFeatureCollection;
import org.geotools.feature.collection.FeatureIteratorImpl;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.identity.FeatureId;

/**
 * @author hgryoo
 *
 */
public class NewFeatureCollection<T extends FeatureType, F extends Feature> extends BaseFeatureCollection<T, F> {

    private SortedMap<String,Feature> contents = new TreeMap<String,Feature>();

    @Override
    public FeatureIterator<F> features() {
        return new FeatureIteratorImpl(contents.values());
    }

    public boolean add(Feature feature) {
        // This cast is necessary to keep with the contract of Set!
        if( feature == null ) return false; // cannot add null!
        final FeatureId ID = feature.getIdentifier();
        if( ID == null ) return false; // ID is required!
        if( contents.containsKey( ID.getID() ) ) return false; // feature all ready present
        
        if( this.schema == null ) {
                this.schema = (T) feature.getType();
        }
        
        FeatureType childType = (FeatureType) getSchema();
        if( !feature.getType().equals(childType) ) {
                System.out.println("Feature Collection contains a heterogeneous" +
                        " mix of features");
                return false;
        }
        // Check inheritance with FeatureType here?
        contents.put( ID.getID(), feature );
        return true;        
    }
    
}
