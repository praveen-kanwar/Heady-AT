package com.heady.test.common.di.annotations

import javax.inject.Scope

/**
 * Annotation For Defining Object Scope In Fragment via Modules
 *
 * Created by Praveen.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class FragmentScope