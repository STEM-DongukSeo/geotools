import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    
        private Map<Name, List> featureTable = new HashMap<Name, List>();

	public FeatureTableGenerator(Feature feature) {
	    addFeature(feature);
	    parseNextFeature(feature);
	    
	    for(Name n : featureTable.keySet()) {
	        System.out.println(n);
	    }
	    
        }
	
	public List getFeatureCollection(Name name) {
	    if(featureTable.containsKey(name)) {
	        return featureTable.get(name);
	    }
	    else {
	        return Collections.emptyList();
	    }
	}
	
	public Map getFeatureTable() {
	    return featureTable;
	}
	
	public void parseNextFeature(ComplexAttribute attr) {

	    Collection<? extends Property> values = attr.getValue();
	    for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {

	        Property p = it.next();
	        PropertyType type = p.getType();

	        if(type instanceof ComplexType) {
	            parseNextFeature((ComplexAttribute) p);
	            if(type instanceof FeatureType) {
	                addFeature((Feature) p);
	            }
	        }
	    }
	}
	
	public void addFeature(Feature f) {
	    Name name = f.getName();
	    if(!featureTable.containsKey(name)) {
	        featureTable.put(name, new ArrayList<Property>());
	    }
	    featureTable.get(name).add(f);
	}
}
