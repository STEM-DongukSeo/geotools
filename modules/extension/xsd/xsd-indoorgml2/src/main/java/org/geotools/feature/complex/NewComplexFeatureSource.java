/**
 * 
 */
package org.geotools.feature.complex;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.geotools.data.DataAccess;
import org.geotools.data.FeatureListener;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.data.QueryCapabilities;
import org.geotools.data.ResourceInfo;
import org.geotools.feature.FeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;

/**
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public class NewComplexFeatureSource<T extends FeatureType, F extends Feature> implements FeatureSource<T, F> {

    FeatureType featureType = null;

    Map<String, Feature> features = new HashMap<String, Feature>();

    public NewComplexFeatureSource() {
    }
    
    public NewComplexFeatureSource(FeatureType fType) {
        this.featureType = fType;
    }
    
    @Override
    public Name getName() {
        if(featureType != null) {
            return featureType.getName();
        } else {
            return null;
        }
    }

    @Override
    public ResourceInfo getInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataAccess<T, F> getDataStore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public QueryCapabilities getQueryCapabilities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addFeatureListener(FeatureListener listener) {
        throw new UnsupportedOperationException();
        
    }

    @Override
    public void removeFeatureListener(FeatureListener listener) {
        throw new UnsupportedOperationException();
        
    }

    @Override
    public FeatureCollection<T, F> getFeatures(Filter filter)
            throws IOException {
        NewFeatureCollection fc = new NewFeatureCollection();
        for(Feature f : features.values()) {
            if(filter.evaluate(f)) {
                fc.add(f);
            }
        }
        return fc;
    }

    @Override
    public FeatureCollection<T, F> getFeatures(Query query) throws IOException {
        throw new UnsupportedOperationException();
    }
    
    public boolean addFeature(Feature feature) throws IllegalArgumentException {
        if( featureType == null) {
            featureType = feature.getType();
        }
        
        if( featureType != feature.getType()) {
            throw new IllegalArgumentException("illegal featureType");
        }
        
        String id = feature.getIdentifier().getID();
        if(features.containsKey(id)) {
            return false;
        }
        features.put(id, feature);
        return true;
    }

    @Override
    public FeatureCollection<T, F> getFeatures() throws IOException {
        NewFeatureCollection fc = new NewFeatureCollection();
        for(Feature f : features.values()) {
                fc.add(f);
        }
        return fc;
    }

    @Override
    public T getSchema() {
        if(featureType != null) {
            return (T) featureType;
        } else {
            return null;
        }
    }

    @Override
    public ReferencedEnvelope getBounds() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public ReferencedEnvelope getBounds(Query query) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount(Query query) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Key> getSupportedHints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
