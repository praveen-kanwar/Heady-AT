package com.heady.test.data.modules.products.dao

import com.google.gson.Gson
import com.heady.test.data.modules.products.models.RealmProductModel
import com.tejora.utils.Utils
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

/**
 * Dao For Products Module To Perform Operations On Local Database
 *
 * Created by Praveen.
 */
class ProductsDao
@Inject
constructor(
    private val gson: Gson,
    private val utils: Utils
) {

    /*
     * Save Data To Local DB.
     */
    fun saveProducts(realmProductModelList: List<RealmProductModel>): Observable<Boolean> {
        return Observable.create { emitter ->
            utils.showLog(
                TAG,
                "Saving Products To Local Database -> ${gson.toJson(realmProductModelList)}"
            )
            val database: Realm = Realm.getDefaultInstance()
            try {
                database.executeTransaction { realm ->
                    realm.insert(realmProductModelList)
                }
                // Emitting
                emitter.onNext(true)
                // Completing
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            } finally {
                database.close()
            }
        }
    }

    companion object {
        private const val TAG = "ProductsDao"
    }
}