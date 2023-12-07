import Versions.NAV_VERSION

object Versions {
    const val NAV_VERSION = "2.6.0-alpha03"

}

object Kotlin {
    const val SDK = "org.jetbrains.java:java-stdlib-jdk8:1.5.21"
}

object AndroidX {
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.2"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
    const val ACTIVITY_VERSION = "androidx.activity:activity-ktx:1.4.0-alpha01"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:1.7.0-alpha02"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.6.1"
}

object Test {
    const val JUNIT = "junit:junit:4.+"
    const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
}

object Glide {
    const val GLIDE_VERSION = "com.github.bumptech.glide:glide:4.12.0"
}

object Gson {
    const val GSON_VERSION = "com.google.code.gson:gson:2.8.9"
}


object AndroidTest {
    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val TEST_RUNNER = "androidx.test:runner:1.4.0"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
}

object DaggerHilt {
    const val DAGGER_HILT_VERSION = "2.42"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.42"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.42"
    const val DAGGER_HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:1.0.0"
    const val DAGGER_HILT_JAVAX = "javax.inject:javax.inject:1"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val CONVERTER_JAXB = "com.squareup.retrofit2:converter-jaxb:2.9.0"
}

object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object Coroutines {
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
}

object NavComponent {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
    const val NAVIGATION_FEATURE =
        "androidx.navigation:navigation-dynamic-features-fragment:$NAV_VERSION"
    const val NAVIGATION_KOTLIN = "androidx.fragment:fragment-ktx:1.5.4"
}

object TestTool {
    const val JUNIT = "junit:junit:4.+"
    const val ANDROID_X_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val ANDROID_X_ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
}


object Paging {
    const val PAING = "3.2.1"
    const val PAGING_COMMON = "androidx.paging:paging-common:$PAING"
    const val PAGING_RUMTIME = "androidx.paging:paging-runtime-ktx:$PAING"
}