/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2012, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.feature.complex;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.geotools.data.DataSourceException;
import org.geotools.data.wfs.internal.Loggers;
import org.geotools.data.wfs.internal.parsers.XmlSimpleFeatureParser;
import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.gml3.v3_2.GML;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.Converters;
import org.geotools.xml.XSD;
import org.opengis.feature.Feature;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.PositionFactory;
import org.opengis.geometry.aggregate.AggregateFactory;
import org.opengis.geometry.aggregate.MultiPoint;
import org.opengis.geometry.aggregate.MultiSurface;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.PrimitiveFactory;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.Shell;
import org.opengis.geometry.primitive.Solid;
import org.opengis.geometry.primitive.SolidBoundary;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.xmlpull.mxp1.MXParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.vividsolutions.jts.geom.Polygon;

/**
 * Abstract form of XmlFeatureParser. Mostly taken out from @{@link XmlSimpleFeatureParser}.
 * @author Adam Brown (Curtin University of Technology)
 */
public abstract class NewXmlFeatureParser<FT extends FeatureType, F extends Feature>
		implements NewGetParser<F> {

        private static final String POS = "pos";
        private static final String POSLIST = "posList";
        private static final String COORDINATES = "coordinates";
        
        private static final String POINT = "Point";
        private static final String LINESTRING = "LineString";
        private static final String POLYGON = "Polygon";
        private static final String SOLID = "Solid";
        
        private static final String EXTERIOR = "exterior";
        private static final String INTERIOR = "interior";
        
        private static final String LINEARRING = "LinearRing";
        private static final String SHELL = "Shell";
        
        private static final String MULTIPOINT = "MultiPoint";
        private static final String POINTMEMBERS = "pointMembers";
        private static final String POINTMEMBER = "pointMember";
        
        private static final String MULTISURFACE = "MultiSurface";
        private static final String SURFACEMEMBERS = "surfaceMembers";
        private static final String SURFACEMEMBER = "surfaceMember";
        
        private static final String MULTIPOLYGON = "MultiPolygon";
        private static final String POLYGONMEMBERS = "polygonMembers";
        private static final String POLYGONMEMBER = "polygonMember";

	protected FT targetType;

	private XSD processingXSD = null;
	
	/*private GeometryFactory geomFac = new GeometryFactory();*/
	private GeometryBuilder builder;
	
	private GeometryFactory geomFac;
	private PrimitiveFactory primitiveFac;
	private PositionFactory posFac;
	private AggregateFactory aggFac;
	
	private InputStream inputStream;

	protected XmlPullParser parser;

	final String featureNamespace;

	final String featureName;

	private static final Logger LOGGER = Loggers.RESPONSES;

	public NewXmlFeatureParser(final InputStream getFeatureResponseStream,
			final FT targetType, QName featureDescriptorName)
			throws IOException {
		this.inputStream = getFeatureResponseStream;
		this.featureNamespace = featureDescriptorName.getNamespaceURI();
		this.featureName = featureDescriptorName.getLocalPart();
		this.targetType = targetType;

		// Create New Geometry Factory
		Hints hints = GeoTools.getDefaultHints();
                hints.put(Hints.CRS, DefaultGeographicCRS.WGS84_3D);
                hints.put(Hints.GEOMETRY_VALIDATE, false);
                setGeometryBuilder( new GeometryBuilder(hints) );
                
		try {
                        parser = new MXParser();
                        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                     // parse root element
                        parser.setInput(inputStream, "UTF-8");
		} catch (XmlPullParserException e) {
			throw new DataSourceException(e);
		}
	}

	@Override
	public FT getFeatureType() {
		return targetType;
	}

	@Override
	public void close() throws IOException {
		if (this.inputStream != null) {
			try {
				this.parser.setInput(null);
				this.parser = null;
				this.inputStream.close();
				this.inputStream = null;
			} catch (XmlPullParserException e) {
				throw new DataSourceException(e);
			}
		}
	}
	
	@Override
	public void setGeometryBuilder(GeometryBuilder geometryBuilder) {
	    if (null != geometryBuilder) {
                this.builder = geometryBuilder;
                geomFac = geometryBuilder.getGeometryFactory();
                primitiveFac = geometryBuilder.getPrimitiveFactory();
                posFac = geometryBuilder.getPositionFactory(); 
                aggFac = geometryBuilder.getAggregateFactory();
            }
	}
	
	/**
	 * Parses the value of the current attribute, parser cursor shall be on a
	 * feature attribute START_TAG event.
	 * 
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws FactoryException
	 * @throws NoSuchAuthorityCodeException
	 */
	@SuppressWarnings("unchecked")
	protected Object parseAttributeValue(AttributeDescriptor attribute)
			throws XmlPullParserException, IOException {
		final AttributeType type = attribute.getType();
		Object parsedValue;
		if (type instanceof GeometryType) {			
		        parser.nextTag();			
			try {
				parsedValue = parseGeom();
			} catch (NoSuchAuthorityCodeException e) {
				throw new DataSourceException(e);
			} catch (FactoryException e) {
				throw new DataSourceException(e);
			}
		} else {
			String rawTextValue = parser.nextText();
			Class binding = type.getBinding();
			parsedValue = Converters.convert(rawTextValue, binding);
		}

		return parsedValue;
	}
	/**
	 * <p>
	 * Precondition: parser cursor positioned on a geometry property (eg,
	 * {@code gml:Point}, etc)
	 * </p>
	 * <p>
	 * Postcondition: parser gets positioned at the end tag of the element it
	 * started parsing the geometry at
	 * </p>
	 * 
	 * @return
	 * @throws FactoryException
	 * @throws NoSuchAuthorityCodeException
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	protected Geometry parseGeom() throws NoSuchAuthorityCodeException,
			FactoryException, XmlPullParserException, IOException {
		final QName startingGeometryTagName = new QName(parser.getNamespace(),
				parser.getName());
		
		int dimension = crsDimension(3);
		CoordinateReferenceSystem crs = crs(DefaultGeographicCRS.WGS84_3D);

		Geometry geom;
		
		if (POINT.equalsIgnoreCase(startingGeometryTagName.getLocalPart())) {
                    geom = parsePoint(dimension, crs);
                } else if(LINESTRING.equals(startingGeometryTagName.getLocalPart())) {
                    geom = parseLineString(dimension, crs);
                } else if(POLYGON.equals(startingGeometryTagName.getLocalPart())) {
                    geom = parsePolygon(dimension, crs);
                } else if(SOLID.equals(startingGeometryTagName.getLocalPart())) {
		    geom = parseSolid(dimension, crs);
                } else if (MULTIPOINT.equals(startingGeometryTagName.getLocalPart())) {
                    geom = parseMultiPoint(dimension, crs);
                } else if (MULTISURFACE.equals(startingGeometryTagName.getLocalPart())) {
                    geom = parseMultiSurface(dimension, crs);
                } else if (MULTIPOLYGON.equals(startingGeometryTagName.getLocalPart())) {
                    geom = parseMultiPolygon(dimension, crs);
                } else {
                        throw new IllegalStateException("Unrecognized geometry element "
                                        + startingGeometryTagName);
                }

		parser.require(END_TAG, startingGeometryTagName.getNamespaceURI(),
				startingGeometryTagName.getLocalPart());
		
		return geom;
	}

	/**
         * Parses a MultiPolygon out of a MultiSurface element (because our geometry
         * model only supports MultiPolygon).
         * <p>
         * Precondition: parser positioned at a {@link GML#MultiSurface
         * MultiSurface} start tag
         * </p>
         * <p>
         * Postcondition: parser positioned at the {@link GML#MultiSurface
         * MultiSurface} end tag of the starting tag
         * </p>
         */
        private MultiSurface parseMultiSurface(int dimension,
                        CoordinateReferenceSystem crs) throws XmlPullParserException,
                        IOException, NoSuchAuthorityCodeException, FactoryException {

                parser.require(START_TAG, null,
                                MULTISURFACE);

                MultiSurface geom;
                parser.nextTag();
                final QName memberTag = new QName(parser.getNamespace(),
                                parser.getName());
                Set<OrientableSurface> polygons = new HashSet<OrientableSurface>(2);
                if (SURFACEMEMBERS.equals(memberTag.getLocalPart())) {
                        while (true) {
                                parser.nextTag();
                                if (END_TAG == parser.getEventType()
                                                && SURFACEMEMBERS.equals(
                                                                parser.getName())) {
                                        // we're done
                                        break;
                                }
                                Surface p = parsePolygon(dimension, crs);
                                polygons.add(p);
                        }
                        parser.nextTag();
                } else if (SURFACEMEMBER.equals(memberTag.getLocalPart())) {
                        while (true) {
                                parser.nextTag();
                                Surface p = parsePolygon(dimension, crs);
                                polygons.add(p);
                                parser.nextTag();
                                parser.require(END_TAG, null,
                                                SURFACEMEMBER);
                                parser.nextTag();
                                if (END_TAG == parser.getEventType()
                                                && MULTISURFACE.equals(
                                                                parser.getName())) {
                                        // we're done
                                        break;
                                }
                        }
                }
                parser.require(END_TAG, null,
                        MULTISURFACE);

                geom = aggFac.createMultiSurface(polygons);
                return geom;
        }
        
        private MultiSurface parseMultiPolygon(int dimension,
                CoordinateReferenceSystem crs) throws XmlPullParserException,
                IOException, NoSuchAuthorityCodeException, FactoryException {

                parser.require(START_TAG, null,
                                   MULTIPOLYGON);
        
                MultiSurface geom;
                Set<OrientableSurface> polygons = new HashSet<OrientableSurface>(2);
                parser.nextTag();
                while (true) {
                        parser.require(START_TAG, null,
                                        POLYGONMEMBER);
                        parser.nextTag();
                        parser.require(START_TAG, null, POLYGON);
                        Surface p = parsePolygon(dimension, crs);
                        polygons.add(p);
                        parser.nextTag();
                        parser.require(END_TAG, null,
                                    POLYGONMEMBER);
                        parser.nextTag();
                        if (END_TAG == parser.getEventType()
                                        && MULTIPOLYGON.equals(parser.getName())) {
                                // we're done
                                break;
                        }
                }
                
                parser.require(END_TAG, null, MULTIPOLYGON);
        
                geom = aggFac.createMultiSurface(polygons);
                return geom;
        }
        
	
	private Solid parseSolid(int dimension, 
	                    CoordinateReferenceSystem crs) throws XmlPullParserException,
	                    IOException, NoSuchAuthorityCodeException, FactoryException {
	    
	    Solid geom = null;
	    Shell shell = null;
	    List<Shell> holes = null;
	    
            parser.require(START_TAG, null, SOLID);

            parser.nextTag();

            QName name = new QName(parser.getNamespace(), parser.getName());

            if (EXTERIOR.equals(name.getLocalPart())) {
                    
                    //TODO add 3.1.1 version parsing routine
                
                    parser.nextTag();
                    shell = parseShell(dimension, crs);
                    parser.nextTag();
                    parser.require(END_TAG, null, EXTERIOR);
            } else {
                    throw new IllegalStateException(
                                    "Unknown polygon boundary element: " + name);
            }

            parser.nextTag();

            name = new QName(parser.getNamespace(), parser.getName());
            if (START_TAG == parser.getEventType()) {
                    if (INTERIOR.equals(name.getLocalPart())) {
                            // parse interior rings
                            holes = new ArrayList<Shell>(2);
                            while (true) {
                                    parser.require(START_TAG, null,
                                                    name.getLocalPart());
                                    parser.nextTag();
                                    parser.require(START_TAG, null,
                                                    SHELL);

                                    Shell hole = parseShell(dimension, crs);

                                    parser.require(END_TAG, null,
                                                    SHELL);

                                    holes.add(hole);

                                    parser.nextTag();
                                    parser.require(END_TAG, null, 
                                                    name.getLocalPart());
                                    parser.nextTag();
                                    if (END_TAG == parser.getEventType()) {
                                            // we're done
                                            parser.require(END_TAG, null,
                                                            SOLID);
                                            break;
                                    }
                            }
                    }
            }
            
            
            parser.require(END_TAG, null, SOLID);
            
            SolidBoundary solidBoundary = primitiveFac.createSolidBoundary(shell, holes);
            geom = primitiveFac.createSolid(solidBoundary);
            return geom;
	}
	
	private Shell parseShell(int dimension, 
                CoordinateReferenceSystem crs) throws XmlPullParserException,
                IOException, NoSuchAuthorityCodeException, FactoryException {
	    
	    Shell geom = null;
	    
	    parser.require(START_TAG, null, SHELL);
	    
	    parser.nextTag();
	    
            final QName memberTag = new QName(parser.getNamespace(), parser.getName());
            List<OrientableSurface> surfaces = new ArrayList<OrientableSurface>(2);
            if (SURFACEMEMBER.equals(memberTag.getLocalPart())) {
                while (true) {
                        parser.nextTag();
                        Surface p = parsePolygon(dimension, crs);
                        surfaces.add(p);
                        parser.nextTag();
                        parser.require(END_TAG, null,
                                SURFACEMEMBER);
                        parser.nextTag();
                        if (END_TAG == parser.getEventType()
                                        && SHELL.equals(
                                                        parser.getName())) {
                                // we're done
                                break;
                        }
                }
            }
            
            parser.require(END_TAG, null, SHELL);
            geom = primitiveFac.createShell(surfaces);
            return geom;
	}

    /**
	 * Parses a polygon.
	 * <p>
	 * Precondition: parser positioned at a {@link GML#Polygon Polygon} start
	 * tag
	 * </p>
	 * <p>
	 * Postcondition: parser positioned at the {@link GML#Polygon Polygon} end
	 * tag of the starting tag
	 * </p>
	 * 
	 * @param dimension
	 * @param crs
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @throws NoSuchAuthorityCodeException
	 * @throws FactoryException
	 */
	private Surface parsePolygon(int dimension, CoordinateReferenceSystem crs)
			throws XmlPullParserException, IOException,
			NoSuchAuthorityCodeException, FactoryException {
	        Surface geom = null;
		Ring shell;
		List<Ring> holes = null;

		parser.nextTag();

		QName name = new QName(parser.getNamespace(), parser.getName());

		if (EXTERIOR.equals(name.getLocalPart())) {
			parser.nextTag();
			shell = parseLinearRing(dimension, crs);
			parser.nextTag();
			parser.require(END_TAG, null, EXTERIOR);
		} else {
			throw new IllegalStateException(
					"Unknown polygon boundary element: " + name);
		}

		parser.nextTag();

		name = new QName(parser.getNamespace(), parser.getName());

		if (START_TAG == parser.getEventType()) {
			if (INTERIOR.equals(name.getLocalPart())) {
				// parse interior rings
				holes = new ArrayList<Ring>(2);
				while (true) {
					parser.require(START_TAG, null,
							name.getLocalPart());
					parser.nextTag();
					parser.require(START_TAG, null,
					            LINEARRING);

					Ring hole = parseLinearRing(dimension, crs);

					parser.require(END_TAG, null,
					            LINEARRING);

					holes.add(hole);

					parser.nextTag();
					parser.require(END_TAG, null, name.getLocalPart());
					parser.nextTag();
					if (END_TAG == parser.getEventType()) {
						// we're done
						parser.require(END_TAG, null,
								POLYGON);
						break;
					}
				}
			}
		}

		parser.require(END_TAG, null, POLYGON);
		
		SurfaceBoundary boundary = primitiveFac.createSurfaceBoundary(shell, holes);
		geom = primitiveFac.createSurface(boundary);
		//geom.setUserData(crs);
		return geom;
	}

	private Ring parseLinearRing(final int dimension,
			CoordinateReferenceSystem crs) throws XmlPullParserException,
			IOException, NoSuchAuthorityCodeException, FactoryException {
		parser.require(START_TAG, null, LINEARRING);

		crs = crs(crs);
		LineString lineString = parseLineStringInternal(dimension, crs);
		List<CurveSegment> curveSegments = new ArrayList<CurveSegment>();
                curveSegments.add(lineString);
                
                Curve curve = primitiveFac.createCurve(curveSegments);
                List<OrientableCurve> curves = new ArrayList<OrientableCurve>();
                curves.add(curve);
		
		parser.require(END_TAG, null, LINEARRING);
		
		Ring linearRing = primitiveFac.createRing(curves);
		//linearRing.setUserData(crs);
		return linearRing;
	}

	private Curve parseLineString(int dimension,
			CoordinateReferenceSystem crs) throws XmlPullParserException,
			IOException, NoSuchAuthorityCodeException, FactoryException {

		parser.require(START_TAG, null, LINESTRING);

		crs = crs(crs);
		LineString lineString = parseLineStringInternal(dimension, crs);
		List<CurveSegment> curveSegments = new ArrayList<CurveSegment>();
                curveSegments.add(lineString);
                
		parser.require(END_TAG, null, LINESTRING);

		Curve geom = primitiveFac.createCurve(curveSegments);
		//geom.setUserData(crs);
		return geom;
	}

	private LineString parseLineStringInternal(int dimension,
			CoordinateReferenceSystem crs) throws XmlPullParserException,
			IOException {

		final QName lineElementName = new QName(parser.getNamespace(),
				parser.getName());

		parser.nextTag();
		Position[] lineCoords;

		final QName coordsName = new QName(parser.getNamespace(),
				parser.getName());
		String tagName = parser.getName();
		if (POS.equals(coordsName.getLocalPart())) {
		        Position[] point;
			List<Position> coords = new ArrayList<Position>();
			int eventType;
			do {
				point = parseCoordList(dimension);
				coords.add(point[0]);
				parser.nextTag();
				tagName = parser.getName();
				eventType = parser.getEventType();
			} while (eventType == START_TAG
					&& tagName.equals(POS));

			lineCoords = coords.toArray(new Position[coords.size()]);
		} else if (POSLIST.equals(coordsName.getLocalPart())) {
			// parser.require(START_TAG, null,
			// posList.getLocalPart());
			lineCoords = parseCoordList(dimension);
			parser.nextTag();
		} else if (COORDINATES.equals(coordsName.getLocalPart())) {
			lineCoords = parseCoordinates(dimension);
			parser.nextTag();
		} else {
			throw new IllegalStateException(
					"Expected posList or pos inside LinearRing: " + tagName);
		}
		parser.require(END_TAG, lineElementName.getNamespaceURI(),
				lineElementName.getLocalPart());
		
		List<Position> coordList = Arrays.asList(lineCoords);
                LineString lineString = builder.createLineString(coordList);
		
		return lineString;
	}

	private Point parsePoint(int dimension, CoordinateReferenceSystem crs)
			throws XmlPullParserException, IOException,
			NoSuchAuthorityCodeException, FactoryException {

		parser.require(START_TAG, null, POINT);

		crs = crs(crs);

		Point geom;
		parser.nextTag();
		parser.require(START_TAG, null, null);
		DirectPosition point;
		if (POS.equals(parser.getName())) {
		    DirectPosition[] coords = parseCoordList(dimension);
			point = coords[0];
			parser.nextTag();
		} else if (COORDINATES.equals(parser.getName())) {
		    DirectPosition[] coords = parseCoordinates(dimension);
			point = coords[0];
			parser.nextTag();
		} else {
			throw new IllegalStateException(
					"Unknown coordinate element for Point: " + parser.getName());
		}

		parser.require(END_TAG, null, POINT);
		

		geom = primitiveFac.createPoint(point);
		return geom;
	}

	/**
     * Parses a MultiPoint.
     * <p>
     * Precondition: parser positioned at a {@link GML#MultiPoint MultiPoint}
     * start tag
     * </p>
     * <p>
     * Postcondition: parser positioned at the {@link GML#MultiPoint MultiPoint}
     * end tag of the starting tag
     * </p>
     * 
     * @throws IOException
     * @throws XmlPullParserException
     * @throws IOException
     * @throws XmlPullParserException
     * @throws FactoryException
     * @throws NoSuchAuthorityCodeException
     * @throws FactoryException
     * @throws NoSuchAuthorityCodeException
     */
    private MultiPoint parseMultiPoint(int dimension,
    		CoordinateReferenceSystem crs) throws XmlPullParserException,
    		IOException, NoSuchAuthorityCodeException, FactoryException {
            MultiPoint geom = null;
    	parser.nextTag();
    	final QName memberTag = new QName(parser.getNamespace(),
    			parser.getName());
    	Set<Point> points = new HashSet<Point>(4);
    	if (POINTMEMBERS.equals(memberTag.getLocalPart())) {
    		while (true) {
    			parser.nextTag();
    			if (END_TAG == parser.getEventType()
    					&& POINTMEMBERS.equals(
    							parser.getName())) {
    				// we're done
    				break;
    			}
    
    			Point p = parsePoint(dimension, crs);
    			points.add(p);
    		}
    		parser.nextTag();
    	} else if (POINTMEMBER.equals(memberTag.getLocalPart())) {
    		while (true) {
    			parser.nextTag();
    			parser.require(START_TAG, null,
    					POINT);
    
    			Point p = parsePoint(dimension, crs);
    			points.add(p);
    			parser.nextTag();
    			parser.require(END_TAG, null,
    					POINTMEMBER);
    			parser.nextTag();
    			if (END_TAG == parser.getEventType()
    					&& MULTIPOINT.equals(
    							parser.getName())) {
    				// we're done
    				break;
    			}
    		}
    	}
    	parser.require(END_TAG, null, MULTIPOINT);
    	geom = aggFac.createMultiPoint(points);
    	return geom;
    }

    private CoordinateReferenceSystem crs(CoordinateReferenceSystem defaultValue)
			throws NoSuchAuthorityCodeException, FactoryException {
		String srsName = parser.getAttributeValue(null, "srsName");
		if (srsName == null) {
			return defaultValue;
		}
		boolean forceXY = false;
		if (srsName.startsWith("http://")) {
			forceXY = true;
			srsName = "EPSG:" + srsName.substring(1 + srsName.lastIndexOf('#'));
		} else if (srsName.startsWith("EPSG:")) {
			forceXY = true;
		}
		CoordinateReferenceSystem crs = CRS.decode(srsName, forceXY);
		return crs;
	}

	private int crsDimension(final int defaultValue) {
		String srsDimension = parser.getAttributeValue(null, "srsDimension");
		if (srsDimension == null) {
			return defaultValue;
		}

		int dimension = Integer.valueOf(srsDimension);
		return dimension;
	}

	private DirectPosition[] parseCoordList(int dimension)
			throws XmlPullParserException, IOException {
		// we might be on a posList tag with srsDimension defined
		dimension = crsDimension(dimension);
		String rawTextValue = parser.nextText();
		DirectPosition[] coords = toCoordList(rawTextValue, dimension);
		return coords;
	}

	private DirectPosition[] parseCoordinates(int dimension)
			throws XmlPullParserException, IOException {
		parser.require(START_TAG, null, COORDINATES);
		// we might be on a posList tag with srsDimension defined
		dimension = crsDimension(dimension);

		String decimalSeparator = parser.getAttributeValue("", "decimal");
		String coordSeparator = parser.getAttributeValue("", "cs");
		String tupleSeparator = parser.getAttributeValue("", "ts");

		String rawTextValue = parser.nextText();
		DirectPosition[] coords = toCoordList(rawTextValue, decimalSeparator,
				coordSeparator, tupleSeparator, dimension);

		parser.require(END_TAG, null, COORDINATES);
		return coords;
	}

	private DirectPosition[] toCoordList(String rawTextValue, final int dimension) {
		rawTextValue = rawTextValue.trim();
		rawTextValue = rawTextValue.replaceAll("\n", " ");
		rawTextValue = rawTextValue.replaceAll("\r", " ");
		String[] split = rawTextValue.trim().split(" +");
		final int ordinatesLength = split.length;
		if (ordinatesLength % dimension != 0) {
			throw new IllegalArgumentException("Number of ordinates ("
					+ ordinatesLength + ") does not match crs dimension: "
					+ dimension);
		}
		final int nCoords = ordinatesLength / dimension;
		DirectPosition[] coords = new DirectPosition[nCoords];
		DirectPosition coord;
		int currCoordIdx = 0;
		for (int i = 0; i < ordinatesLength; i += dimension) {
		    
		        double[] ps = new double[dimension];
		    
		        ps[0] = Double.valueOf(split[i]);
		        ps[1] = Double.valueOf(split[i + 1]);
			
			if(dimension > 2) {
			    ps[2] = Double.valueOf(split[i + 2]);
			}
			
			coord = builder.createDirectPosition(ps);
			
			coords[currCoordIdx] = coord;
			currCoordIdx++;
		}
		return coords;
	}

	private DirectPosition[] toCoordList(String rawTextValue,
			final String decimalSeparator, final String coordSeparator,
			final String tupleSeparator, final int dimension) {

		rawTextValue = rawTextValue.trim();
		rawTextValue = rawTextValue.replaceAll("\n", " ");
		rawTextValue = rawTextValue.replaceAll("\r", " ");

		String[] tuples = rawTextValue.trim()
				.split("\\" + tupleSeparator + "+");

		final int nCoords = tuples.length;

		DirectPosition[] coords = new DirectPosition[nCoords];
		DirectPosition coord;

		for (int i = 0; i < nCoords; i++) {
			String tuple = tuples[i];
			String[] oridnates = tuple.split("\\" + coordSeparator + "+");
			double[] parsedOrdinates = new double[oridnates.length];
			for (int o = 0; o < oridnates.length; o++) {
				String ordinate = oridnates[o];
				if (!".".equals(decimalSeparator)) {
					String[] split = ordinate.split("\\" + decimalSeparator);
					ordinate = new StringBuilder(split[0]).append('.')
							.append(split[1]).toString();
				}
				parsedOrdinates[o] = Double.parseDouble(ordinate);
			}
			coord = builder.createDirectPosition(parsedOrdinates);
			coords[i] = coord;
		}
		return coords;
	}
	
	protected String seekFeature() throws IOException, XmlPullParserException {
		int tagType;
		
		while (true) {
			tagType = parser.next();
			if (tagType == XmlPullParser.END_DOCUMENT) {
				close();
				return null;
			}

			if (START_TAG == tagType) {
				String namespace = parser.getNamespace();
				String name = parser.getName();
				
				if (featureNamespace.equals(namespace)
						&& featureName.equals(name)) {
				    
				        String featureId = null;
				        
				        featureId = parser.getAttributeValue(
				                    org.geotools.gml3.GML.id.getNamespaceURI(), "id");
				        
				        if (featureId == null) {
				            featureId = parser.getAttributeValue(
				                    org.geotools.gml3.v3_2.GML.id.getNamespaceURI(), "id");
				        }
				        
                                        if (featureId == null) {
                                            featureId = parser.getAttributeValue(null, "fid");
                                        }
                                        // Mapserver hack
                                        if (featureId == null) {
                                            featureId = parser.getAttributeValue(null, "id");
                                        }
					
					return featureId;
				}
			}
		}
	}
}
