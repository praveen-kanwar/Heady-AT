package com.heady.test.common.view

/**
 * Contract For All Views
 *
 * Created by Praveen.
 */
interface BaseView {
    /*
     * Show Loading Indicator
     */
    fun showLoading()

    /*
     * Hide Loading Indicator
     */
    fun hideLoading()

    /*
     * Show an error message
     *
     * @param message Error In String
     */
    fun showError(message: String)

}