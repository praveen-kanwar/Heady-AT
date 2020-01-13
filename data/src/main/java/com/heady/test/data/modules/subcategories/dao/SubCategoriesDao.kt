package com.heady.test.data.modules.subcategories.dao

import com.google.gson.Gson
import com.heady.test.data.modules.subcategories.models.RealmSubCategoryModel
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBean
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanR
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

    /*
     * Fetch SubCategories Count From Local DB.
     */
    fun fetchSubCategoriesCount(subCategoriesIdArray: Array<Int>): Observable<Int> {
        return Observable.create {
            try {
                utils.showLog(
                    TAG,
                    "Retrieving SubCategories Count For ${gson.toJson(subCategoriesIdArray)}"
                )
                it.onNext(
                    Realm.getDefaultInstance().where(RealmSubCategoryModel::class.java)
                        .`in`("id", subCategoriesIdArray)
                        .findAll()
                        .count()
                )
                it.onComplete()
            } catch (error: Exception) {
                utils.showLog(TAG, "Error While Finding User -> ${error.message}")
                it.onError(error)
            }
        }
    }

    /*
     * Fetch SubCategories From Local DB.
     */
    fun fetchSubCategories(subCategoriesIdArray: Array<Int>): Observable<SubCategoryBeanR> {
        return Observable.create {
            try {
                utils.showLog(
                    TAG,
                    "Retrieving SubCategories For ${gson.toJson(subCategoriesIdArray)}"
                )
                val database = Realm.getDefaultInstance()
                val queryResult = database.where(RealmSubCategoryModel::class.java)
                    .`in`("id", subCategoriesIdArray)
                    .findAll()
                val subCategoriesList = Observable
                    .fromIterable(database.copyFromRealm(queryResult))
                    .map { realmSubCategory ->
                        SubCategoryBean(
                            realmSubCategory.id,
                            realmSubCategory.name,
                            realmSubCategory.products
                        )
                    }
                    .toList()
                    .blockingGet()

                utils.showLog(
                    TAG,
                    "Retrieved SubCategories -> ${gson.toJson(subCategoriesList)}"
                )

                it.onNext(SubCategoryBeanR(subCategoriesList))
                it.onComplete()
            } catch (error: Exception) {
                utils.showLog(TAG, "Error While Finding User -> ${error.message}")
                it.onError(error)
            }
        }
    }

    companion object {
        private const val TAG = "SubCategoriesDao"
    }
}