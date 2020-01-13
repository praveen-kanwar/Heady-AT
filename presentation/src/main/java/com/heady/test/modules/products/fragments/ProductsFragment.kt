package com.heady.test.modules.products.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.heady.test.R
import com.heady.test.base.fragment.BaseFragment
import com.heady.test.modules.products.adapters.ProductAdapter
import com.heady.test.modules.products.models.ProductModelQ
import com.heady.test.modules.products.models.ProductModelR
import com.heady.test.modules.products.presenters.ProductsPresenter
import com.heady.test.modules.products.views.ProductsFragmentView
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject

/**
 * Fragment Responsible For Product Display
 *
 * Created by Praveen.
 */
class ProductsFragment : BaseFragment(), ProductsFragmentView, AdapterView.OnItemSelectedListener {

    private val productIdArrayList = arrayListOf<Int>()

    @Inject
    lateinit var productsPresenter: ProductsPresenter

    // Layout Manager For RecyclerView
    private var productListLayoutManager: LinearLayoutManager? = null

    // Adapter For RecyclerView
    @Inject
    lateinit var productAdapter: ProductAdapter

    /*
     * This method will be called first, even before onCreate(),
     * letting us know that your fragment has been attached to an activity.
     * You are passed the Activity that will host your fragment
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        utils.showLog(TAG, "onAttach($context)")
    }

    /*
     * Called to do initial creation of a fragment.  This is called after #onAttach and before #onCreateView
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        utils.showLog(TAG, "onAttach($context)")
        productIdArrayList.clear()
        productIdArrayList.addAll(arguments?.getIntArray("Argument")!!.toList())
        utils.showLog(
            TAG,
            "Received Products List -> ${gson.toJson(productIdArrayList)}"
        )
    }

    /*
     * The system calls this callback when it’s time for the fragment to draw its UI for the first time.
     * To draw a UI for the fragment,
     * A View component must be returned from this method which is the root of the fragment’s layout.
     * We can return null if the fragment does not provide a UI
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        utils.showLog(TAG, "onCreateView($inflater, $container, $savedInstanceState)")
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    /*
     * This will be called after onCreateView().
     * This is particularly useful when inheriting the onCreateView() implementation
     * but we need to configure the resulting views, such as with a ListFragment and when to set up an adapter
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated($view, $savedInstanceState)")
    }

    /*
     * This will be called after onCreate() and onCreateView(),
     * To indicate that the activity’s onCreate() has completed.
     * If there is something that’s needed to be initialised in the fragment
     * That depends upon the activity’s onCreate() having completed its work then
     * onActivityCreated() can be used for that initialisation work
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        utils.showLog(TAG, "onActivityCreated($savedInstanceState)")
        productListLayoutManager = LinearLayoutManager(context)
        productsRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            layoutManager = productListLayoutManager
            adapter = productAdapter
        }
        sortingFloatingActionButton.setOnClickListener {
            sortingMenuSpinner.performClick()
        }
        sortingMenuSpinner.onItemSelectedListener = this
        productsPresenter.fetchCategories(ProductModelQ(productIdArrayList))
    }

    /*
     * The onStart() method is called once the fragment gets visible
     */
    override fun onStart() {
        super.onStart()
        utils.showLog(TAG, "onStart()")
    }

    /*
     * The onResume() method is called after onStart(),
     * But When Inactive Fragment Become Active onResume Is Called Directly.
     */
    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    /*
     * The system calls this method as the first indication that the user is leaving the fragment.
     * This is usually where you should commit any changes that should be persisted beyond the current user session
     */
    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
    }

    /*
     * Fragment going to be stopped by calling onStop()
     */
    override fun onStop() {
        super.onStop()
        utils.showLog(TAG, "onStop()")
    }

    /*
     * It’s called before onDestroy().
     * This is the counterpart to onCreateView() where we set up the UI.
     * If there are things that are needed to be cleaned up specific to the UI,
     * then that logic can be put up in onDestroyView()
     */
    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView()")
    }

    /*
     * onDestroy() called to do final clean up of the fragment’s state,
     * but Not guaranteed to be called by the Android platform.
     */
    override fun onDestroy() {
        super.onDestroy()
        utils.showLog(TAG, "onDestroy()")
    }

    /*
     * It’s called after onDestroy(),
     * to notify that the fragment has been disassociated from its hosting activity
     */
    override fun onDetach() {
        super.onDetach()
        utils.showLog(TAG, "onDetach()")
    }

    override fun responseReceived(productsModelR: ProductModelR) {
        utils.showLog(TAG, "Received Products -> ${gson.toJson(productsModelR)}")
        productAdapter.setProductList(productsModelR.productList)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        utils.showLog(TAG, "onNothingSelected()")
    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        utils.showLog(TAG, "onItemSelected($parent, $view, $position, $id)")
        when (parent.id) {
            R.id.sortingMenuSpinner -> {
                utils.showLog(TAG, "Selected Sorting ${parent.getItemAtPosition(position)}")
                utils.showToast("Sorting by ${parent.getItemAtPosition(position)}")
                productAdapter.sortAccountList(position)
            }
        }
    }

    companion object {
        private const val TAG = "ProductsFragment"
    }
}