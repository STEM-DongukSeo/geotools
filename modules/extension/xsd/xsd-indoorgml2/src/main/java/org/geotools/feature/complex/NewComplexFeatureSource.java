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

    Map<String, Feature> features = new HashMap<String, Feature>();

    @Override
    public Name getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResourceInfo getInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataAccess<T, F> getDataStore() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public QueryCapabilities getQueryCapabilities() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addFeatureListener(FeatureListener listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeFeatureListener(FeatureListener listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FeatureCollection<T, F> getFeatures(Filter filter)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FeatureCollection<T, F> getFeatures(Query query) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    public boolean addFeatures(Feature feature) {
        String id = feature.getIdentifier().getID();
        if(features.containsKey(id)) {
            return false;
        }
        features.put(id, feature);
        return true;
    }

    @Override
    public FeatureCollection<T, F> getFeatures() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getSchema() {
        // TODO Auto-generated method stub
        return null;
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
