package com.heady.test.data.modules.categories.dao

import com.google.gson.Gson
import com.heady.test.data.modules.categories.models.RealmCategoryModel
import com.tejora.utils.Utils
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.realm.Realm
import javax.inject.Inject

/**
 * Dao For Categories Module To Perform Operations On Local Database
 *
 * Created by Praveen.
 */
class CategoriesDao
@Inject
constructor(
    private val gson: Gson,
    private val utils: Utils
) {

    /*
     * Fetch Categories Count From Local DB.
     */
    fun fetchCategoriesCount(): Observable<Int> {
        return Observable.create { emitter ->
            utils.showLog(
                TAG,
                "Fetching Categories Count From Local Database"
            )
            try {
                // Emitting
                emitter.onNext(Realm.getDefaultInstance().where(RealmCategoryModel::class.java).findAll().count())
                // Completing
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    /*
     * Fetch Categories Count From Local DB.
     */
    fun fetchCategories(): Observable<List<RealmCategoryModel>> {
        return Observable.create { emitter ->
            utils.showLog(
                TAG,
                "Fetching Categories List From Local Database"
            )
            try {
                val database = Realm.getDefaultInstance()
                val queryResult = database.where(RealmCategoryModel::class.java).findAll()
                // Emitting
                emitter.onNext(queryResult.toObservable().toList().blockingGet())
                // Completing
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    /*
     * Save Data To Local DB.
     */
    fun saveCategories(realmCategoryModel: List<RealmCategoryModel>): Observable<Boolean> {
        return Observable.create { emitter ->
            utils.showLog(
                TAG,
                "Saving Categories To Local Database -> ${gson.toJson(realmCategoryModel)}"
            )
            val database: Realm = Realm.getDefaultInstance()
            try {
                database.executeTransaction { realm ->
                    realm.insert(realmCategoryModel)
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
        private const val TAG = "CategoriesDao"
    }
}