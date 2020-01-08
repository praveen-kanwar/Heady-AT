package com.heady.test.data.database

import android.content.Context
import com.tejora.utils.Utils
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Realm Database Configuration Provider
 *
 * Created by Praveen.
 */
class DatabaseConfig
@Inject
constructor(
    val context: Context,
    val utils: Utils
) {

    @Singleton
    fun initializeRealmDatabase() {
        utils.showLog(TAG, "initializeRealmDatabase()")
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
            .name(DATABASE_NAME)
            .encryptionKey(utils.fetchDatabaseEncryptionKey())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    companion object {
        private const val TAG = "DatabaseConfig"
        private const val DATABASE_NAME = "heady.realm"
    }
}