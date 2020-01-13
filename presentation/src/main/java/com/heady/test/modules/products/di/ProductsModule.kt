package com.heady.test.modules.products.di

import com.heady.test.base.fragment.di.module.FragmentModule
import com.heady.test.modules.products.fragments.ProductsFragment
import com.heady.test.modules.products.views.ProductsFragmentView
import dagger.Binds
import dagger.Module

/**
 * Providing Dependencies Of Products Module
 *
 * Created by Praveen.
 */
@Module
abstract class ProductsModule :
    FragmentModule<ProductsFragment, ProductsFragmentView> {

    @Binds
    abstract override fun provideFragmentView(fragment: ProductsFragment): ProductsFragmentView
}