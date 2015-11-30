package org.geotools.indoorgml.core;

import org.eclipse.xsd.util.XSDSchemaLocationResolver;
import org.geotools.gml3.v3_2.GMLConfiguration;
import org.geotools.xlink.XLINKConfiguration;
import org.geotools.xml.Configuration;
import org.picocontainer.MutablePicoContainer;

/**
 * Parser configuration for the http://www.opengis.net/indoorgml/1.0/core schema.
 *
 * @generated
 */
public class INDOORCOREConfiguration extends Configuration {

    /**
     * Creates a new configuration.
     * 
     * @generated
     */     
    public INDOORCOREConfiguration() {
       super(INDOORCORE.getInstance());
       
       //TODO: add dependencies here
       addDependency(new GMLConfiguration());
    }
    
    /**
     * Registers the bindings for the configuration.
     *
     * @generated
     */
    protected final void registerBindings( MutablePicoContainer container ) {
        //Types
        container.registerComponentImplementation(INDOORCORE.CellSpaceBoundaryMemberType,CellSpaceBoundaryMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpaceBoundaryPropertyType,CellSpaceBoundaryPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpaceBoundaryType,CellSpaceBoundaryTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpaceMemberType,CellSpaceMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpacePropertyType,CellSpacePropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpaceType,CellSpaceTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.EdgesType,EdgesTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.externalObjectReferenceType,ExternalObjectReferenceTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.ExternalReferenceType,ExternalReferenceTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.IndoorFeaturesType,IndoorFeaturesTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.InterEdgesType,InterEdgesTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.InterLayerConnectionMemberType,InterLayerConnectionMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.InterLayerConnectionPropertyType,InterLayerConnectionPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.InterLayerConnectionType,InterLayerConnectionTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.MultiLayeredGraphPropertyType,MultiLayeredGraphPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.MultiLayeredGraphType,MultiLayeredGraphTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.NodesType,NodesTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.PrimalSpaceFeaturesPropertyType,PrimalSpaceFeaturesPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.PrimalSpaceFeaturesType,PrimalSpaceFeaturesTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayerClassTypeType,SpaceLayerClassTypeTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayerMemberType,SpaceLayerMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayerPropertyType,SpaceLayerPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayersType,SpaceLayersTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayerType,SpaceLayerTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.StateMemberType,StateMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.StatePropertyType,StatePropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.StateType,StateTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.TransitionMemberType,TransitionMemberTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.TransitionPropertyType,TransitionPropertyTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.TransitionType,TransitionTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.typeOfTopoExpressionCodeEnumerationType,TypeOfTopoExpressionCodeEnumerationTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.typeOfTopoExpressionCodeOtherType,TypeOfTopoExpressionCodeOtherTypeBinding.class);
        container.registerComponentImplementation(INDOORCORE.typeOfTopoExpressionCodeType,TypeOfTopoExpressionCodeTypeBinding.class);

        //Elements
        container.registerComponentImplementation(INDOORCORE.CellSpace,CellSpaceBinding.class);
        container.registerComponentImplementation(INDOORCORE.CellSpaceBoundary,CellSpaceBoundaryBinding.class);
        container.registerComponentImplementation(INDOORCORE.IndoorFeatures,IndoorFeaturesBinding.class);
        container.registerComponentImplementation(INDOORCORE.InterLayerConnection,InterLayerConnectionBinding.class);
        container.registerComponentImplementation(INDOORCORE.MultiLayeredGraph,MultiLayeredGraphBinding.class);
        container.registerComponentImplementation(INDOORCORE.PrimalSpaceFeatures,PrimalSpaceFeaturesBinding.class);
        container.registerComponentImplementation(INDOORCORE.SpaceLayer,SpaceLayerBinding.class);
        container.registerComponentImplementation(INDOORCORE.State,StateBinding.class);
        container.registerComponentImplementation(INDOORCORE.Transition,TransitionBinding.class);

    
    }
} 