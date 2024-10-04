package extensions

import com.android.build.api.dsl.BuildType
import common.AppConfig
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

fun Project.extractProperties(buildType: BuildType): Properties {
    val project=this
    buildType.apply {
        var typeName = name.lowercase(Locale.ROOT)
        if (typeName.lowercase(Locale.ROOT).contentEquals(AppConfig.BuildTypeConstant.STAGING))
            typeName = AppConfig.BuildTypeConstant.DEBUG
        val propertiesFile =
            project.rootProject.file("properties/${typeName}.properties")
        val properties = Properties()
        properties.load(FileInputStream(propertiesFile))
        return properties
    }

}


