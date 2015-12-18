#ifndef JAVACPP_SFCGAL_Coordinate_H
#define JAVACPP_SFCGAL_Coordinate_H

#include <SFCGAL/Coordinate.h>

class SFCoordinate{
	SFCGAL::Coordinate data;
public:
	typedef SFCGAL::Coordinate cpp_base;
	const cpp_base& get_data() const { return data; }
	cpp_base& get_data() { return data; }
	
	SFCoordinate() : data() { }
	SFCoordinate(double x, double y) : data(x, y) { }
	SFCoordinate(double x, double y, double z) : data(x, y, z) { }
	//SFCoordinate(const Kernel::Point2& other) : data(other) { }
	//SFCoordinate(const Kernel::Point3& other) : data(other) { }
	SFCoordinate(const SFCoordinate& other) : data(other.data) { }
	SFCoordinate(const SFCGAL::Coordinate& other) : data(other) { }
	
	SFCoordinate& operator=(const SFCoordinate& other) {
		data = other.data;
		
		return *this;
	}
	
	~SFCoordinate() { }
	
	int coordinateDimension() const {
		return data.coordinateDimension();
	}

	bool isEmpty() const {
		return data.isEmpty();
	}

	bool is3D() const {
		return data.is3D();
	}

	double x() const {
		return CGAL::to_double(data.x());
	}

	double y() const {
		return CGAL::to_double(data.y());
	}

	double z() const {
		return CGAL::to_double(data.z());
	}

	SFCoordinate& round(const long& scaleFactor = 1) {
		data.round(scaleFactor);

		return *this;
	}

	bool operator<(const SFCoordinate& other) const {
		return ( data < other.data ) ;
	}

	bool operator==(const SFCoordinate& other) const {
		return ( data == other.data ) ;
	}

	bool operator!=(const SFCoordinate& other) const {
		return ( data != other.data ) ;
	}
	
	//Kernel::Vector_2 toVector_2() const;
	//Kernel::Vector_3 toVector_3() const;

	//Kernel::Point_2 toPoint_2() const;
	//Kernel::Point_3 toPoint_3() const;
};

#endif
