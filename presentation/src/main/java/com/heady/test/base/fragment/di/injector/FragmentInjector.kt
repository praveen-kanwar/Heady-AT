package com.heady.test.base.fragment.di.injector

import com.heady.test.base.fragment.BaseFragment
import com.heady.test.common.di.annotations.FragmentScope
import com.heady.test.modules.dashboard.fragments.DashboardFragment
import com.heady.test.modules.splash.di.SplashModule
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
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributesSplashFragment(): SplashFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesDashboardFragment(): DashboardFragment

}