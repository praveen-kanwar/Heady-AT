package com.heady.test.modules.subcategories.di

import com.heady.test.base.fragment.di.module.FragmentModule
import com.heady.test.modules.subcategories.fragments.SubCategoriesFragment
import com.heady.test.modules.subcategories.views.SubCategoriesFragmentView
import dagger.Binds
import dagger.Module

/**
 * Providing Dependencies Of SubCategories Module
 *
 * Created by Praveen.
 */
@Module
abstract class SubCategoriesModule :
    FragmentModule<SubCategoriesFragment, SubCategoriesFragmentView> {

    @Binds
    abstract override fun provideFragmentView(fragment: SubCategoriesFragment): SubCategoriesFragmentView
}