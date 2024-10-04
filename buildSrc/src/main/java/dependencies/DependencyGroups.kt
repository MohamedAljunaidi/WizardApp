package dependencies

/**
 *  The DependencyGroups class was created
 *  to combine all common dependencies into a
 *  single object..
 */
object DependencyGroups {

    val coroutinesDependencies = arrayListOf<String>().apply {
        add(Dependencies.coroutinesCore)
        add(Dependencies.coroutinesAndroid)
        add(Dependencies.kotlin)
    }


    val hiltDependencies = arrayListOf<String>().apply {
        add(Dependencies.hiltAndroid)
        add(Dependencies.hiltNavigation)
    }

    val hiltKaptDependencies = arrayListOf<String>().apply {
        add(Dependencies.hiltCompilerKapt)
        add(Dependencies.hiltComposeCompilerKept)
    }


    val retrofitDependencies = arrayListOf<String>().apply {
        add(Dependencies.retrofit)
        add(Dependencies.gson)
        add(Dependencies.converterGson)
        add(Dependencies.loggingInterceptor)
    }


    val androidDependencies = arrayListOf<String>().apply {
        add(Dependencies.core)
    }

    val junitTestImplementationDependencies = arrayListOf<String>().apply {
        add(Dependencies.junitTestImplementation)
    }

    val junitAndroidTestImplementation = arrayListOf<String>().apply {
        add(Dependencies.junitExtAndroidTestImplementation)
        add(Dependencies.espressoAndroidTestImplementation)
    }

    val roomDependencies = arrayListOf<String>().apply {
        add(Dependencies.room)
        add(Dependencies.roomPaging)
    }
}