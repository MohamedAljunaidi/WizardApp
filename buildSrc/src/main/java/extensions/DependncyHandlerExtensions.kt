package extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Extension to adds a list of third party that used kept function.
 *
 * @param list the list of the group Dependencies
 */
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

/**
 * Extension to adds a list of third party that used implementation function.
 *
 * @param list the list of the group Dependencies
 */
fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

/**
 * Extension to adds a list of third party that used androidTestImplementation function.
 *
 * @param list the list of the group Dependencies
 */
fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

/**
 * Extension to adds a list of third party that used testImplementation function.
 *
 * @param list the list of the group Dependencies
 */
fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
