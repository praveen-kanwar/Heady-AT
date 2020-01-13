package com.heady.test.modules.products.adapters

import android.view.View
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.products.models.ProductModel
import com.tejora.utils.Utils

/**
 * RecyclerView ViewHolder Of Products
 *
 * Created by Praveen.
 */
class ProductViewHolder(
    view: View,
    private val utils: Utils
) : RecyclerView.ViewHolder(view) {

    // Display Text For Category Details
    private val productName: TextView = view.findViewById(R.id.productTextView)
    private val productViewCount: TextView = view.findViewById(R.id.productViewCountTextView)
    private val productOrderCount: TextView = view.findViewById(R.id.productOrderCountTextView)
    private val productShareCount: TextView = view.findViewById(R.id.productShareCountTextView)
    private val productVariants: RecyclerView = view.findViewById(R.id.productVariantsRecyclerView)

    /*
     * Render All Values In Holder
     */
    fun renderValues(productModel: ProductModel) {
        try {
            renderTextAsync(
                productName,
                "Name : ${productModel.name}"
            )
            renderTextAsync(
                productViewCount,
                "Viewed  : ${productModel.viewCount}"
            )
            renderTextAsync(
                productOrderCount,
                "Ordered : ${productModel.orderCount}"
            )
            renderTextAsync(
                productShareCount,
                "Shared  : ${productModel.shares}"
            )
            val productVariantAdapter = ProductVariantAdapter(productModel.tax, utils)
            productVariants.apply {
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(productVariants.context, RecyclerView.HORIZONTAL, false)
                adapter = productVariantAdapter
            }
            productVariantAdapter.setProductVariantList(productModel.variants)
            productVariantAdapter.notifyDataSetChanged()
        } catch (exception: Exception) {
            utils.showLog(
                TAG,
                "Exception Occurred While Rendering Products -> ${exception.message}"
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
        private const val TAG = "ProductViewHolder"
    }
}