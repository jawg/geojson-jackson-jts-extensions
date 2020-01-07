package io.jawg.geojson.jts.extension

import io.jawg.geojson.Feature
import io.jawg.geojson.Geometry
import io.jawg.geojson.GeometryCollection
import io.jawg.geojson.LineString
import io.jawg.geojson.MultiLineString
import io.jawg.geojson.MultiPoint
import io.jawg.geojson.MultiPolygon
import io.jawg.geojson.Point
import io.jawg.geojson.Polygon
import io.jawg.geojson.dsl.lineString
import io.jawg.geojson.dsl.multiLineString
import io.jawg.geojson.dsl.multiPoint
import io.jawg.geojson.dsl.multiPolygon
import io.jawg.geojson.dsl.point
import io.jawg.geojson.dsl.polygon
import io.jawg.geojson.Geometry as GeoJsonGeometry
import io.jawg.geojson.GeometryCollection as GeoJsonGeometryCollection
import org.locationtech.jts.geom.Geometry as JtsGeometry
import org.locationtech.jts.geom.GeometryCollection as JtsGeometryCollection
import org.locationtech.jts.geom.LineString as JtsLineString
import org.locationtech.jts.geom.MultiLineString as JtsMultiLineString
import org.locationtech.jts.geom.MultiPoint as JtsMultiPoint
import org.locationtech.jts.geom.MultiPolygon as JtsMultiPolygon
import org.locationtech.jts.geom.Point as JtsPoint
import org.locationtech.jts.geom.Polygon as JtsPolygon


/**
 * Transform a JTS [JtsPoint] to a GeoJSON [Point].
 */
fun JtsPoint.toGeoJson(): Point {
  val coordinate = requireNotNull(coordinate) { "Point coordinate must not be null" }
  return point {
    lng = coordinate.getX()
    lat = coordinate.getY()
    alt = coordinate.getZ().takeIf { !it.isNaN() }
  }
}

/**
 * Transform a JTS [JtsMultiPoint] to a GeoJSON [MultiPoint].
 */
fun JtsMultiPoint.toGeoJson(): MultiPoint {
  return multiPoint {
    for (i in 0 until numGeometries) {
      val point = getGeometryN(i) as JtsPoint
      coordinates += point.toGeoJson().coordinates
    }
  }
}

/**
 * Transform a JTS [JtsLineString] to a GeoJSON [LineString].
 */
fun JtsLineString.toGeoJson(): LineString {
  return lineString {
    getCoordinates().forEach { coordinate ->
      position {
        lng = coordinate.getX()
        lat = coordinate.getY()
        alt = coordinate.getZ().takeIf { !it.isNaN() }
      }
    }
  }
}

/**
 * Transform a JTS [JtsMultiLineString] to a GeoJSON [MultiLineString].
 */
fun JtsMultiLineString.toGeoJson(): MultiLineString {
  return multiLineString {
    for (i in 0 until numGeometries) {
      val lineString = getGeometryN(i) as JtsLineString
      this.lineStrings += lineString.toGeoJson()
    }
  }
}

/**
 * Retrieve rings of a [JtsPolygon].
 * @return an [Array] containing the polygon rings as [JtsLineString]
 */
private fun JtsPolygon.rings(): Array<JtsLineString> {
  val rings = arrayOfNulls<JtsLineString>(1 + numInteriorRing)
  rings[0] = exteriorRing
  for (i in 0 until numInteriorRing) {
    rings[i + 1] = getInteriorRingN(i)
  }
  @Suppress("UNCHECKED_CAST")
  return rings as Array<JtsLineString>
}

/**
 * Transform a JTS [JtsPolygon] to a GeoJSON [Polygon].
 */
fun JtsPolygon.toGeoJson(): Polygon {
  return polygon {
    rings().forEach { ring ->
      ring {
        for (i in 0 until ring.numPoints) {
          position {
            lng = ring.coordinateSequence.getX(i)
            lat = ring.coordinateSequence.getY(i)
            alt = ring.coordinateSequence.getZ(i).takeIf { !it.isNaN() }
          }
        }
      }
    }
  }
}

/**
 * Transform a JTS [JtsMultiPolygon] to a GeoJSON [MultiPolygon].
 */
fun JtsMultiPolygon.toGeoJson(): MultiPolygon {
  return multiPolygon {
    for (i in 0 until numGeometries) {
      val polygon = getGeometryN(i) as JtsPolygon
      polygons += polygon.toGeoJson()
    }
  }
}

/**
 * Transform a JTS [JtsGeometry] to a GeoJSON [Geometry].
 */
fun JtsGeometry.toGeoJson(): Geometry<*> {
  return when (this) {
    is JtsPoint -> toGeoJson()
    is JtsMultiPoint -> toGeoJson()
    is JtsLineString -> toGeoJson()
    is JtsMultiLineString -> toGeoJson()
    is JtsPolygon -> toGeoJson()
    is JtsMultiPolygon -> toGeoJson()
    is JtsGeometryCollection -> toGeoJson()
    else -> throw IllegalArgumentException("Unsupported geometry type $geometryType")
  }
}

/**
 * Transform a JTS [JtsGeometry] to a GeoJSON [Feature]
 * by retrieving the feature id and properties from [JtsGeometry.userData] as [FeatureData].
 */
fun JtsGeometry.toFeature(): Feature {
  val userData = userData
  val (id, properties) = if (userData is FeatureData) userData else FeatureData(null, null)
  return Feature(this.toGeoJson(), properties, id)
}

/**
 * Transform a JTS [JtsGeometryCollection] to a GeoJSON [GeometryCollection].
 */
fun JtsGeometryCollection.toGeoJson(): GeometryCollection {
  val geometries = ArrayList<GeoJsonGeometry<*>>(numGeometries)
  for (i in 0 until numGeometries) {
    val geometry = getGeometryN(i)
    geometries += geometry.toGeoJson()
  }
  return GeoJsonGeometryCollection(geometries)
}

