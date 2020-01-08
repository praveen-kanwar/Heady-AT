package com.heady.test.modules.splash.di

import com.heady.test.base.fragment.di.module.FragmentModule
import com.heady.test.modules.splash.fragments.SplashFragment
import com.heady.test.modules.splash.views.SplashFragmentView
import dagger.Binds
import dagger.Module

/**
 * Providing Dependencies Of Splash Module
 *
 * Created by Praveen on 01-07-2019.
 */
@Module
abstract class SplashModule : FragmentModule<SplashFragment, SplashFragmentView> {

    @Binds
    abstract override fun provideFragmentView(fragment: SplashFragment): SplashFragmentView
}