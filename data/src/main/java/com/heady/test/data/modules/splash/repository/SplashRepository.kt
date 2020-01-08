package com.heady.test.data.modules.splash.repository

import com.heady.test.data.modules.splash.dao.SplashDao
import com.heady.test.data.modules.splash.restclient.SplashRestClient
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Repository For Splash Module, Responsible For Web & Local Data Operations
 *
 * Created by Praveen on 01-07-2019.
 */
class SplashRepository
@Inject
constructor(
    private val utils: Utils,
    private val splashDao: SplashDao,
    private val splashRestClient: SplashRestClient
) {

    fun saveData(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val TAG = "SplashRepository"
    }
}