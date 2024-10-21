import com.xsoft.satori.build_logic.convention.implementation

plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.satori.android.feature)
}

android {
    namespace = "com.xsoftcdmx.ui"

}

dependencies {
    implementation(libs.coil.compose)

}