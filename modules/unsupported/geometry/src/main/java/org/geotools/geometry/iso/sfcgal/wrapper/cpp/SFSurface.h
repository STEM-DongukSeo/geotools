#ifndef JAVACPP_SFCGAL_Surface_H
#define JAVACPP_SFCGAL_Surface_H

#include <SFCGAL/Surface.h>
#include "SFGeometry.h"

class SFSurface : public SFGeometry {
public:
	SFSurface() : SFGeometry() { }
	//SFSurface(const SFSurface& other) : SFGeometry(other.data) { }
	//SFSurface(SFSurface* other) : SFGeometry(other->data) { }
	//SFSurface(const SFSFCGAL::Surface& other) : SFGeometry(new SFCGAL::Surface(other)) { }
	SFSurface(SFCGAL::Surface* other) : SFGeometry(other) { }

	~SFSurface() { }

	//--SFCGAL::Geometry
	int dimension() const {
		return data->dimension();
	}
};

#endif
