plugins {
    alias(libs.plugins.satori.android.library)
    alias(libs.plugins.secrets)
    
}

android {
    namespace = "com.xsoft.satori.core.model"
    buildFeatures {
        buildConfig = true
    }
}
secrets{
    defaultPropertiesFileName = "constants.properties"
}

dependencies {

}