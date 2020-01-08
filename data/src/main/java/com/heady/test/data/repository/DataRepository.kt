package com.heady.test.data.repository

import com.heady.test.data.database.DatabaseConfig
import com.heady.test.data.modules.splash.repository.SplashRepository
import com.heady.test.domain.common.repository.Repository
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Data Repository Responsible For Fetching Data From Local Database & Web API.
 *
 * Created by Praveen.
 */
class DataRepository
@Inject
constructor(
    databaseConfig: DatabaseConfig,
    private val splashRepository: SplashRepository,
    private val utils: Utils
) : Repository {


    init {
        databaseConfig.initializeRealmDatabase()
    }

    /*
     * To Fetch All Data
     */
    override fun fetchData(): Observable<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val TAG = "DataRepository"
    }

}