plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.android_teamfresh_kgi"
    compileSdk = SdkVersions.compileSdk

    defaultConfig {
        applicationId = "com.example.android_teamfresh_kgi"
        minSdk = SdkVersions.minSdk
        targetSdk = SdkVersions.targetSdk
        versionCode = AppVersions.androidVersionCode
        versionName = AppVersions.androidVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {

    implementation(KTX.CORE)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    implementation(AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(TestTool.JUNIT)
    androidTestImplementation(TestTool.ANDROID_X_JUNIT)
    androidTestImplementation(TestTool.ANDROID_X_ESPRESSO)

    // dager hilt
    implementation(DaggerHilt.DAGGER_HILT)
    kapt(DaggerHilt.DAGGER_HILT_COMPILER)
    kapt(DaggerHilt.DAGGER_HILT_ANDROIDX_COMPILER)

    // Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_GSON)
    implementation(Retrofit.CONVERTER_JAXB)

    //okHttp
    implementation(OkHttp.OKHTTP)
    implementation(OkHttp.LOGGING_INTERCEPTOR)
}