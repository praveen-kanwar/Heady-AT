package com.heady.test.data.modules.splash.dao

import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Dao For Splash Module To Perform Operations On Local Database
 *
 * Created by Praveen.
 */
class SplashDao
@Inject
constructor(
    private val utils: Utils
) {

    /*
     * Save Data To Local DB.
     */
    fun saveData(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val TAG = "SplashDao"
    }
}