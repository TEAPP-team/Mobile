plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("plugin.serialization") version "1.4.10"
}
group = "com.github.vsbauer"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}
android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.github.vsbauer.teapp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    android {
        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
            exclude ("META-INF/LICENSE")
            exclude ("META-INF/LICENSE.txt")
            exclude ("META-INF/license.txt")
            exclude ("META-INF/NOTICE")
            exclude ("META-INF/NOTICE.txt")
            exclude ("META-INF/notice.txt")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/*.kotlin_module")
        }
    }
}