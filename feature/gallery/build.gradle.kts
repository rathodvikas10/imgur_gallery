plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.library.compose)
    alias(libs.plugins.app.feature)
    alias(libs.plugins.app.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.gallery"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.androidx.paging.compose)
    implementation(libs.material.icons.compose)
    implementation(libs.hilt.navigation.compose)

    // Testing dependencies
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.androidx.paging.testing)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}