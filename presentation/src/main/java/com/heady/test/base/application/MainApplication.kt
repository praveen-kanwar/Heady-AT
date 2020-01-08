package com.heady.test.base.application

import android.content.Context
import androidx.multidex.MultiDex
import com.google.gson.Gson
import com.heady.test.BuildConfig
import com.heady.test.base.application.di.component.DaggerApplicationComponent
import com.tejora.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

/**
 * Main Application
 *
 * Created by Praveen.
 */
class MainApplication : DaggerApplication() {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var utils: Utils

    // Provide Dagger Application Component
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }

    // To Add Multidex Support
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    // Application Created
    override fun onCreate() {
        super.onCreate()
        utils.enableLog(BuildConfig.DEBUG)
        utils.showLog(TAG, "Application Created")
    }

    companion object {
        private const val TAG = "MainApplication"
    }
}