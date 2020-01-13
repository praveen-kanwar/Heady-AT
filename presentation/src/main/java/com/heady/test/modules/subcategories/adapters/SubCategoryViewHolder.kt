package com.heady.test.modules.subcategories.adapters

import android.view.View
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.subcategories.models.SubCategoryModel
import com.tejora.utils.Utils

/**
 * RecyclerView ViewHolder Of SubCategory
 *
 * Created by Praveen.
 */
class SubCategoryViewHolder(
    view: View,
    private val utils: Utils
) : RecyclerView.ViewHolder(view) {

    // Display Text For SubCategory Details
    val subCategoryName: TextView = view.findViewById(R.id.subCategoryTextView)

    /*
     * Render All Values In Holder
     */
    fun renderValues(subCategoryModel: SubCategoryModel) {
        try {
            renderTextAsync(
                subCategoryName,
                subCategoryModel.name
            )
        } catch (exception: Exception) {
            utils.showLog(
                TAG,
                "Exception Occurred While Rendering SubCategory -> ${exception.message}"
            )
        }
    }

    private fun renderTextAsync(textView: TextView, text: String) {
        val params: PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(textView)
        val precomputedText: PrecomputedTextCompat =
            PrecomputedTextCompat.create(text, params)
        TextViewCompat.setPrecomputedText(textView, precomputedText)
    }

    companion object {
        // Tag For Log
        private const val TAG = "SubCategoryViewHolder"
    }
}