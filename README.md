# JTS extensions for GeoJSON

Kotlin extensions to convert GeoJSON to JTS and vice-versa.

## Requirements
* Java 8+

## Dependency

Add the dependency in your ```dependencies { ... }```:
```kotlin
implementation("io.jawg.geojson:geojson-jackson-jts-extensions:{version}")
```

For SNAPSHOT versions add the repository in your ```repositories { ... }```:
```kotlin
maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
```

## Usage
Convert a GeoJSON ```Geometry``` to a JTS `Geometry`:
```kotlin
val geoJsonGeometry: Geometry = ...
val jtsGeometry = geoJsonGeometry.toJts()

// or on Features
val feature: Feature = ...
val jtsGeometry = feature.toJts() // feature id and properties will be stored in userData
```

Convert a JTS `Geometry` to a GeoJSON ```Geometry```:
```kotlin
val jtsGeometry = ...
val geoJsonGeometry = jtsGeometry.toGeoJson()

// or to Feature
val jtsGeometry = ...
val feature = jtsGeometry.toFeature() // feature id and properties will be retrieved from userData

```

## Build the library

```bash
./gradlew build
```

## Publish the library
```bash
./gradlew publish
```
