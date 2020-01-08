package com.heady.test.domain.common.interactor

import com.heady.test.domain.common.threads.BackgroundThread
import com.heady.test.domain.common.threads.MainThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Responsible For Task Execution On Data Layer
 *
 * Created by Praveen.
 */
abstract class Interactor<RequestBean, ResponseBean>(
    private val backgroundThread: BackgroundThread, // Thread On Which Task Will Be Performed Using Input Type
    private val mainThread: MainThread // Thread On Which Result Will Be Published As Output Type
) {
    // Hold Reference Of The Task To Be Performed On Background Thread.
    private var disposable: Disposable? = null

    // A Disposable Container For Holding Multiple Disposables For Execution.
    private val disposableContainer = CompositeDisposable()

    // Perform The Background Task.
    fun execute(
        onNext: ((responseBean: ResponseBean) -> Unit), // Notify on this method on each update.
        onError: ((error: Throwable) -> Unit), // Notify on this method when error occur during update.
        onComplete: (() -> Unit), // Notify on this method when task is completed.
        requestBean: RequestBean // Data Object required for task as input parameter.
    ) {
        disposeLast() // Dispose Last Disposable If Any.
        // Create New Disposable For Task
        disposable = buildInteractorObservable(requestBean)
            .subscribeOn(backgroundThread.scheduler)
            .observeOn(mainThread.scheduler)
            .subscribe(onNext, onError, onComplete)
        // Add Created Disposable To Container
        addDisposable(disposable!!)
    }

    // To Be Implemented By Module Interactor's For Calling Specific Method On Repository.
    internal abstract fun buildInteractorObservable(requestBean: RequestBean): Observable<ResponseBean>

    // Add Disposable To Container.
    private fun addDisposable(disposable: Disposable) {
        disposable.let { currentDisposable ->
            disposableContainer.add(currentDisposable)
        }
    }

    // Clear Last Disposable From Container.
    private fun disposeLast() {
        disposable?.let { lastDisposable ->
            if (!lastDisposable.isDisposed) {
                lastDisposable.dispose()
            }
        }
    }

    // Dispose All Task From Container.
    fun dispose() {
        disposableContainer.clear()
    }
}