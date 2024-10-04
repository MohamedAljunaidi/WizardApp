import common.AppConfig
import common.BaseGradle
import dependencies.Dependencies
import dependencies.DependencyGroups
import extensions.implementation
import extensions.kapt

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("common.base-android-library")
}


android{
    namespace = "com.example.navigation"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(Dependencies.lifeCycleRunTime)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)
    implementation(Dependencies.navigation)
    implementation(DependencyGroups.androidDependencies)

    implementation(DependencyGroups.junitAndroidTestImplementation)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    androidTestImplementation(Dependencies.composeTooling)
    androidTestImplementation(Dependencies.composeUiTestManifest)
}