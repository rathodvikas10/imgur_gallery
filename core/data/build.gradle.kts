plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data"
}

dependencies {
    api(projects.core.network)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.testing)

    testImplementation(libs.kotlinx.coroutines.test)
}