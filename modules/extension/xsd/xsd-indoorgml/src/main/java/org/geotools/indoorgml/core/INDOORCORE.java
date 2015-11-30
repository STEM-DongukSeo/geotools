package org.geotools.indoorgml.core;


import java.util.Set;
import javax.xml.namespace.QName;

import org.geotools.gml3.v3_2.GML;
import org.geotools.xlink.XLINK;
import org.geotools.xml.XSD;
import org.opengis.feature.type.Schema;

/**
 * This interface contains the qualified names of all the types,elements, and 
 * attributes in the http://www.opengis.net/indoorgml/1.0/core schema.
 *
 * @generated
 */
public final class INDOORCORE extends XSD {

    /** singleton instance */
    private static final INDOORCORE instance = new INDOORCORE();
    
    /**
     * Returns the singleton instance.
     */
    public static final INDOORCORE getInstance() {
       return instance;
    }
    
    /**
     * private constructor
     */
    private INDOORCORE() {
    }
    
    protected void addDependencies(Set dependencies) {
        dependencies.add(GML.getInstance());
    }
    
    @Override
    protected Schema buildTypeSchema() {
        // TODO Auto-generated method stub
        return new INDOORCORESchema();
    }

    /**
     * Returns 'http://www.opengis.net/indoorgml/1.0/core'.
     */
    public String getNamespaceURI() {
       return NAMESPACE;
    }
    
    /**
     * Returns the location of 'indoorgmlcore.xsd.'.
     */
    public String getSchemaLocation() {
       return getClass().getResource("indoorgmlcore.xsd").toString();
    }
    
    /** @generated */
    public static final String NAMESPACE = "http://www.opengis.net/indoorgml/1.0/core";
    
    /* Type Definitions */
    /** @generated */
    public static final QName CellSpaceBoundaryMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryMemberType");
    /** @generated */
    public static final QName CellSpaceBoundaryPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryPropertyType");
    /** @generated */
    public static final QName CellSpaceBoundaryType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundaryType");
    /** @generated */
    public static final QName CellSpaceMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceMemberType");
    /** @generated */
    public static final QName CellSpacePropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpacePropertyType");
    /** @generated */
    public static final QName CellSpaceType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceType");
    /** @generated */
    public static final QName EdgesType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","EdgesType");
    /** @generated */
    public static final QName externalObjectReferenceType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","externalObjectReferenceType");
    /** @generated */
    public static final QName ExternalReferenceType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","ExternalReferenceType");
    /** @generated */
    public static final QName IndoorFeaturesType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","IndoorFeaturesType");
    /** @generated */
    public static final QName InterEdgesType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","InterEdgesType");
    /** @generated */
    public static final QName InterLayerConnectionMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionMemberType");
    /** @generated */
    public static final QName InterLayerConnectionPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionPropertyType");
    /** @generated */
    public static final QName InterLayerConnectionType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnectionType");
    /** @generated */
    public static final QName MultiLayeredGraphPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraphPropertyType");
    /** @generated */
    public static final QName MultiLayeredGraphType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraphType");
    /** @generated */
    public static final QName NodesType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","NodesType");
    /** @generated */
    public static final QName PrimalSpaceFeaturesPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeaturesPropertyType");
    /** @generated */
    public static final QName PrimalSpaceFeaturesType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeaturesType");
    /** @generated */
    public static final QName SpaceLayerClassTypeType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerClassTypeType");
    /** @generated */
    public static final QName SpaceLayerMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerMemberType");
    /** @generated */
    public static final QName SpaceLayerPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerPropertyType");
    /** @generated */
    public static final QName SpaceLayersType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayersType");
    /** @generated */
    public static final QName SpaceLayerType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayerType");
    /** @generated */
    public static final QName StateMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","StateMemberType");
    /** @generated */
    public static final QName StatePropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","StatePropertyType");
    /** @generated */
    public static final QName StateType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","StateType");
    /** @generated */
    public static final QName TransitionMemberType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","TransitionMemberType");
    /** @generated */
    public static final QName TransitionPropertyType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","TransitionPropertyType");
    /** @generated */
    public static final QName TransitionType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","TransitionType");
    /** @generated */
    public static final QName typeOfTopoExpressionCodeEnumerationType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeEnumerationType");
    /** @generated */
    public static final QName typeOfTopoExpressionCodeOtherType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeOtherType");
    /** @generated */
    public static final QName typeOfTopoExpressionCodeType = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","typeOfTopoExpressionCodeType");

    /* Elements */
    /** @generated */
    public static final QName CellSpace = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpace");
    /** @generated */
    public static final QName CellSpaceBoundary = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","CellSpaceBoundary");
    /** @generated */
    public static final QName IndoorFeatures = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","IndoorFeatures");
    /** @generated */
    public static final QName InterLayerConnection = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","InterLayerConnection");
    /** @generated */
    public static final QName MultiLayeredGraph = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","MultiLayeredGraph");
    /** @generated */
    public static final QName PrimalSpaceFeatures = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","PrimalSpaceFeatures");
    /** @generated */
    public static final QName SpaceLayer = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","SpaceLayer");
    /** @generated */
    public static final QName State = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","State");
    /** @generated */
    public static final QName Transition = 
        new QName("http://www.opengis.net/indoorgml/1.0/core","Transition");

    /* Attributes */

}
    