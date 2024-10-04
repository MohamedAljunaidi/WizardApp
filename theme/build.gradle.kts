import dependencies.Dependencies
import dependencies.DependencyGroups
import extensions.implementation
import extensions.kapt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("common.base-android-library")
}


android {
    namespace = "com.assignment.theme"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(Dependencies.navigation)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)

    implementation(Dependencies.core)

    androidTestImplementation(Dependencies.junitTestImplementation)
    androidTestImplementation(Dependencies.espressoAndroidTestImplementation)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    androidTestImplementation(Dependencies.composeTooling)
    androidTestImplementation(Dependencies.composeUiTestManifest)
}