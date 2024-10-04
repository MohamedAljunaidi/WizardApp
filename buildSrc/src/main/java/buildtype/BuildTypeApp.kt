package buildtype

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import common.AppConfig
import common.AppConfig.BuildTypeConstant
import extensions.extractProperties
import org.gradle.api.Project

/**
 * The `BuildTypeApp` class for (application plugin).
 * Here, we set up our build types, such as debug, staging, and release,
 * as well as any configurations, such as buildConfigField and manifestPlaceholders.
 */

object BuildTypeApp {

    fun initAppBuildType(baseAppModuleExtension: BaseAppModuleExtension, project: Project) {

        baseAppModuleExtension.apply {
            buildTypes {

                applicationVariants.all {
                    val variant = this
                    variant.outputs
                        .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                        .forEach { output ->

                            val projectName = AppConfig.AppConfigConstant.APPLICATION_NAME
                            val buildType = variant.buildType.name
                            val version = variant.versionName
                            val outputFileName = "${projectName}-${buildType}-${version}.apk"
                            output.outputFileName = outputFileName
                        }
                }
                all {
                    val properties= project.extractProperties(this)
                    ConfigApp.initConfigData(buildType = this, properties = properties)
                }

                getByName(BuildTypeConstant.DEBUG) {
                    isMinifyEnabled = false
                    signingConfig = signingConfigs.getByName(BuildTypeConstant.DEBUG)
                }

                maybeCreate(BuildTypeConstant.STAGING)
                getByName(BuildTypeConstant.STAGING) {
                    initWith(getByName(BuildTypeConstant.DEBUG))
                    isMinifyEnabled = true
                }

                maybeCreate(BuildTypeConstant.RELEASE)
                getByName(BuildTypeConstant.RELEASE) {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }
    }
}