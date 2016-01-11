import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.geotools.data.complex.NewFeatureTypeRegistry;
import org.geotools.data.complex.config.EmfComplexFeatureReader;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.xml.resolver.SchemaResolver;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.Name;

/**
 * 
 */

/**
 * @author HyungGyu Ryoo (Pusan National University)
 *
 */
public class ComplexFeatureServer {

    Map<Name, AttributeType> types;

    NewFeatureTypeRegistry typeRegistry;

    public ComplexFeatureServer() {
        
    }

    public void registerSchema(URL url) throws IOException {
        SchemaResolver appSchemaResolver = new SchemaResolver();
        EmfComplexFeatureReader reader = EmfComplexFeatureReader.newInstance();
        reader.setResolver(appSchemaResolver);
        NewFeatureTypeRegistry typeRegistry = new NewFeatureTypeRegistry(new ComplexFeatureTypeFactoryImpl(),
                new GmlFeatureTypeRegistryConfiguration(null));
        typeRegistry.addSchemas(reader.parse(url));
        this.types = typeRegistry.getTypeMap();
    }
    
    public void printRegisteredSchmea() {
        
    }
    
    public void destorySchmea() {
        if(typeRegistry != null) {
            typeRegistry.disposeSchemaIndexes();
        }
    }
    
    



}
