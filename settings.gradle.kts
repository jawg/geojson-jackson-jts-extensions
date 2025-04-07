rootProject.name = "geojson-jackson-jts-extensions"

pluginManagement {
  plugins {
    kotlin("jvm") version "${extra["version.kotlin"]}"
    id("com.github.ben-manes.versions") version "${extra["version.versions.plugin"]}"
  }
}

dependencyResolutionManagement {
  repositories {
    maven {
      name = "Central Portal Snapshots"
      url = uri("https://central.sonatype.com/repository/maven-snapshots/")

      // Only search this repository for the specific dependency
      content {
        includeModule("io.jawg.geojson", "geojson-jackson")
      }
    }
    mavenCentral()
  }
}
