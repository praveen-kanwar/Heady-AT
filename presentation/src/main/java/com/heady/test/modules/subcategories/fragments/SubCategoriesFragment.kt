package com.heady.test.modules.subcategories.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.heady.test.R
import com.heady.test.base.fragment.BaseFragment
import com.heady.test.modules.subcategories.adapters.SubCategoryAdapter
import com.heady.test.modules.subcategories.models.SubCategoryModel
import com.heady.test.modules.subcategories.models.SubCategoryModelQ
import com.heady.test.modules.subcategories.models.SubCategoryModelR
import com.heady.test.modules.subcategories.presenters.SubCategoriesPresenter
import com.heady.test.modules.subcategories.views.SubCategoriesFragmentView
import com.tejora.utils.TejoraBus
import kotlinx.android.synthetic.main.fragment_subcategories.*
import javax.inject.Inject

/**
 * SubCategories Fragment For Displaying SubCategories In UI
 *
 * Created by Praveen.
 */
class SubCategoriesFragment : BaseFragment(), SubCategoriesFragmentView {

    private val subCategoriesIdArrayList = arrayListOf<Int>()

    @Inject
    lateinit var subCategoriesPresenter: SubCategoriesPresenter

    // Layout Manager For RecyclerView
    private var subCategoryListLayoutManager: LinearLayoutManager? = null

    // Adapter For RecyclerView
    @Inject
    lateinit var subCategoryAdapter: SubCategoryAdapter

    // Selected Pre Account List To Add In Today's Appointment
    private var selectedSubCategory: SubCategoryModel? = null

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
        subCategoriesIdArrayList.clear()
        subCategoriesIdArrayList.addAll(arguments?.getIntArray("Argument")!!.toList())
        utils.showLog(
            TAG,
            "Received SubCategories List -> ${gson.toJson(subCategoriesIdArrayList)}"
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
        return inflater.inflate(R.layout.fragment_subcategories, container, false)
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
        subCategoryListLayoutManager = LinearLayoutManager(context)
        subCategoriesRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            layoutManager = subCategoryListLayoutManager
            adapter = subCategoryAdapter
        }
        subCategoriesPresenter.fetchCategories(SubCategoryModelQ(subCategoriesIdArrayList))
    }

    /*
     * The onStart() method is called once the fragment gets visible
     */
    override fun onStart() {
        super.onStart()
        utils.showLog(TAG, "onStart()")
        disposableContainer.add(TejoraBus.listen(SubCategoryModel::class.java).subscribe { subCategoryModel ->
            utils.showLog(TAG, "Selected Category: ${gson.toJson(subCategoryModel)}")
            selectedSubCategory = subCategoryModel
            val bundle = Bundle()
            bundle.putIntArray("Argument", subCategoryModel.products.toIntArray())
            navigationController
                .navigate(
                    R.id.navigateToProductFragment,
                    bundle
                )
        })
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

    override fun responseReceived(subCategoryModelR: SubCategoryModelR) {
        utils.showLog(TAG, "Received SubCategories -> ${gson.toJson(subCategoryModelR)}")
        subCategoryAdapter.setSubCategoryList(subCategoryModelR.subCategoriesList)
    }

    companion object {
        private const val TAG = "SubCategoriesFragment"
    }
}