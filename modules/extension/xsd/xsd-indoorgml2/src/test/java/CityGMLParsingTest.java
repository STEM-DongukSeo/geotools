import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.geotools.data.complex.config.EmfComplexFeatureReader;
import org.geotools.data.complex.config.FeatureTypeRegistry;
import org.geotools.feature.NameImpl;
import org.geotools.feature.complex.NewXmlComplexFeatureParser;
import org.geotools.feature.type.ComplexFeatureTypeFactoryImpl;
import org.geotools.gml3.complex.GmlFeatureTypeRegistryConfiguration;
import org.geotools.xml.resolver.SchemaResolver;
import org.junit.Test;
import org.opengis.feature.Feature;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.FeatureType;

/**
 * 
 */

/**
 * @author ryoo
 *
 */
public class CityGMLParsingTest {

    @Test
    public void test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL baseSchema = classLoader.getResource("org/geotools/citygml/2.0/cityGMLBase.xsd");
        URL profileSchmea = classLoader.getResource("org/geotools/citygml/building/2.0/building.xsd");
        
        // Creating Feature Type
        SchemaResolver appSchemaResolver = new SchemaResolver();
        EmfComplexFeatureReader reader = EmfComplexFeatureReader.newInstance();
        reader.setResolver(appSchemaResolver);
        FeatureTypeRegistry typeRegistry = new FeatureTypeRegistry(new ComplexFeatureTypeFactoryImpl(),
                    new GmlFeatureTypeRegistryConfiguration(null));
        
        typeRegistry.addSchemas(reader.parse(baseSchema));
        typeRegistry.addSchemas(reader.parse(profileSchmea));
        
        AttributeDescriptor descriptor = typeRegistry
                        .getDescriptor(new NameImpl("http://www.opengis.net/citygml/2.0",
                                        ":", "CityModel"), null);
        FeatureType featureType = (FeatureType) descriptor.getType();

        // Creating Feature
        URL url = getClass().getResource("Lotte_17.gml");
        NewXmlComplexFeatureParser featureParser = new NewXmlComplexFeatureParser(
                        url.openStream(),
                        featureType, new QName("http://www.opengis.net/citygml/2.0",
                                "CityModel"));
        
        // Act
        Feature feature = featureParser.parse();
        
        
        feature.getClass();
    }

}
