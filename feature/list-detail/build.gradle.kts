import com.xsoft.satori.build_logic.convention.implementation

plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.satori.android.feature)
    alias(libs.plugins.satori.android.room)
}

android {
    namespace = "com.xsoftcdmx.list_detail"

}

dependencies {
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.coil.compose) // Coil para Compose
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}