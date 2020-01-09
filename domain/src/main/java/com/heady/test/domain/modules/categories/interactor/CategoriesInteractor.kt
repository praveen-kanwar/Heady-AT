package com.heady.test.domain.modules.categories.interactor

import com.heady.test.domain.common.interactor.Interactor
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.common.threads.BackgroundThread
import com.heady.test.domain.common.threads.MainThread
import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Responsible For Task Execution Of Categories Module On Data Layer
 *
 * Created by Praveen.
 */
class CategoriesInteractor
@Inject
constructor(
    private val repository: Repository,
    backgroundThread: BackgroundThread,
    mainThread: MainThread
) : Interactor<CategoryBeanQ, CategoryBeanR>(backgroundThread, mainThread) {

    override fun buildInteractorObservable(requestBean: CategoryBeanQ): Observable<CategoryBeanR> {
        return repository.fetchData(requestBean)
    }

    companion object {
        const val TAG = "SplashInteractor"
    }
}