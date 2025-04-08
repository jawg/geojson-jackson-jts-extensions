package io.jawg.geojson.jts.extension

import io.jawg.geojson.Geometry
import org.junit.jupiter.api.Test
import org.locationtech.jts.geom.GeometryCollection
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class GeoJsonExtensionsTest {

  @Test
  internal fun point() {
    val geojson = GeoJson.Points.PARIS
    val jts = geojson.toJts()
    assertEqualsExact(Jts.Points.PARIS, jts)
  }

  @Test
  internal fun `point with alt`() {
    val geojson = GeoJson.Points.MONT_BLANC
    val jts = geojson.toJts()
    assertEqualsExact(Jts.Points.MONT_BLANC, jts)
  }

  @Test
  internal fun multiPoint() {
    val geojson = GeoJson.MultiPoints.STATIONS
    val jts = geojson.toJts()
    assertEqualsExact(Jts.MultiPoints.STATIONS, jts)
  }

  @Test
  internal fun lineString() {
    val geojson = GeoJson.LineStrings.RAIL_WAY
    val jts = geojson.toJts()
    assertEqualsExact(Jts.LineStrings.RAIL_WAY, jts)
  }

  @Test
  internal fun multiLineString() {
    val geojson = GeoJson.MultiLineStrings.RAIL_WAYS
    val jts = geojson.toJts()
    assertEqualsExact(Jts.MultiLineStrings.RAIL_WAYS, jts)
  }

  @Test
  internal fun polygon() {
    val geojson = GeoJson.Polygons.BUILDING
    val jts = geojson.toJts()
    assertEqualsExact(Jts.Polygons.BUILDING, jts)
  }

  @Test
  internal fun multiPolygon() {
    val geojson = GeoJson.MultiPolygons.BUILDINGS
    val jts = geojson.toJts()
    assertEqualsExact(Jts.MultiPolygons.BUILDINGS, jts)
  }

  @Test
  internal fun geometryCollection() {
    val geojson = GeoJson.GeometryCollections.TUILERIES_GARDEN
    val jts = geojson.toJts()
    assertEqualsExact(Jts.GeometryCollections.TUILERIES_GARDEN, jts)
  }

  @Test
  internal fun `geometry as Point`() {
    val geometry: Geometry<*> = GeoJson.Points.MONT_BLANC
    val jts = geometry.toJts()
    assertEqualsExact(Jts.Points.MONT_BLANC, jts)
  }

  @Test
  internal fun `geometry as MultiPoint`() {
    val geometry: Geometry<*> = GeoJson.MultiPoints.STATIONS
    val jts = geometry.toJts()
    assertEqualsExact(Jts.MultiPoints.STATIONS, jts)
  }

  @Test
  internal fun `geometry as LineString`() {
    val geometry: Geometry<*> = GeoJson.LineStrings.RAIL_WAY
    val jts = geometry.toJts()
    assertEqualsExact(Jts.LineStrings.RAIL_WAY, jts)
  }

  @Test
  internal fun `geometry as MultiLineString`() {
    val geometry: Geometry<*> = GeoJson.MultiLineStrings.RAIL_WAYS
    val jts = geometry.toJts()
    assertEqualsExact(Jts.MultiLineStrings.RAIL_WAYS, jts)
  }

  @Test
  internal fun `geometry as Polygon`() {
    val geometry: Geometry<*> = GeoJson.Polygons.BUILDING
    val jts = geometry.toJts()
    assertEqualsExact(Jts.Polygons.BUILDING, jts)
  }

  @Test
  internal fun `geometry as MultiPolygon`() {
    val geometry: Geometry<*> = GeoJson.MultiPolygons.BUILDINGS
    val jts = geometry.toJts()
    assertEqualsExact(Jts.MultiPolygons.BUILDINGS, jts)
  }

  @Test
  internal fun feature() {
    val geojson = GeoJson.Features.PARIS
    val jts = geojson.toJts()

    assertNotNull(jts.userData)
    assertEquals(Jts.Features.PARIS.userData, jts.userData)
    assertEqualsExact(Jts.Features.PARIS, jts)
  }

  @Test
  internal fun `feature collection`() {
    val geojson = GeoJson.FeatureCollections.CITIES
    val jts = geojson.toJts()

    assertNull(jts.userData)
    assertEquals(2, jts.numGeometries)
    assertEquals(Jts.Features.PARIS.userData, jts.getGeometryN(0).userData)
    assertEqualsExact(Jts.Features.PARIS, jts.getGeometryN(0))
  }

}
