import common.AppConfig
import dependencies.Dependencies
import dependencies.DependencyGroups
import extensions.implementation
import extensions.kapt

plugins {
    id("common.base-android-library")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.assignment.hometab"
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

    implementation(project(AppConfig.ModulePathsConstant.CORE))
    implementation(project(AppConfig.ModulePathsConstant.NAVIGATION))
    implementation(project(AppConfig.ModulePathsConstant.NETWORK))
    implementation(project(AppConfig.ModulePathsConstant.THEME))
    implementation(project(AppConfig.ModulePathsConstant.CACHING))

    implementation(Dependencies.lifeCycleRunTime)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.hiltDependencies)
    implementation(Dependencies.navigation)
    implementation(Dependencies.coil)
    kapt(DependencyGroups.hiltKaptDependencies)

    implementation(Dependencies.core)
    implementation(DependencyGroups.retrofitDependencies)
    implementation(DependencyGroups.coroutinesDependencies)
    androidTestImplementation(Dependencies.junitTestImplementation)
    androidTestImplementation(Dependencies.espressoAndroidTestImplementation)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    androidTestImplementation(Dependencies.composeTooling)
    androidTestImplementation(Dependencies.composeUiTestManifest)
}
