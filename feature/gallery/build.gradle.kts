plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.library.compose)
    alias(libs.plugins.app.feature)
    alias(libs.plugins.app.hilt)
}

android {
    namespace = "com.example.gallery"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.androidx.paging.compose)
    implementation(libs.material.icons.compose)
    implementation(libs.hilt.navigation.compose)
}