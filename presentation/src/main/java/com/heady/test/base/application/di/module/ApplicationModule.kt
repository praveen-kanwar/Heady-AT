package com.heady.test.base.application.di.module

import android.content.Context
import com.google.gson.Gson
import com.heady.test.base.application.MainApplication
import com.heady.test.common.thread.UIThread
import com.heady.test.data.repository.DataRepository
import com.heady.test.data.thread.JobExecutionThread
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.common.threads.BackgroundThread
import com.heady.test.domain.common.threads.MainThread
import com.tejora.utils.IUtils
import com.tejora.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Application Module
 *
 * Created by Praveen.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(mainApplication: MainApplication): Context {
        return mainApplication.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): MainThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideRepository(dataRepository: DataRepository): Repository {
        return dataRepository
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutionThread: JobExecutionThread): BackgroundThread {
        return jobExecutionThread
    }

    @Provides
    @Singleton
    fun provideUtils(iutils: IUtils): Utils {
        return iutils
    }
}