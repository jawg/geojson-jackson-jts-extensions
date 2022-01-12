plugins {
  `maven-publish`
  signing
  kotlin("jvm")
}

description = "Kotlin extensions to convert GeoJSON to JTS and vice-versa"

group = "io.jawg.geojson"
version = "${property("version")}"

val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  test {
    useJUnitPlatform()
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
  }

  javadoc {
    if (JavaVersion.current().isJava9Compatible) {
      (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
  }
}

publishing {
  repositories {
    maven {
      val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
      val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
      url = uri(if (isReleaseVersion) releasesRepoUrl else snapshotsRepoUrl)
      credentials {
        val ossrhUsername: String? by project
        val ossrhPassword: String? by project
        username = ossrhUsername
        password = ossrhPassword
      }
    }
  }
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
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
  }
}

signing {
  sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign>().configureEach {
  onlyIf { isReleaseVersion }
}

dependencies {
  api("io.jawg.geojson:geojson-jackson:${property("version.jawg.geojson-jackson")}")
  api("org.locationtech.jts:jts-core:${property("version.jts")}")
  testImplementation(kotlin("test-junit5"))
}