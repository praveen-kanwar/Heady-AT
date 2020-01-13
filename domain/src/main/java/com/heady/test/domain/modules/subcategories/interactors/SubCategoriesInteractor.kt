package com.heady.test.domain.modules.subcategories.interactors

import com.heady.test.domain.common.interactor.Interactor
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.common.threads.BackgroundThread
import com.heady.test.domain.common.threads.MainThread
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanQ
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanR
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Responsible For Task Execution Of SubCategory Module On Data Layer
 *
 * Created by Praveen.
 */
class SubCategoriesInteractor
@Inject
constructor(
    private val repository: Repository,
    backgroundThread: BackgroundThread,
    mainThread: MainThread
) : Interactor<SubCategoryBeanQ, SubCategoryBeanR>(backgroundThread, mainThread) {

    override fun buildInteractorObservable(requestBean: SubCategoryBeanQ): Observable<SubCategoryBeanR> {
        return repository.fetchSubCategories(requestBean)
    }

    companion object {
        const val TAG = "SubCategoryInteractor"
    }
}