package com.heady.test.modules.categories.di

import com.heady.test.base.fragment.di.module.FragmentModule
import com.heady.test.modules.categories.fragments.CategoriesFragment
import com.heady.test.modules.categories.views.CategoriesFragmentView
import dagger.Binds
import dagger.Module

/**
 * Providing Dependencies Of Categories Module
 *
 * Created by Praveen.
 */
@Module
abstract class CategoriesModule : FragmentModule<CategoriesFragment, CategoriesFragmentView> {

    @Binds
    abstract override fun provideFragmentView(fragment: CategoriesFragment): CategoriesFragmentView
}