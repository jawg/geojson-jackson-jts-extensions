rootProject.name = "geojson-jackson-jts-extensions"

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
