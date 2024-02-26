plugins {
    alias(libs.plugins.app.library)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    api(projects.core.data)

    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.javax.inject)

    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.paging.testing)
}