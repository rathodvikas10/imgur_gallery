plugins {
    alias(libs.plugins.app.application)
    alias(libs.plugins.app.hilt)
}

android {
    namespace = "com.example.imgurgallery"

    defaultConfig {
        applicationId = "com.example.imgurgallery"
        versionCode = 1
        versionName = "1.0"
    }

}

dependencies {
    implementation(projects.feature.gallery)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}