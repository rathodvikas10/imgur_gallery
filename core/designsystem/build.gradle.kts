plugins {
    alias(libs.plugins.app.library)
    alias(libs.plugins.app.library.compose)
}

android {
    namespace = "com.example.designsystem"
}

dependencies {
    api(libs.androidx.core.ktx)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.material3)
    api(libs.coil)
    api(libs.coil.compose)

    api(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}