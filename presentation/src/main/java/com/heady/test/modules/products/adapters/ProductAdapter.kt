package com.heady.test.modules.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.products.models.ProductModel
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * Products Adapter For RecyclerView
 *
 * Created by Praveen.
 */
class ProductAdapter
@Inject
constructor(
    private val utils: Utils
) : RecyclerView.Adapter<ProductViewHolder>() {

    // Category List To Be Displayed
    private val productList = mutableListOf<ProductModel>()

    /*
     * Invoked By Layout Manager To Create Views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        utils.showLog(TAG, "onCreateViewHolder")
        val mContext = parent.context
        val accountView = LayoutInflater
            .from(mContext)
            .inflate(
                R.layout.adapter_product,
                parent,
                false
            )
        return ProductViewHolder(
            accountView,
            utils
        )
    }

    /*
     * Item Counts To Be Rendered
     */
    override fun getItemCount() = productList.size

    /*
     * Rendering Values In A View.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        utils.showLog(TAG, "onBindViewHolder($position)")
        holder.apply {
            renderValues(productList[position])
        }
    }

    /*
     * To Set Category List
     */
    fun setProductList(receivedCategoryList: List<ProductModel>) {
        utils.showLog(TAG, "setProductList")
        if (productList.size > 0) {
            productList.clear()
            notifyDataSetChanged()
        }
        productList.addAll(receivedCategoryList)
        notifyItemRangeInserted(0, productList.size)
    }

    /*
     * To Sort Products
     */
    fun sortAccountList(sortOperation: Int) {
        when (sortOperation) {
            SORT_BY_MOST_VIEWED -> {
                productList.sortWith(compareByDescending(ProductModel::viewCount))
            }
            SORT_BY_MOST_ORDERED -> {
                productList.sortWith(compareByDescending(ProductModel::orderCount))
            }
            SORT_BY_MOST_SHARED -> {
                productList.sortWith(compareByDescending(ProductModel::shares))
            }
        }
        notifyItemRangeChanged(0, productList.size)
    }

    companion object {
        // Tag For Log
        private const val TAG = "ProductAdapter"
        private const val SORT_BY_MOST_VIEWED = 0
        private const val SORT_BY_MOST_ORDERED = 1
        private const val SORT_BY_MOST_SHARED = 2
    }
}