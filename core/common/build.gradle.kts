plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.hilt)
}

android {
    namespace = "com.example.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}