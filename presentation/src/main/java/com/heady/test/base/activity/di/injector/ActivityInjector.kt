package com.heady.test.base.activity.di.injector

import com.heady.test.base.activity.BaseActivity
import com.heady.test.base.activity.di.module.ActivityModule
import com.heady.test.base.fragment.di.injector.FragmentInjector
import com.heady.test.common.di.annotations.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Dagger Injector For Activities, Injecting Dependencies Of Activities Without Component
 *
 * Created by Praveen.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityInjector {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class,
            FragmentInjector::class]
    ) // BaseActivity Dependent Modules
    abstract fun contributesBaseActivity(): BaseActivity
}