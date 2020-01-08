package com.heady.test.common.presenter

import com.heady.test.domain.common.interactor.Interactor
import io.reactivex.disposables.CompositeDisposable

/**
 * Base Presenter As A Contract To Be Implemented By All Presenters
 *
 * Created by Praveen.
 */
abstract class BasePresenter(vararg interactors: Interactor<*, *>) {

    // A Disposable Container For Holding Multiple Disposables For Execution.
    val disposableContainer = CompositeDisposable()

    private var interactorList: MutableList<Interactor<*, *>> = mutableListOf()

    init {
        interactorList.addAll(interactors)
    }

    /*
     * Method that receives response from Data Layer
     */
    abstract fun onNext(responseBean: Any)

    /*
     * Method that notifies response is received from Data Layer
     */
    abstract fun onComplete()

    /*
     * Method that notifies error occurred at Data Layer while responding to request
     */
    abstract fun onError(e: Throwable)

    /*
     * Called when Lifecycle Of (Activity or Fragment) is over.
     */
    fun disposeInteractors() {
        disposableContainer.clear()
        interactorList.forEach { interactor ->
            interactor.dispose()
        }
    }
}