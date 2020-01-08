package com.heady.test.base.fragment.di.module

import com.heady.test.base.fragment.BaseFragment
import com.heady.test.common.view.BaseView

/**
 * Contract For All Fragment Module To Provide There View As Interface Object For Abstraction
 *
 * Created by Praveen.
 */
interface FragmentModule<Fragment : BaseFragment, FragmentView : BaseView> {

    fun provideFragmentView(fragment: Fragment): FragmentView
}