package com.heady.test.modules.products.adapters

import android.view.View
import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.products.models.ProductTaxModel
import com.heady.test.modules.products.models.ProductVariantModel
import com.tejora.utils.Utils

/**
 * RecyclerView ViewHolder Of ProductVariant
 *
 * Created by Praveen.
 */
class ProductVariantViewHolder(
    view: View,
    private val utils: Utils
) : RecyclerView.ViewHolder(view) {

    // Display Text For Category Details
    private val productColor: TextView = view.findViewById(R.id.productColorTextView)
    private val productSize: TextView = view.findViewById(R.id.productSizeTextView)
    private val productPrice: TextView = view.findViewById(R.id.productPriceTextView)
    private val productTax: TextView = view.findViewById(R.id.productTaxTextView)

    /*
     * Render All Values In Holder
     */
    fun renderValues(productVariantModel: ProductVariantModel, productTaxModel: ProductTaxModel) {
        try {
            renderTextAsync(
                productColor,
                "Color : ${productVariantModel.color}"
            )
            renderTextAsync(
                productSize,
                "Size  : ${productVariantModel.size}"
            )
            renderTextAsync(
                productPrice,
                "Price : ${productVariantModel.price}"
            )
            renderTextAsync(
                productTax,
                "Tax   : ${productTaxModel.name} - ${productTaxModel.value}"
            )
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