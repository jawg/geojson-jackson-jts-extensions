package io.jawg.geojson.jts.extension

import kotlin.test.assertTrue

internal fun assertEqualsExact(expected: org.locationtech.jts.geom.Geometry, actual: org.locationtech.jts.geom.Geometry) {
  assertTrue(expected.equalsExact(actual))
}