/**
 * 
 */
package org.geotools.feature.complex;

import java.util.Collection;
import java.util.Iterator;

import org.geotools.graph.build.GraphBuilder;
import org.geotools.graph.build.GraphGenerator;
import org.geotools.graph.build.basic.BasicGraphGenerator;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Graphable;
import org.opengis.feature.Attribute;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

/**
 * @author hgryoo
 *
 */
public class ComplexFeatureGraphGenerator extends BasicGraphGenerator {

    /**
     * The underling "geometry" building graph generator
     */
    GraphGenerator decorated;
    
    public ComplexFeatureGraphGenerator( GraphGenerator decorated ) {
            this.decorated = decorated;
    }
    
    public Graph getGraph() {
            return decorated.getGraph();
    }
    
    public GraphBuilder getGraphBuilder() {
            return decorated.getGraphBuilder();
    }
    
    public GraphGenerator getDecorated() {
        return decorated;
    }
    
    public Graphable add( Object obj ) {
            Feature feature = (Feature) obj;
            
            Graphable g = null;
            GeometryAttribute ga = getGeometry(feature);
            g = decorated.add(ga.getValue());
            
            //Geometry geom = (Geometry) g.getObject();
            //Preserve geometry from Graphable, as it may be changed.
            feature.setDefaultGeometryProperty(ga);
            g.setObject( feature );
            return g;
    }
    public Graphable remove( Object obj ) {
            Feature feature = (Feature) obj;
            return decorated.remove( getGeometry(feature) );
    }
    
    public Graphable get(Object obj) {
            Feature feature = (Feature) obj;
            return decorated.get( getGeometry(feature) );
    }
    
    private GeometryAttribute getGeometry(Feature feature) {
    	GeometryAttribute ga = null;
        if(feature.getDefaultGeometryProperty() != null) {
            ga = feature.getDefaultGeometryProperty();
        }
        else {
            Iterator<? extends Property> itr = feature.getValue().iterator(); 
            while (itr.hasNext()) {
                Property p = itr.next();
                
                if(GeometryAttribute.class.isAssignableFrom(p.getClass())) {
                    ga = (GeometryAttribute) p;
                    break;
                }
                else if(p.getValue() != null){
                	Iterator<? extends Attribute> ia = (Iterator<? extends Attribute>) ((Collection<? extends Property>) p.getValue()).iterator();
                	while(ia.hasNext()){
                		Attribute a = ia.next();
                		
                		if(GeometryAttribute.class.isAssignableFrom(a.getClass())) {
                            ga = (GeometryAttribute) a;
                            ((Feature) p).setDefaultGeometryProperty(ga);
                            break;
                        }
                	}
                }
            }
        }
        return ga;
    }
}
