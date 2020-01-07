package io.jawg.geojson.jts.extension

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryCollection
import org.locationtech.jts.geom.LineString
import org.locationtech.jts.geom.MultiLineString
import org.locationtech.jts.geom.MultiPoint
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Polygon

object Jts {

  private val factory = GeometryFactories.Default

  object Points {
    val PARIS: Point = factory.createPoint(Coordinate(2.3522, 48.8566))
    val MONT_BLANC: Point = factory.createPoint(Coordinate(6.8652, 45.8326,4810.0))
  }

  object MultiPoints {
    val STATIONS: MultiPoint = factory.createMultiPoint(
      arrayOf(
        factory.createPoint(Coordinate(2.3393, 48.8522,3.5)),
        factory.createPoint(Coordinate(2.3442, 48.8511)),
        factory.createPoint(Coordinate(2.3487, 48.8500))
      )
    )
  }

  object LineStrings {
    val RAIL_WAY: LineString = factory.createLineString(
      arrayOf(
        Coordinate(2.3474, 48.8587),
        Coordinate(2.3410, 48.8608),
        Coordinate(2.3363, 48.8623)
      )
    )
  }

  object MultiLineStrings {
    val RAIL_WAYS: MultiLineString = factory.createMultiLineString(
      arrayOf(
        LineStrings.RAIL_WAY,
        factory.createLineString(
          arrayOf(
            Coordinate(2.3498, 48.8640),
            Coordinate(2.3457, 48.8625)
          )
        )
      )
    )
  }

  object Polygons {
    val BUILDING: Polygon = factory.createPolygon(
      factory.createLinearRing(
        arrayOf(
          Coordinate(2.362688,48.831176),
          Coordinate(2.362851,48.830980),
          Coordinate(2.363168,48.831088),
          Coordinate(2.362996,48.831291),
          Coordinate(2.362688,48.831176)
        )
      ),
      arrayOf(
        factory.createLinearRing(
          arrayOf(
            Coordinate(2.362945,48.831191),
            Coordinate(2.362859,48.831136),
            Coordinate(2.362897,48.831079),
            Coordinate(2.363018,48.831106),
            Coordinate(2.362945,48.831191)
          )
        )
      )
    )
  }

  object MultiPolygons {
    val BUILDINGS: MultiPolygon = factory.createMultiPolygon(
      arrayOf(
        Polygons.BUILDING,
        factory.createPolygon(
          factory.createLinearRing(
            arrayOf(
              Coordinate(
                2.361937,
                48.831166
              ),
              Coordinate(
                2.361910,
                48.831076
              ),
              Coordinate(
                2.362146,
                48.831106
              ),
              Coordinate(
                2.361937,
                48.831166
              )
            )
          )
        )
      )
    )
  }

  object GeometryCollections {
    val TUILERIES_GARDEN: GeometryCollection = factory.createGeometryCollection(
      arrayOf(
        factory.createPolygon(
          factory.createLinearRing(
            arrayOf(
              Coordinate(2.323511,48.866225),
              Coordinate(2.321569,48.863874),
              Coordinate(2.329949,48.860995),
              Coordinate(2.331794,48.863740),
              Coordinate(2.323511,48.866225)
            )
          )
        ),
        factory.createLineString(
          arrayOf(
            Coordinate(2.330839,48.862364),
            Coordinate(2.322567, 48.865046)
          )
        ),
        factory.createPoint(Coordinate(2.324155, 48.864531))
      )
    )
  }

  object Features {
    val PARIS: Point = factory.createPoint(Coordinate(2.3522, 48.8566))
      .also { it.userData = FeatureData("paris75000", mapOf("population" to 2_141_000)) }
  }

}