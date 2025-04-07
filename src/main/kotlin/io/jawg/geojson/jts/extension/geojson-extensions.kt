package io.jawg.geojson.jts.extension

import io.jawg.geojson.Feature
import io.jawg.geojson.FeatureCollection
import io.jawg.geojson.GeoJsonObject
import io.jawg.geojson.Geometry
import io.jawg.geojson.GeometryCollection
import io.jawg.geojson.LineString
import io.jawg.geojson.LineStringCoordinates
import io.jawg.geojson.LinearRing
import io.jawg.geojson.MultiLineString
import io.jawg.geojson.MultiPoint
import io.jawg.geojson.MultiPolygon
import io.jawg.geojson.Point
import io.jawg.geojson.Polygon
import io.jawg.geojson.PolygonCoordinates
import io.jawg.geojson.Position
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.CoordinateSequence
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.impl.CoordinateArraySequence
import org.locationtech.jts.geom.Geometry as JtsGeometry
import org.locationtech.jts.geom.GeometryCollection as JtsGeometryCollection
import org.locationtech.jts.geom.LineString as JtsLineString
import org.locationtech.jts.geom.LinearRing as JtsLinearRing
import org.locationtech.jts.geom.MultiLineString as JtsMultiLineString
import org.locationtech.jts.geom.MultiPoint as JtsMultiPoint
import org.locationtech.jts.geom.MultiPolygon as JtsMultiPolygon
import org.locationtech.jts.geom.Point as JtsPoint
import org.locationtech.jts.geom.Polygon as JtsPolygon


object GeometryFactories {
  val Default: GeometryFactory = GeometryFactory()
}

/**
 * Transform a GeoJSON [Position] to a JTS [Coordinate].
 */
private fun Position.toCoordinate(): Coordinate {
  return if (alt != null) Coordinate(lng, lat, alt!!) else Coordinate(lng, lat)
}

/**
 * Transform a [List] of GeoJSON [Position] to a JTS [CoordinateSequence].
 */
private fun List<Position>.toCoordinateSequence(): CoordinateSequence {
  return CoordinateArraySequence(this.map { it.toCoordinate() }.toTypedArray())
}

/**
 * Transform a GeoJSON [LineStringCoordinates] to a JTS [JtsLineString].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
private fun LineStringCoordinates.toLineString(factory: GeometryFactory = GeometryFactories.Default): JtsLineString {
  return factory.createLineString(this.toCoordinateSequence())
}

/**
 * Transform a GeoJSON [LinearRing] to a JTS [JtsLinearRing].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
private fun LinearRing.toLinearRing(factory: GeometryFactory = GeometryFactories.Default): JtsLinearRing {
  return factory.createLinearRing(this.toCoordinateSequence())
}

/**
 * Transform a GeoJSON [PolygonCoordinates] to a JTS [JtsPolygon].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
private fun PolygonCoordinates.toPolygon(factory: GeometryFactory = GeometryFactories.Default): JtsPolygon {
  val rings = this
  require(rings.isNotEmpty()) { "Polygon must have at least one linear ring" }

  val shell = rings.first().toLinearRing(factory)
  val holes = rings.drop(1).map { it.toLinearRing(factory) }.toTypedArray()

  return factory.createPolygon(shell, holes)
}

/**
 * Transform a GeoJSON [Point] to a JTS [JtsPoint].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun Point.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsPoint {
  return factory.createPoint(coordinates.toCoordinate())
}

/**
 * Transform a GeoJSON [MultiPoint] to a JTS [JtsMultiPoint].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun MultiPoint.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsMultiPoint {
  return factory.createMultiPoint(coordinates.toCoordinateSequence())
}

/**
 * Transform a GeoJSON [LineString] to a JTS [JtsLineString].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun LineString.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsLineString {
  return coordinates.toLineString(factory)
}

/**
 * Transform a GeoJSON [MultiLineString] to a JTS [JtsMultiLineString].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun MultiLineString.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsMultiLineString {
  val lineStrings = coordinates.map { it.toLineString(factory) }.toTypedArray()
  return factory.createMultiLineString(lineStrings)
}

/**
 * Transform a GeoJSON [Polygon] to a JTS [JtsPolygon].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun Polygon.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsPolygon {
  return coordinates.toPolygon(factory)
}

/**
 * Transform a GeoJSON [MultiPolygon] to a JTS [JtsMultiPolygon].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun MultiPolygon.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsMultiPolygon {
  val polygons = coordinates.map { it.toPolygon(factory) }.toTypedArray()
  return factory.createMultiPolygon(polygons)
}

/**
 * Transform a GeoJSON [Geometry] to a JTS [JtsGeometry].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun GeoJsonObject.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsGeometry {
  return when (this) {
    is Point -> toJts(factory)
    is MultiPoint -> toJts(factory)
    is LineString -> toJts(factory)
    is MultiLineString -> toJts(factory)
    is Polygon -> toJts(factory)
    is MultiPolygon -> toJts(factory)
    is GeometryCollection -> toJts(factory)
    is Feature -> toJts(factory)
    is FeatureCollection -> toJts(factory)
    else -> throw IllegalArgumentException("Unsupported geometry type $type")
  }
}

/**
 * Transform a GeoJSON [GeometryCollection] to a JTS [JtsGeometryCollection].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun GeometryCollection.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsGeometryCollection {
  val geometries = geometries.map { it.toJts(factory) }.toTypedArray()
  return factory.createGeometryCollection(geometries)
}

/**
 * Transform a GeoJSON [Feature] to a JTS [JtsGeometry]
 * by putting the feature id and properties in [JtsGeometry.userData] as [FeatureData].
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun Feature.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsGeometry {
  val geometry = requireNotNull(geometry) { "Feature geometry must not be null when converting to JTS Geometry" }
  return geometry.toJts(factory).also { it.userData = FeatureData(id, properties) }
}

/**
 * Transform a GeoJSON [FeatureCollection] to a JTS [JtsGeometryCollection]
 * @param factory geometry factory used to create the geometry. Default to [GeometryFactories.Default]
 */
fun FeatureCollection.toJts(factory: GeometryFactory = GeometryFactories.Default): JtsGeometryCollection {
  val geometries = features.map { it.toJts(factory) }.toTypedArray()
  return factory.createGeometryCollection(geometries)
}
