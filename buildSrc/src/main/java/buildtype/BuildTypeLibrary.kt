package buildtype

import com.android.build.api.dsl.LibraryExtension
import common.AppConfig
import extensions.extractProperties
import org.gradle.api.Project

/**
 *
 * The `BuildTypeLibrary` class for (Library plugin).
 * Here, we set up our build types, such as debug, staging, and release,
 * as well as any configurations, such as buildConfigField and manifestPlaceholders.
 */

object BuildTypeLibrary {

    fun initLibraryBuildType(libraryExtension: LibraryExtension, project: Project) {

        libraryExtension.apply {
            buildTypes {
                all {
                    val properties= project.extractProperties(this)
                    ConfigApp.initConfigData(buildType = this, properties = properties)
                }
                getByName(AppConfig.BuildTypeConstant.DEBUG) {
                    isMinifyEnabled = false
                    signingConfig = signingConfigs.getByName(AppConfig.BuildTypeConstant.DEBUG)
                }
                maybeCreate(AppConfig.BuildTypeConstant.STAGING)
                getByName(AppConfig.BuildTypeConstant.STAGING) {
                    initWith(getByName(AppConfig.BuildTypeConstant.DEBUG))
                    isMinifyEnabled = true
                }

                maybeCreate(AppConfig.BuildTypeConstant.RELEASE)
                getByName(AppConfig.BuildTypeConstant.RELEASE) {
                    isMinifyEnabled = true
                }
            }
        }
    }
}