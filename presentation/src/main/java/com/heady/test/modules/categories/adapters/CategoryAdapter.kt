package com.heady.test.modules.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.categories.models.CategoryModel
import com.tejora.utils.TejoraBus
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * Adapter To Display Categories In RecyclerView
 *
 * Created by Praveen.
 */
class CategoryAdapter
@Inject
constructor(
    private val utils: Utils
) : RecyclerView.Adapter<CategoryViewHolder>() {

    // Category List To Be Displayed
    private val categoryList = mutableListOf<CategoryModel>()

    /*
     * Invoked By Layout Manager To Create Views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        utils.showLog(TAG, "onCreateViewHolder")
        val mContext = parent.context
        val accountView = LayoutInflater
            .from(mContext)
            .inflate(
                R.layout.adapter_category,
                parent,
                false
            )
        val categoryViewHolder = CategoryViewHolder(
            accountView,
            utils
        )
        setItemOnClickListener(categoryViewHolder)
        return categoryViewHolder
    }

    /*
     * Item Counts To Be Rendered
     */
    override fun getItemCount() = categoryList.size

    /*
     * Rendering Values In A View.
     */
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        utils.showLog(TAG, "onBindViewHolder($position)")
        holder.apply {
            renderValues(categoryList[position])
        }
    }

    /*
     * To Set Category List
     */
    fun setCategoryList(receivedCategoryList: List<CategoryModel>) {
        utils.showLog(TAG, "setCategoryList")
        if (categoryList.size > 0) {
            categoryList.clear()
            notifyDataSetChanged()
        }
        categoryList.addAll(receivedCategoryList)
        notifyItemRangeInserted(0, categoryList.size)
    }

    /*
     * Set Click Listener On Category Text Of ViewHolder
     */
    private fun setItemOnClickListener(categoryViewHolder: CategoryViewHolder) {
        categoryViewHolder.categoryName.setOnClickListener {
            TejoraBus.publish(categoryList[categoryViewHolder.adapterPosition])
        }
    }

    companion object {
        // Tag For Log
        private const val TAG = "CategoryAdapter"
    }
}