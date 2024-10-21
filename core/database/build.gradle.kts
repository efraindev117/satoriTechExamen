plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.satori.android.room)
    alias(libs.plugins.satori.android.hilt)
}

android {
    namespace = "com.xsoftcdmx.database"
}

dependencies {
    implementation(libs.converter.gson)
    implementation(libs.room.paging)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:model"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}