package com.heady.test.base.application.di.component

import com.heady.test.base.activity.di.injector.ActivityInjector
import com.heady.test.base.application.MainApplication
import com.heady.test.base.application.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Dagger Application Component
 *
 * Created by Praveen.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityInjector::class]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}