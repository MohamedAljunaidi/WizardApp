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
    namespace = "com.assignment.network"
}

dependencies {
    implementation(project(common.AppConfig.ModulePathsConstant.CORE))

    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)
    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.retrofitDependencies)
    implementation(DependencyGroups.coroutinesDependencies)
    implementation(Dependencies.navigation)
    implementation(DependencyGroups.junitAndroidTestImplementation)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    androidTestImplementation(Dependencies.composeTooling)
    androidTestImplementation(Dependencies.composeUiTestManifest)
}