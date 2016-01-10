import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.complex.NewComplexFeatureSource;
import org.geotools.feature.complex.NewFeatureCollection;
import org.opengis.feature.ComplexAttribute;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyType;
import org.opengis.filter.identity.FeatureId;

/**
 * 
 */

/**
 * @author hgryoo
 *
 */
public class FeatureTableGenerator {
    
        private Map<Name, NewComplexFeatureSource> featureSourceTable = new HashMap<Name, NewComplexFeatureSource>();

	public FeatureTableGenerator(Feature feature) {
	    addFeature(feature);
	    parseNextFeature(feature);
        }
	
	public FeatureSource getFeatureSource(Name name) {
	    if(featureSourceTable.containsKey(name)) {
	        return featureSourceTable.get(name);
	    }
	    return null;
	}
	
	public Map getFeatureSources() {
	    return featureSourceTable;
	}
	
	public void parseNextFeature(ComplexAttribute attr) {

	    Collection<? extends Property> values = attr.getValue();
	    for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {

	        Property p = it.next();
	        PropertyType type = p.getType();

	        if(type instanceof ComplexType) {
	            boolean isSuccess = true;
	            if(type instanceof FeatureType) {
	                isSuccess = addFeature((Feature) p);
	            }
	            if(isSuccess) parseNextFeature((ComplexAttribute) p);
	        }
	    }
	}
	
	public boolean addFeature(Feature f) {
	    Name name = f.getName();
	    if(!featureSourceTable.containsKey(name)) {
	        featureSourceTable.put(name, new NewComplexFeatureSource(f.getType()));
	    }
	    return featureSourceTable.get(name).addFeature(f);
	}
}
