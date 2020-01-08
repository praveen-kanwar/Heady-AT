package com.heady.test.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.google.gson.Gson
import com.heady.test.base.activity.BaseActivity
import com.tejora.utils.Utils
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Base Fragment Class With Common Contract Implemented,
 * To Implemented By All Fragments Of Application.
 *
 * Created by Praveen.
 */
abstract class BaseFragment : DaggerFragment() {

    // Reference Of BaseActivity
    @Inject
    lateinit var baseActivity: BaseActivity

    // A Disposable Container For Holding Multiple Disposables
    val disposableContainer = CompositeDisposable()

    // Gson For Parsing Data Class To JSON
    @Inject
    lateinit var gson: Gson

    // Navigation Controller To Navigation Across Fragments
    val navigationController: NavController
        get() = findNavController()

    // Helper Class For Reusable Functionality Across Multiple Project
    @Inject
    lateinit var utils: Utils

    /*
     * This method will be called first, even before onCreate(),
     * letting us know that your fragment has been attached to an activity.
     * You are passed the Activity that will host your fragment
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        utils.showLog(TAG, "onAttach($context)")
    }

    /*
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils.showLog(TAG, "onAttach($context)")
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    /*
     * The system calls this callback when it’s time for the fragment to draw its UI for the first time.
     * To draw a UI for the fragment,
     * A View component must be returned from this method which is the root of the fragment’s layout.
     * We can return null if the fragment does not provide a UI
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        utils.showLog(TAG, "onCreateView($inflater, $container, $savedInstanceState)")
        return null
    }

    /*
     * This will be called after onCreateView().
     * This is particularly useful when inheriting the onCreateView() implementation
     * but we need to configure the resulting views, such as with a ListFragment and when to set up an adapter
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated($view, $savedInstanceState)")
    }

    /*
     * This will be called after onCreate() and onCreateView(),
     * To indicate that the activity’s onCreate() has completed.
     * If there is something that’s needed to be initialised in the fragment
     * That depends upon the activity’s onCreate() having completed its work then
     * onActivityCreated() can be used for that initialisation work
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        utils.showLog(TAG, "onActivityCreated($savedInstanceState)")
    }

    /*
     * The onStart() method is called once the fragment gets visible
     */
    override fun onStart() {
        super.onStart()
        utils.showLog(TAG, "onStart()")
    }

    /*
     * The onResume() method is called after onStart(), But When Inactive Fragment Become Active onResume Is Called Directly.
     */
    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    /*
     * The system calls this method as the first indication that the user is leaving the fragment.
     * This is usually where you should commit any changes that should be persisted beyond the current user session
     */
    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
    }

    /*
     * Fragment going to be stopped by calling onStop()
     */
    override fun onStop() {
        super.onStop()
        utils.showLog(TAG, "onStop()")
        disposableContainer.clear()
    }

    /*
     * It’s called before onDestroy().
     * This is the counterpart to onCreateView() where we set up the UI.
     * If there are things that are needed to be cleaned up specific to the UI,
     * then that logic can be put up in onDestroyView()
     */
    override fun onDestroyView() {
        utils.showLog(TAG, "onDestroyView()")
        super.onDestroyView()

    }

    /*
     * onDestroy() called to do final clean up of the fragment’s state,
     * but Not guaranteed to be called by the Android platform.
     */
    override fun onDestroy() {
        super.onDestroy()
        utils.showLog(TAG, "onDestroy()")
    }

    /*
     * It’s called after onDestroy(),
     * to notify that the fragment has been disassociated from its hosting activity
     */
    override fun onDetach() {
        super.onDetach()
        utils.showLog(TAG, "onDetach()")
    }

    /*
     * Show Loading Screen On Top Of Activity
     */
    fun showLoading() {
        utils.showLog(TAG, "Showing Loading")
        baseActivity.showLoading()
    }

    /*
     * Hide Loading Screen On Top Of Activity
     */
    fun hideLoading() {
        utils.showLog(TAG, "Hiding Loading")
        baseActivity.hideLoading()
    }

    /*
     * Show Error Screen On Top Of Activity
     */
    fun showError(message: String) {
        utils.showLog(TAG, "Showing Error $message")
        baseActivity.showError()
    }

    /*
     * Make Toolbar Invisible
     */
    fun showToolBar() {
        utils.showLog(TAG, "Showing Toolbar")
        baseActivity.showToolBar()
    }

    /*
     * Make Toolbar Invisible
     */
    fun hideToolBar() {
        utils.showLog(TAG, "Hiding Toolbar")
        baseActivity.hideToolBar()
    }

    /*
     * Hide The System UI (i.e System Bar aka Status Bar & Navigation Bar)
     */
    fun hideSystemUI() {
        utils.showLog(TAG, "Hide The System UI (i.e System Bar aka Status Bar & Navigation Bar)")
        baseActivity.hideSystemUI()
    }

    /*
     * Show The System UI (i.e System Bar aka Status Bar & Navigation Bar)
     */
    fun showSystemUI() {
        utils.showLog(TAG, "Show The System UI (i.e System Bar aka Status Bar & Navigation Bar)")
        baseActivity.showSystemUI()
    }

    /*
     * Exit Application
     */
    fun finish() {
        baseActivity.exitApplication()
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}