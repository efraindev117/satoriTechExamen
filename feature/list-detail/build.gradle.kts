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
    implementation("androidx.paging:paging-runtime:3.2.0")
    implementation("androidx.paging:paging-compose:3.2.0")
    implementation("io.coil-kt:coil-compose:2.4.0") // Coil para Compose
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}