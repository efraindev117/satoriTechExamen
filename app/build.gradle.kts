import com.xsoft.satori.build_logic.convention.implementation

plugins {
    alias(libs.plugins.satori.android.application)
    alias(libs.plugins.satori.android.application.compose)
    alias(libs.plugins.satori.android.hilt)

}

android {
    namespace = "com.xsoft.satori"

    defaultConfig {
        applicationId = "com.xsoft.satori"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}

dependencies {
    implementation(libs.coil.kt.coil)
    implementation(libs.coil.compose)

    implementation(libs.room.paging)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(project(":feature:list-detail"))
    implementation(project(":core:designsystem"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.compose)
}
