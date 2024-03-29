# Imgur Gallery
Search the top images of the week from Imgur Gallery

## Feature
- Search the top images of the week
- View the search result in grit or list

## Developer Setup
- Android Studio Jellyfish
- Gradle Version: 8.6
- Android Gradle Plugin: 8.4.0-alpha09
- Kotlin Version: 1.9.21
- JVM Version: 17

## Build and Testing
###### Build:
APK Path: imgur_gallery/app/build/outputs/apk/release
```
./gradlew assembleRelease
```
###### Testing:
```
./gradlew testDebug
```
###### Lint:
```
./gradlew lint
```

## Assumption
The Imgur Gallery API odes not provide a fixed page size during search. \
It is assumed that the device will display not more than 25 items. 

## TODO
- Improve error handling
- Improve loading state of items in pagination
- Add next page loading state
- Performance optimization
- Static code analysis 

## Application Architecture
The application architecture is designed using multi-modular approach. \
This has the following advantages : 
- Easy task distribution
- Scalable and maintainable application
- Low build time by using parallel build process
- Easy to test and generate reposts

#### Architecture structure :
- app: The main application
- core:module: The application models
- core:common: A common sharable code used across all the app and modules
- core:network: Network client and API configuration and setup
- core:data: Single source of truth for data
- core:domain: Use case based on features
- feature:gallery: Display top images of the week

## Library:
- Jetpack Compose
- Dagger Hilt
- Kotlin Serialization
- Kotlin Coroutines
- Retfofit Okhttp
- Paging 3
- Coil
- Robolectric
- Turbine
- JUnit4