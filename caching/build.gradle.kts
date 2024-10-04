import dependencies.Dependencies
import dependencies.DependencyGroups
import extensions.implementation
import extensions.kapt

plugins {
    id("common.base-android-library")
}

android{
    namespace = "com.assignment.caching"
}

dependencies {
    implementation(project(common.AppConfig.ModulePathsConstant.CORE))

    implementation(DependencyGroups.hiltDependencies)
    kapt(DependencyGroups.hiltKaptDependencies)

    kapt(Dependencies.roomCompiler)
    implementation(DependencyGroups.roomDependencies)
    implementation(Dependencies.gson)
    
    implementation(DependencyGroups.androidDependencies)
    implementation(DependencyGroups.junitTestImplementationDependencies)
    implementation(DependencyGroups.junitAndroidTestImplementation)
}

