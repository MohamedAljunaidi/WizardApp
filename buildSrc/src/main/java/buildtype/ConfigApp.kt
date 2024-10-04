package buildtype

import com.android.build.api.dsl.BuildType
import common.AppConfig
import extensions.buildConfigStringField
import java.util.Properties

/**
 * The `ConfigApp` class to set up our configurations,
 * such as buildConfigField and manifestPlaceholders.
 */

object ConfigApp {

    fun initConfigData(buildType: BuildType, properties: Properties) {

        buildType.apply {

            manifestPlaceholders[AppConfig.ManifestConstant.APPLICATION_LABEL_KEY] =
                properties.getProperty(AppConfig.ManifestConstant.APPLICATION_LABEL_VALUE)


            //base URL
            buildConfigStringField(
                AppConfig.BuildConfigField.BASE_URL_KEY,
                properties.getProperty(AppConfig.BuildConfigField.BASE_URL_VALUE)
            )

        }
    }
}