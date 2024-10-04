package com.assignment.extensions


fun substitutePathParams(
    path: String,
    pathParams: Map<String, String>?
): String {
    var newPath = path
    if (pathParams != null) {
        for ((key, value) in pathParams) {
            val keyAsParam = key.toPathParamRepresentation()
            newPath = newPath.replace(keyAsParam, value)
        }
    }
    return newPath
}

private operator fun <K, V> Map<out K, V>.plus(map: Map<out K, V>?): Map<K, V> =
    LinkedHashMap(this).apply {
        if (map != null) putAll(map)
    }

private fun String.toPathParamRepresentation() = "{$this}"