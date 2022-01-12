rootProject.name = "geojson-jackson-jts-extensions"

pluginManagement {
  plugins {
    kotlin("jvm") version "${extra["version.kotlin"]}"
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}