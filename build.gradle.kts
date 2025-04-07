import com.vanniktech.maven.publish.SonatypeHost

plugins {
  kotlin("jvm")
  id("com.github.ben-manes.versions")
  id("com.vanniktech.maven.publish")
}

description = "Kotlin extensions to convert GeoJSON to JTS and vice-versa"

group = "io.jawg.geojson"
version = "${property("version")}"

kotlin {
  jvmToolchain(21)
}

tasks {
  test {
    useJUnitPlatform()
  }
}

mavenPublishing {
  publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
  signAllPublications()

  pom {
    name.set("Kotlin extensions to convert GeoJSON to JTS and vice-versa")
    description.set("Kotlin extensions to convert GeoJSON to JTS and vice-versa")
    url.set("https://github.com/jawg/geojson-jackson-jts-extensions")
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
      }
    }
    organization {
      name.set("Jawg")
      url.set("https://jawg.io")
    }
    developers {
      developer {
        id.set("jawg")
        name.set("Jawg")
        email.set("contact@jawg.io")
      }
    }
    scm {
      connection.set("scm:git:git://git@github.com:jawg/geojson-jackson-jts-extensions.git")
      developerConnection.set("scm:git:ssh://git@github.com:jawg/geojson-jackson-jts-extensions.git")
      url.set("https://github.com/jawg/geojson-jackson-jts-extensions")
    }
  }
}

dependencies {
  api("io.jawg.geojson:geojson-jackson:${property("version.jawg.geojson-jackson")}")
  api("org.locationtech.jts:jts-core:${property("version.jts")}")
  testImplementation(kotlin("test-junit5"))
}
