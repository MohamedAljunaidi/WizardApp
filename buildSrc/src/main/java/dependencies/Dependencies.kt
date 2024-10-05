package dependencies

/**
 *  The Dependencies class includes every third party that we used in Gradle.
 */
object Dependencies {
    const val core = "androidx.core:core-ktx:${DependencyVersions.core_version}"
    const val junitTestImplementation =
        "junit:junit:${DependencyVersions.junitTestImplementation_version}"
    const val junitExtAndroidTestImplementation =
        "androidx.test.ext:junit:${DependencyVersions.junitExtAndroidTestImplementation_version}"
    const val espressoAndroidTestImplementation =
        "androidx.test.espresso:espresso-core:${DependencyVersions.espressoAndroidTestImplementation_version}"

    const val lifeCycleRunTime =
        "androidx.lifecycle:lifecycle-runtime-compose-android:${DependencyVersions.life_cycle_runtime_ktx_version}"

    const val activityCompose =
        "androidx.activity:activity-compose:${DependencyVersions.activity_compose}"

    const val composeBom = "androidx.compose:compose-bom:${DependencyVersions.compose_bom}"

    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"


    const val composeTooling = "androidx.compose.ui:ui-tooling"


    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"

    const val composeUiTestManifest = "androidx.compose.ui:ui-test-junit4"

    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"

    const val composeMaterial3 =
        "androidx.compose.material3:material3:${DependencyVersions.material_version}"


    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.coroutines_version}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutines_version}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${DependencyVersions.kotlin_version}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${DependencyVersions.hilt_version}"
    const val hiltCompilerKapt =
        "com.google.dagger:hilt-android-compiler:${DependencyVersions.hilt_version}"
    const val hiltComposeCompilerKept =
        "androidx.hilt:hilt-compiler:${DependencyVersions.hilt_compose_compiler_version}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${DependencyVersions.hilt_navigation_version}"
    const val navigation =
        "androidx.navigation:navigation-compose:${DependencyVersions.navigation_version}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DependencyVersions.retrofit_version}"
    const val gson = "com.google.code.gson:gson:${DependencyVersions.gson_version}"
    const val converterGson =
        "com.squareup.retrofit2:converter-gson:${DependencyVersions.retrofit_version}"

    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.okhttp_version}"

    const val coil =
        "io.coil-kt:coil-compose:${DependencyVersions.coil_version}"

    const val roomPaging = "androidx.room:room-paging:${DependencyVersions.room_version}"
    const val room = "androidx.room:room-ktx:${DependencyVersions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${DependencyVersions.room_version}"


    const val mockitoCore = "org.mockito:mockito-core:${DependencyVersions.mockito_core_version}"
    const val mockitoInline =
        "org.mockito:mockito-inline:${DependencyVersions.mockito_inline_version}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${
        DependencyVersions.coroutines_test_version
    }"
    const val turbine = "app.cash.turbine:turbine:${DependencyVersions.turbine_version}"

}