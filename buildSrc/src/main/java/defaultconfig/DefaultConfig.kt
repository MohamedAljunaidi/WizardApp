package defaultconfig

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import common.AppConfig
import org.gradle.api.JavaVersion

/**
 *
 * The DefaultConfig for the app and library module are handled by the "DefaultConfig" object.
 */
object DefaultConfig {

    fun initAppDefaultConfig(baseAppModuleExtension: BaseAppModuleExtension) {
        baseAppModuleExtension.apply {
            compileSdk = AppConfig.AppConfigConstant.COMPILE_SDK
            buildToolsVersion = AppConfig.AppConfigConstant.BUILD_TOOLS_VERSION
            defaultConfig {
                versionCode = AppConfig.AppConfigConstant.VERSION_CODE
                minSdk = AppConfig.AppConfigConstant.MIN_SDK
                applicationId = AppConfig.AppConfigConstant.APPLICATION_ID
                targetSdk = AppConfig.AppConfigConstant.TARGET_SDK
                versionName = AppConfig.AppConfigConstant.VERSION_NAME
                testInstrumentationRunner = AppConfig.AppConfigConstant.TEST_INSTRUMENTATION_RUNNER

            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildFeatures {
                dataBinding = true
                buildConfig = true
            }

        }
    }

    fun initLibraryDefaultConfig(libraryExtension: LibraryExtension) {
        libraryExtension.apply {
            compileSdk = AppConfig.AppConfigConstant.COMPILE_SDK
            defaultConfig {
                minSdk = AppConfig.AppConfigConstant.MIN_SDK
                testInstrumentationRunner = AppConfig.AppConfigConstant.TEST_INSTRUMENTATION_RUNNER
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildFeatures {
                dataBinding = true
                buildConfig = true
            }

        }
    }


}