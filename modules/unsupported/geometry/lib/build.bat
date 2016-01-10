set CGAL_INCLUDEDIR=%CGAL_DIR%/include
set CGAL_LIBRARYDIR=%CGAL_DIR%/bin
set SFCGAL_INCLUDEDIR=%SFCGAL_DIR%/include
set SFCGAL_LIBRARYDIR=%SFCGAL_DIR%/src

javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/PointerVector.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFCoordinate.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFGeometry.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFPoint.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFLineString.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFSurface.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFPolygon.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFTriangle.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFPolyhedralSurface.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulatedSurface.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFSolid.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFGeometryCollection.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPoint.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFMultiLineString.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPolygon.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFMultiSolid.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFEnvelope.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulate.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFPreparedGeometry.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFCAPI.java
javac -cp javacpp.jar;../src/main/java ../src/main/java/org/geotools/geometry/iso/sfcgal/wrapper/SFAlgorithm.java

java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/PointerVector -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFCoordinate -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFGeometry -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPoint -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFLineString -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFSurface -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPolygon -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangle -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPolyhedralSurface -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulatedSurface -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFSolid -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFGeometryCollection -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPoint -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiLineString -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPolygon -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiSolid -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFEnvelope -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulate -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPreparedGeometry -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFCAPI -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw
java -jar javacpp.jar -cp ../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFAlgorithm -Xcompiler -I%BOOST_INCLUDEDIR% -Xcompiler -I%CGAL_INCLUDEDIR% -Xcompiler -I%SFCGAL_INCLUDEDIR% -Xcompiler -I%GMP_INC_DIR% -Xcompiler -I%MPFR_INC_DIR% -Xcompiler -L%BOOST_LIBRARYDIR% -Xcompiler -L%CGAL_LIBRARYDIR% -Xcompiler -L%SFCGAL_LIBRARYDIR% -Xcompiler -L%GMP_LIB_DIR% -Xcompiler -L%MPFR_LIB_DIR% -Xcompiler -lboost_system-mgw52-mt-1_55 -Xcompiler -lboost_thread-mgw52-mt-1_55 -Xcompiler -lCGAL -Xcompiler -lCGAL_Core -Xcompiler -lSFCGAL -Xcompiler -lgmp-10 -Xcompiler -lmpfr-4 -properties windows-x86_64-mingw

@echo off
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/PointerVector
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFGeometry
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFCoordinate
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFGeometry
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPoint
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFLineString
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFSurface
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPolygon
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangle
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPolyhedralSurface
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulatedSurface
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFSolid
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFGeometryCollection
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPoint
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiLineString
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiPolygon
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFMultiSolid
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFEnvelope
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFTriangulate
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFPreparedGeometry
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFCAPI
rem java -cp javacpp.jar;../src/main/java org/geotools/geometry/iso/sfcgal/wrapper/SFAlgorithm

pause