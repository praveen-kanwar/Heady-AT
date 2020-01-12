package com.heady.test.data.modules.subcategories.dao

import com.google.gson.Gson
import com.heady.test.data.modules.subcategories.models.RealmSubCategoryModel
import com.tejora.utils.Utils
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

/**
 * Dao For SubCategories Module To Perform Operations On Local Database
 *
 * Created by Praveen.
 */
class SubCategoriesDao
@Inject
constructor(
    private val gson: Gson,
    private val utils: Utils
) {

    /*
     * Save Data To Local DB.
     */
    fun saveSubCategories(realmSubCategoryModel: List<RealmSubCategoryModel>): Observable<Boolean> {
        return Observable.create { emitter ->
            utils.showLog(
                TAG,
                "Saving SubCategories To Local Database -> ${gson.toJson(realmSubCategoryModel)}"
            )
            val database: Realm = Realm.getDefaultInstance()
            try {
                database.executeTransaction { realm ->
                    realm.insert(realmSubCategoryModel)
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
        private const val TAG = "SubCategoriesDao"
    }
}