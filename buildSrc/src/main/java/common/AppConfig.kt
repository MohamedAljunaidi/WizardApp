package common

/**
 *  The `AppConfig` object held all of the constants that we utilized in our Gradle project.
 */

object AppConfig {

    object AppConfigConstant {
        const val COMPILE_SDK = 34
        const val MIN_SDK = 24
        const val TARGET_SDK = 34
        const val VERSION_NAME = "1.0.0"
        const val VERSION_CODE = 1
        const val BUILD_TOOLS_VERSION = "34.0.0"
        const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
        const val JVM_TARGET = "1.8"
        const val APPLICATION_ID = "com.assignment.myapplication"
        const val APPLICATION_NAME = "Wizard App"

    }
    object ManifestConstant {
        const val APPLICATION_LABEL_KEY = "applicationLabel"
        const val APPLICATION_LABEL_VALUE = "applicationlabel"
    }
    object BuildConfigField {
        const val BASE_URL_KEY = "BASE_URL"
        const val BASE_URL_VALUE = "base_url"
    }

    object BuildTypeConstant {
        const val DEBUG = "debug"
        const val STAGING = "staging"
        const val RELEASE = "release"
    }

    object ModulePathsConstant {
        const val CORE = ":core"
        const val NETWORK = ":network"
        const val NAVIGATION = ":navigation"
        const val THEME = ":theme"
        const val CACHING = ":caching"
        const val HOME_TAB = ":features:hometab"
        const val SEARCH = ":features:search"
    }


}