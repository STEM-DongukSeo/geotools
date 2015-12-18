#include <SFCGAL/algorithm/area.h>
#include <SFCGAL/algorithm/convexHull.h>
#include <SFCGAL/algorithm/covers.h>
#include <SFCGAL/algorithm/difference.h>
#include <SFCGAL/algorithm/distance.h>
#include <SFCGAL/algorithm/distance3d.h>
#include <SFCGAL/algorithm/extrude.h>
#include <SFCGAL/algorithm/intersection.h>
#include <SFCGAL/algorithm/intersects.h>
#include <SFCGAL/algorithm/isValid.h>
#include <SFCGAL/algorithm/minkowskiSum.h>
#include <SFCGAL/algorithm/offset.h>
#include <SFCGAL/algorithm/plane.h>
#include <SFCGAL/algorithm/straightSkeleton.h>
#include <SFCGAL/algorithm/tesselate.h>
#include <SFCGAL/algorithm/union.h>
#include <SFCGAL/algorithm/volume.h>

#include <SFCGAL/Kernel.h>

#include "SFCoordinate.h"
#include "SFGeometry.h"
#include "SFPoint.h"
#include "SFLineString.h"
#include "SFPolygon.h"
#include "SFMultiLineString.h"
#include "SFMultiPolygon.h"


double area( const SFGeometry& g ) {
	return SFCGAL::algorithm::area(*(g.get_data()));
}

double area3D( const SFGeometry& g) {
	return SFCGAL::algorithm::area3D(*(g.get_data()));
}

SFGeometry& convexHull( const SFGeometry& g ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::convexHull(*(g.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& convexHull3D( const SFGeometry& g) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::convexHull3D(*(g.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

bool covers(const SFGeometry& gA, const SFGeometry& gB ) {
	return SFCGAL::algorithm::covers(*(gA.get_data()), *(gB.get_data()));
}

bool covers3D(const SFGeometry& gA, const SFGeometry& gB ) {
	return SFCGAL::algorithm::covers3D(*(gA.get_data()), *(gB.get_data()));
}

SFGeometry& difference( const SFGeometry& gA, const SFGeometry& gB ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::difference(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& difference3D( const SFGeometry& gA, const SFGeometry& gB ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::difference3D(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

double distance( const SFGeometry& gA, const SFGeometry& gB ) {
	return SFCGAL::algorithm::distance(*(gA.get_data()), *(gB.get_data()));
}

double distance3D( const SFGeometry& gA, const SFGeometry& gB) {
	return SFCGAL::algorithm::distance3D(*(gA.get_data()), *(gB.get_data()));
}

SFGeometry& extrude( const SFGeometry& g, double dx, double dy, double dz) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::extrude(*(g.get_data()), dx, dy, dz);
	
	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& intersection( const SFGeometry& gA, const SFGeometry& gB) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::intersection(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& intersection3D( const SFGeometry& gA, const SFGeometry& gB) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::intersection3D(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

bool intersects( const SFGeometry& gA, const SFGeometry& gB ){
	return SFCGAL::algorithm::intersects(*(gA.get_data()), *(gB.get_data()));
}

bool intersects3D( const SFGeometry& gA, const SFGeometry& gB ){
	return SFCGAL::algorithm::intersects3D(*(gA.get_data()), *(gB.get_data()));
}
/*
Validity isValid( const Geometry& ga, const double toleranceAbs) {
	return SFCGAL::algorithm::isValid(*(gA.get_data()), toleranceAbs);
}
*/

SFGeometry& minkowskiSum( const SFGeometry& g, const SFPolygon& polygon ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::minkowskiSum(*(g.get_data()), *(SFCGAL::Polygon *)(polygon.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFMultiPolygon& offset( const SFGeometry& g, const double r) {
	std::auto_ptr<SFCGAL::MultiPolygon> p = SFCGAL::algorithm::offset(*(g.get_data()), r);

	SFMultiPolygon *multiPolygon = new SFMultiPolygon(p.release());

	return *multiPolygon;	
}

bool hasPlane3D( const SFPolygon& polygon ) {
	return SFCGAL::algorithm::hasPlane3D<SFCGAL::Kernel>(*(SFCGAL::Polygon *)(polygon.get_data()));
}

SFMultiLineString& straightSkeleton( const SFGeometry& g, bool autoOrientation ) {
	std::auto_ptr<SFCGAL::MultiLineString> p = SFCGAL::algorithm::straightSkeleton(*(g.get_data()), autoOrientation);

	SFMultiLineString *multiLineString = new SFMultiLineString(p.release());

	return *multiLineString;
}

SFGeometry& tesselate( const SFGeometry& g ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::tesselate(*(g.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& union_( const SFGeometry& gA, const SFGeometry& gB ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::union_(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

SFGeometry& union3D( const SFGeometry& gA, const SFGeometry& gB ) {
	std::auto_ptr<SFCGAL::Geometry> p = SFCGAL::algorithm::union3D(*(gA.get_data()), *(gB.get_data()));

	SFGeometry *geometry = new SFGeometry(p.release());

	return *geometry;
}

double volume( const SFGeometry& g ){
	return CGAL::to_double(SFCGAL::algorithm::volume(*(g.get_data())));
}
