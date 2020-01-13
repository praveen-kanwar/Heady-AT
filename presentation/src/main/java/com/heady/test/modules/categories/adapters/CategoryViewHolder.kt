package com.heady.test.modules.categories.adapters

import android.view.View
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.categories.models.CategoryModel
import com.tejora.utils.Utils

/**
 * RecyclerView View Holder For Categories
 *
 * Created by Praveen.
 */
class CategoryViewHolder(
    view: View,
    private val utils: Utils
) : RecyclerView.ViewHolder(view) {

    // Display Text For Category Details
    val categoryName: TextView = view.findViewById(R.id.categoryTextView)

    /*
     * Render All Values In Holder
     */
    fun renderValues(categoryModel: CategoryModel) {
        try {
            renderTextAsync(
                categoryName,
                categoryModel.name
            )
        } catch (exception: Exception) {
            utils.showLog(
                TAG,
                "Exception Occurred While Rendering Category -> ${exception.message}"
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
        private const val TAG = "CategoryViewHolder"
    }
}