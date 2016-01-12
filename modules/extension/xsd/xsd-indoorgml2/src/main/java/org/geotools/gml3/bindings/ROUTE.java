package org.geotools.gml3.bindings;

import java.util.Set;

import javax.xml.namespace.QName;

import org.geotools.gml3.GML;
import org.geotools.xml.XSD;


/**
 * 
 *
 * @source $URL$
 */
public final class ROUTE extends XSD {
    private static ROUTE instance = new ROUTE();
    public static String NAMESPACE = "http://www.geotools.org/route";

    //types
    public static QName RouteFeatureType = new QName(NAMESPACE, "RouteFeatureType");
    public static QName RouteFeatureCollectionType = new QName(NAMESPACE, "RouteRouteCollectionType");

    //elements
    public static QName RouteFeature = new QName(NAMESPACE, "RouteFeature");
    public static QName RouteFeatureCollection = new QName(NAMESPACE, "RouteFeatureCollection");

    private ROUTE() {
    }

    public static ROUTE getInstance() {
        return instance;
    }

    protected void addDependencies(Set dependencies) {
        dependencies.add(GML.getInstance());
    }

    public String getNamespaceURI() {
        return NAMESPACE;
    }

    public String getSchemaLocation() {
        return getClass().getResource("route.xsd").toString();
    }
}