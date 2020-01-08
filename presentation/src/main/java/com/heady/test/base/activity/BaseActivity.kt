package com.heady.test.base.activity

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import com.heady.test.R
import com.tejora.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject

/**
 * Base Activity
 *
 * Created by Praveen.
 */
class BaseActivity : DaggerAppCompatActivity() {

    // A Disposable Container For Holding Multiple Disposables
    private val disposableContainer = CompositeDisposable()

    // Gson For Parsing Data Class To JSON
    @Inject
    lateinit var gson: Gson

    // Navigation Controller To Navigation Across Fragments
    private val navigationController: NavController
        get() = findNavController(R.id.navigationHostFragment)

    // Helper Class For Reusable Functionality Across Multiple Project
    @Inject
    lateinit var utils: Utils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Prevents User From Taking Screenshots Of Application
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        // For Logo Animation From Splash Screen To Login Screen
        constraintLayoutMain.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        // Get Firebase Token For Push Notification
        getFirebaseMessagingToken()
        // Dismiss Error Screen
        dismissErrorMaterialButton.setOnClickListener {
            hideError()
        }
    }

    /*
     * When Activity Is First Time Launched, Called Before OnResume
     */
    override fun onStart() {
        super.onStart()
        utils.showLog(TAG, "onStart()")
    }

    /*
     * When Activity Is Visible
     */
    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume")
    }

    /*
     * When Activity Is In Background
     */
    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause")
        disposableContainer.clear()
    }

    /*
     * When Activity Is About To Be Ended
     */
    override fun onStop() {
        super.onStop()
        utils.showLog(TAG, "onStop()")
    }

    /*
     * Handles Android Back Button Press & Navigates Accordingly
     */
    override fun onSupportNavigateUp(): Boolean {
        utils.showLog(
            TAG,
            "onSupportNavigateUp -> ${navigationController.currentDestination.toString()}"
        )
        return navigationController.navigateUp()
    }

    /*
     * To Observe User Interaction With Application, If Not Interacted In Defined Time Lock Screen
     */
    override fun onUserInteraction() {
        super.onUserInteraction()
        utils.showLog(TAG, "onUserInteraction")
    }

    /*
     * Show Loading Screen On Top Of Activity
     */
    fun showLoading() {
        utils.showLog(TAG, "Showing Loader")
        if (loadingLottieAnimationView.visibility != View.VISIBLE)
            loadingLottieAnimationView.visibility = View.VISIBLE
    }

    /*
     * Hide Loading Screen On Top Of Activity
     */
    fun hideLoading() {
        utils.showLog(TAG, "Hiding Loader")
        if (loadingLottieAnimationView.visibility != View.GONE)
            loadingLottieAnimationView.visibility = View.GONE
    }

    /*
     * Show Error Screen On Top Of Activity
     */
    fun showError() {
        utils.showLog(TAG, "Showing Error")
        if (errorConstraintLayout.visibility != View.VISIBLE)
            errorConstraintLayout.visibility = View.VISIBLE
    }

    /*
     * Hide Error Screen On Top Of Activity
     */
    private fun hideError() {
        utils.showLog(TAG, "Hiding Error")
        if (errorConstraintLayout.visibility != View.GONE)
            errorConstraintLayout.visibility = View.GONE
    }

    /*
     * Make Toolbar Invisible
     */
    fun showToolBar() {
        utils.showLog(TAG, "Showing Toolbar")
        if (appBarLayout.visibility != View.VISIBLE) {
            appBarLayout.visibility = View.VISIBLE
        }
    }

    /*
     * Make Toolbar Invisible
     */
    fun hideToolBar() {
        utils.showLog(TAG, "Hiding Toolbar")
        if (appBarLayout.visibility != View.GONE)
            appBarLayout.visibility = View.GONE
    }

    /*
     * Hide The System UI (i.e System Bar aka Status Bar & Navigation Bar)
     */
    fun hideSystemUI() {
        constraintLayoutMain.fitsSystemWindows = false
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    /*
     * Show The System UI (i.e System Bar aka Status Bar & Navigation Bar)
     */
    fun showSystemUI() {
        constraintLayoutMain.fitsSystemWindows = true
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_VISIBLE)
    }

    /*
     * Exit Application
     */
    fun exitApplication() {
        finish()
    }

    /*
     * Function To Get Firebase Messaging Token
     */
    private fun getFirebaseMessagingToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    utils.showLog(TAG, "Unable To Get Firebase Messaging Token")
                } else {
                    utils.showLog(TAG, "Firebase Messaging Token:- ${task.result?.token}")
                }
            }
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}