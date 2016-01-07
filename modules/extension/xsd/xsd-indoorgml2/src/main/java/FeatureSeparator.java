import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

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
public class FeatureSeparator {
	private Map<QName, List> featureTable;
	
	public FeatureSeparator() {
		
	}
	
	public void separate(Property root) {
		
		Name name = root.getName();
		
		
		
	}
	
	public void parseComplex(ComplexAttribute complex) {
		Name name = complex.getName();
		ComplexType complexType = complex.getType();
	}
	
	public void parseNextAttribute(Feature feature) {
		
			Name name = feature.getName();
			FeatureType fType = feature.getType();
			FeatureId id = feature.getIdentifier();
			
			Collection<? extends Property> values = feature.getValue();
			for(Iterator<? extends Property> it = values.iterator(); it.hasNext(); ) {
				
				Property p = it.next();
				PropertyType type = p.getType();
				
				if(type instanceof FeatureType) {
					parseNextAttribute((Feature) p);
				} else if(type instanceof ComplexType) {
					ComplexType cType = (ComplexType) type;
				} else if(type instanceof AttributeType) {
					AttributeType aType = (AttributeType) type;
				}
			}
	}
}
