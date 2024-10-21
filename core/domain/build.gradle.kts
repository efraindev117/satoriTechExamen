import com.xsoft.satori.build_logic.convention.implementation

plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.satori.android.hilt)
}

android {
    namespace = "com.xsoftcdmx.domain"

}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(libs.room.paging)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}