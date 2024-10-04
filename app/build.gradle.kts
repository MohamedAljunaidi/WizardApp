import common.AppConfig
import common.BaseGradle
import dependencies.Dependencies
import dependencies.DependencyGroups
import extensions.implementation
import extensions.kapt

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
}


android {
    namespace = "com.assignment.wizardapp"
    BaseGradle.appGradle(this, project = project)
    kotlinOptions {
        jvmTarget = AppConfig.AppConfigConstant.JVM_TARGET
    }


    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(AppConfig.ModulePathsConstant.HOME_TAB))
//    implementation(project(AppConfig.ModulePathsConstant.SEARCH))
    implementation(project(AppConfig.ModulePathsConstant.NAVIGATION))
    implementation(project(AppConfig.ModulePathsConstant.THEME))

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
