plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.network"
}

dependencies {
    api(projects.core.common)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.json)
}