package io.jawg.geojson.jts.extension

import io.jawg.geojson.dsl.feature
import io.jawg.geojson.dsl.geometryCollection
import io.jawg.geojson.dsl.lineString
import io.jawg.geojson.dsl.multiLineString
import io.jawg.geojson.dsl.multiPoint
import io.jawg.geojson.dsl.multiPolygon
import io.jawg.geojson.dsl.point
import io.jawg.geojson.dsl.polygon

object GeoJson {
  object Points {

    val PARIS = point {
      lat = 48.8566
      lng = 2.3522
    }

    val MONT_BLANC = point {
      lat = 45.8326
      lng = 6.8652
      alt = 4810.0
    }

  }

  object MultiPoints {

    val STATIONS = multiPoint {
      position {
        lat = 48.8522
        lng = 2.3393
        alt = 3.5
      }
      position {
        lat = 48.8511
        lng = 2.3442
      }
      position {
        lat = 48.8500
        lng = 2.3487
      }
    }

  }

  object LineStrings {

    val RAIL_WAY = lineString {
      position {
        lat = 48.8587
        lng = 2.3474
      }
      position {
        lat = 48.8608
        lng = 2.3410
      }
      position {
        lat = 48.8623
        lng = 2.3363
      }
    }

  }

  object MultiLineStrings {

    val RAIL_WAYS = multiLineString {
      lineString {
        position {
          lat = 48.8587
          lng = 2.3474
        }
        position {
          lat = 48.8608
          lng = 2.3410
        }
        position {
          lat = 48.8623
          lng = 2.3363
        }
      }
      lineString {
        position {
          lat = 48.8640
          lng = 2.3498
        }
        position {
          lat = 48.8625
          lng = 2.3457
        }
      }
    }

  }

  object Polygons {

    val BUILDING = polygon {
      ring {
        position {
          lat = 48.831176
          lng = 2.362688
        }
        position {
          lat = 48.830980
          lng = 2.362851
        }
        position {
          lat = 48.831088
          lng = 2.363168
        }
        position {
          lat = 48.831291
          lng = 2.362996
        }
        position {
          lat = 48.831176
          lng = 2.362688
        }
      }
      ring {
        position {
          lat = 48.831191
          lng = 2.362945
        }
        position {
          lat = 48.831136
          lng = 2.362859
        }
        position {
          lat = 48.831079
          lng = 2.362897
        }
        position {
          lat = 48.831106
          lng = 2.363018
        }
        position {
          lat = 48.831191
          lng = 2.362945
        }
      }
    }

  }

  object MultiPolygons {

    val BUILDINGS = multiPolygon {
      polygon {
        ring {
          position {
            lat = 48.831176
            lng = 2.362688
          }
          position {
            lat = 48.830980
            lng = 2.362851
          }
          position {
            lat = 48.831088
            lng = 2.363168
          }
          position {
            lat = 48.831291
            lng = 2.362996
          }
          position {
            lat = 48.831176
            lng = 2.362688
          }
        }
        ring {
          position {
            lat = 48.831191
            lng = 2.362945
          }
          position {
            lat = 48.831136
            lng = 2.362859
          }
          position {
            lat = 48.831079
            lng = 2.362897
          }
          position {
            lat = 48.831106
            lng = 2.363018
          }
          position {
            lat = 48.831191
            lng = 2.362945
          }
        }
      }
      polygon {
        ring {
          position {
            lat = 48.831166
            lng = 2.361937
          }
          position {
            lat = 48.831076
            lng = 2.361910
          }
          position {
            lat = 48.831106
            lng = 2.362146
          }
          position {
            lat = 48.831166
            lng = 2.361937
          }
        }
      }
    }

  }

  object GeometryCollections {

    val TUILERIES_GARDEN = geometryCollection {
      polygon {
        ring {
          position {
            lat = 48.866225
            lng = 2.323511
          }
          position {
            lat = 48.863874
            lng = 2.321569
          }
          position {
            lat = 48.860995
            lng = 2.329949
          }
          position {
            lat = 48.863740
            lng = 2.331794
          }
          position {
            lat = 48.866225
            lng = 2.323511
          }
        }
      }
      lineString {
        position {
          lat = 48.862364
          lng = 2.330839
        }
        position {
          lat = 48.865046
          lng = 2.322567
        }
      }
      point {
        lat = 48.864531
        lng = 2.324155
      }
    }

  }

  object Features {

    val PARIS = feature {
      id = "paris75000"
      point {
        lat = 48.8566
        lng = 2.3522
      }
      properties {
        "population" to 2_141_000
      }
    }

    val PARIS_GEOMETRY = feature {
      point {
        lat = 48.8566
        lng = 2.3522
      }
    }

  }
}

