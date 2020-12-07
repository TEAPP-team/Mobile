plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")

    id("kotlin-android")

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
    implementation("com.yandex.android:maps.mobile:4.0.0-lite")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.2.5")
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

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
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