package com.heady.test.modules.dashboard.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.heady.test.R
import com.heady.test.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseFragment() {

    // Layout Manager For RecyclerView
    private var layoutManager: GridLayoutManager? = null

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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    /*
     * This will be called after onCreateView().
     * This is particularly useful when inheriting the onCreateView() implementation
     * but we need to configure the resulting views, such as with a ListFragment and when to set up an adapter
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated($view, $savedInstanceState)")

        layoutManager = GridLayoutManager(context, 2)
        dashboardRecyclerView.setHasFixedSize(true)
        dashboardRecyclerView.layoutManager = layoutManager
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
        showToolBar()
    }

    /*
     * The onResume() method is called after onStart(),
     * But When Inactive Fragment Become Active onResume Is Called Directly.
     */
    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
        // Listener To Manually Handle BackPress On Dashboard Page
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            AlertDialog.Builder(context!!)
                .setCancelable(false)
                .setTitle(EXIT_CONSENT)
                .setMessage("Are you sure you want to exit application?")
                .setPositiveButton("Exit") { dialog, _ ->
                    dialog.dismiss()
                    utils.showLog(TAG, "User Opted To Exit")
                    // To Exit Application
                    finish()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()

                }
                .create()
                .show()
        }
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
        layoutManager = null
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

    companion object {
        const val TAG = "DashboardFragment"
        const val EXIT_CONSENT = "Exit Application"
    }
}