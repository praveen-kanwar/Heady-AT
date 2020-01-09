package com.heady.test.base.fragment.di.injector

import com.heady.test.base.fragment.BaseFragment
import com.heady.test.common.di.annotations.FragmentScope
import com.heady.test.modules.categories.di.CategoriesModule
import com.heady.test.modules.categories.fragments.CategoriesFragment
import com.heady.test.modules.splash.fragments.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger Injector For Fragment, Injecting Dependencies Of Fragments Without Component.
 *
 * All Fragment Dependencies To Be Defined Her For Injecting Without Component.
 *
 * Created by Praveen.
 */
@Module
abstract class FragmentInjector {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesBaseFragment(): BaseFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesSplashFragment(): SplashFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CategoriesModule::class])
    abstract fun contributesCategoriesFragment(): CategoriesFragment

}