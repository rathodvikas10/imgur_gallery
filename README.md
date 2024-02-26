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

## Assumption
The Imgur Gallery API odes not provide a fixed page size during search. \
It is assumed that the device will display not more then 25 items. \

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

## Library:
- Jetpack Compose
- Dagger Hilt
- Kotlin Serialization
- Kotlin Coroutines
- Retfofit
- Paging 3