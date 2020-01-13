package com.heady.test.modules.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.products.models.ProductTaxModel
import com.heady.test.modules.products.models.ProductVariantModel
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * Product Variant Adapter For RecyclerView
 *
 * Created by Praveen.
 */
class ProductVariantAdapter
@Inject
constructor(
    private val productTaxModel: ProductTaxModel,
    private val utils: Utils
) : RecyclerView.Adapter<ProductVariantViewHolder>() {

    // Category List To Be Displayed
    private val productVariantList = mutableListOf<ProductVariantModel>()

    /*
     * Invoked By Layout Manager To Create Views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVariantViewHolder {
        utils.showLog(TAG, "onCreateViewHolder")
        val mContext = parent.context
        val productVariantView = LayoutInflater
            .from(mContext)
            .inflate(
                R.layout.adapter_product_variant,
                parent,
                false
            )
        return ProductVariantViewHolder(
            productVariantView,
            utils
        )
    }

    /*
     * Item Counts To Be Rendered
     */
    override fun getItemCount() = productVariantList.size

    /*
     * Rendering Values In A View.
     */
    override fun onBindViewHolder(holder: ProductVariantViewHolder, position: Int) {
        utils.showLog(TAG, "onBindViewHolder($position)")
        holder.apply {
            renderValues(productVariantList[position], productTaxModel)
        }
    }

    /*
     * To Set Category List
     */
    fun setProductVariantList(list: List<ProductVariantModel>) {
        utils.showLog(TAG, "setProductVariantList")
        if (productVariantList.size > 0) {
            productVariantList.clear()
            notifyDataSetChanged()
        }
        productVariantList.addAll(list)
        notifyItemRangeInserted(0, productVariantList.size)
    }

    companion object {
        // Tag For Log
        private const val TAG = "ProductVariantAdapter"
    }
}