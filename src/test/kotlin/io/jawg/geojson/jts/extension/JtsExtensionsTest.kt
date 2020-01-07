package io.jawg.geojson.jts.extension

import org.junit.jupiter.api.Test
import org.locationtech.jts.geom.Geometry
import kotlin.test.assertEquals

class JtsExtensionsTest {

  @Test
  internal fun point() {
    val jts = Jts.Points.PARIS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.Points.PARIS, geojson)
  }

  @Test
  internal fun `point with alt`() {
    val jts = Jts.Points.MONT_BLANC
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.Points.MONT_BLANC, geojson)
  }

  @Test
  internal fun multiPoint() {
    val jts = Jts.MultiPoints.STATIONS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiPoints.STATIONS, geojson)
  }

  @Test
  internal fun lineString() {
    val jts = Jts.LineStrings.RAIL_WAY
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.LineStrings.RAIL_WAY, geojson)
  }

  @Test
  internal fun multiLineString() {
    val jts = Jts.MultiLineStrings.RAIL_WAYS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiLineStrings.RAIL_WAYS, geojson)
  }

  @Test
  internal fun polygon() {
    val jts = Jts.Polygons.BUILDING
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.Polygons.BUILDING, geojson)
  }

  @Test
  internal fun multiPolygon() {
    val jts = Jts.MultiPolygons.BUILDINGS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiPolygons.BUILDINGS, geojson)
  }

  @Test
  internal fun geometryCollection() {
    val jts = Jts.GeometryCollections.TUILERIES_GARDEN
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.GeometryCollections.TUILERIES_GARDEN, geojson)
  }

  @Test
  internal fun `geometry as Point`() {
    val jts: Geometry = Jts.Points.MONT_BLANC
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.Points.MONT_BLANC, geojson)
  }

  @Test
  internal fun `geometry as MultiPoint`() {
    val jts: Geometry = Jts.MultiPoints.STATIONS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiPoints.STATIONS, geojson)
  }

  @Test
  internal fun `geometry as LineString`() {
    val jts: Geometry = Jts.LineStrings.RAIL_WAY
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.LineStrings.RAIL_WAY, geojson)
  }

  @Test
  internal fun `geometry as MultiLineString`() {
    val jts: Geometry = Jts.MultiLineStrings.RAIL_WAYS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiLineStrings.RAIL_WAYS, geojson)
  }

  @Test
  internal fun `geometry as Polygon`() {
    val jts: Geometry = Jts.Polygons.BUILDING
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.Polygons.BUILDING, geojson)
  }

  @Test
  internal fun `geometry as MultiPolygon`() {
    val jts: Geometry = Jts.MultiPolygons.BUILDINGS
    val geojson = jts.toGeoJson()
    assertEquals(GeoJson.MultiPolygons.BUILDINGS, geojson)
  }

  @Test
  internal fun feature() {
    val jts = Jts.Features.PARIS
    val geojson = jts.toFeature()
    assertEquals(GeoJson.Features.PARIS, geojson)
  }

  @Test
  internal fun `feature no id no properties`() {
    val jts = Jts.Points.PARIS
    val geojson = jts.toFeature()
    assertEquals(GeoJson.Features.PARIS_GEOMETRY, geojson)
  }

}