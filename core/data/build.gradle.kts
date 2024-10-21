import com.xsoft.satori.build_logic.convention.implementation

plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.satori.android.hilt)
    alias(libs.plugins.satori.android.room)
}

android {
    namespace = "com.xsoft.satori.core.data"
}

dependencies {
    implementation(libs.room.paging)
    implementation(libs.retrofit)
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}