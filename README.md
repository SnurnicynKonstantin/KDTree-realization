# Realization of searching of points in area using KDTree

## Task

Implement the geo-index based on K-D-tree and the function of finding the nearest objects on the basis of this index.

## Details

Geodata - a pair of values with a floating point of the form Point (latitude, longitude),
describing the location of the object on the earth's surface in the polar coordinate system. The main store of the list of objects of type Point is the hashtablitz.
Geo-index is an auxiliary data structure (see for example a binary tree
search) accelerates the search by location. One of the main tasks is to search for all points within a specified radius from the specified point.

## Implement

● data structure for storing geoobject data with Geo fields (double
latitude, double longitude, String title) - latitude, longitude and name
Geographical object (string);
● test data generator that populates the object table with random data
(in mind);
● the data search structure using the K-D-tree algorithm, the index is updated to
each addition / deletion / modification of table entries;
● List <Geo> near (Point point, double radius) function based on this
geo-index, which return all objects from the specified point to
the distance is no more than radius (the number with a floating point).
● a console interface that allows you to enter the coordinates of the point, radius and
outputting the results of the near function with these parameters to the console.

## Instruments

● automatic assembly project - Maven
● You can use third-party libraries if this does not directly apply to the index / search algorithm itself, which must be implemented manually
