package com.heady.test.modules.subcategories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heady.test.R
import com.heady.test.modules.subcategories.models.SubCategoryModel
import com.tejora.utils.TejoraBus
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * SubCategory Adapter To Display It In RecyclerView
 *
 * Created by Praveen.
 */
class SubCategoryAdapter
@Inject
constructor(
    private val utils: Utils
) : RecyclerView.Adapter<SubCategoryViewHolder>() {

    // Category List To Be Displayed
    private val subCategoryList = mutableListOf<SubCategoryModel>()

    /*
     * Invoked By Layout Manager To Create Views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        utils.showLog(TAG, "onCreateViewHolder")
        val mContext = parent.context
        val accountView = LayoutInflater
            .from(mContext)
            .inflate(
                R.layout.adapter_subcategory,
                parent,
                false
            )
        val subCategoryViewHolder = SubCategoryViewHolder(
            accountView,
            utils
        )
        setItemOnClickListener(subCategoryViewHolder)
        return subCategoryViewHolder
    }

    /*
     * Item Counts To Be Rendered
     */
    override fun getItemCount() = subCategoryList.size

    /*
     * Rendering Values In A View.
     */
    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        utils.showLog(TAG, "onBindViewHolder($position)")
        holder.apply {
            renderValues(subCategoryList[position])
        }
    }

    /*
     * To Set Category List
     */
    fun setSubCategoryList(receivedSubCategoryList: List<SubCategoryModel>) {
        utils.showLog(TAG, "setCategoryList")
        if (subCategoryList.size > 0) {
            subCategoryList.clear()
            notifyDataSetChanged()
        }
        subCategoryList.addAll(receivedSubCategoryList)
        notifyItemRangeInserted(0, subCategoryList.size)
    }

    /*
     * Set Click Listener On Category Text Of ViewHolder
     */
    private fun setItemOnClickListener(subCategoryViewHolder: SubCategoryViewHolder) {
        subCategoryViewHolder.subCategoryName.setOnClickListener {
            TejoraBus.publish(subCategoryList[subCategoryViewHolder.adapterPosition])
        }
    }

    companion object {
        // Tag For Log
        private const val TAG = "SubCategoryAdapter"
    }
}