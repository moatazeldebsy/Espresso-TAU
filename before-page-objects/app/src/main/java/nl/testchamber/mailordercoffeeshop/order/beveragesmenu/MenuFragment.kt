package nl.testchamber.mailordercoffeeshop.order.beveragesmenu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_beverage_list.*
import nl.testchamber.apiservice.HttpApiService
import nl.testchamber.apiservice.data.BeverageMenuItem
import nl.testchamber.apiservice.interfaces.ApiService
import nl.testchamber.apiservice.interfaces.BrewServiceResponseListener
import nl.testchamber.mailordercoffeeshop.R
import nl.testchamber.mailordercoffeeshop.order.OrderViewModel
import org.jetbrains.anko.support.v4.runOnUiThread


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MenuFragment.OnListFragmentInteractionListener] interface.
 */
class MenuFragment : androidx.fragment.app.Fragment(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        initDataset()
    }

    private var columnCount = 1
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var recyclerview: RecyclerView
    private var beverageMenuContent = mutableListOf<BeverageMenuItem>()
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = HttpApiService()
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity!!).get(OrderViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_beverage_list, container, false)

        // Set the myBeverageRecyclerViewAdapter
        recyclerview = view.findViewById(R.id.beverage_recycler_view)
        with(recyclerview) {
            layoutManager = when {
                columnCount <= 1 -> androidx.recyclerview.widget.LinearLayoutManager(context)
                else -> androidx.recyclerview.widget.GridLayoutManager(context, columnCount)
            }
            adapter = MyBeverageRecyclerViewAdapter(beverageMenuContent, listener)
        }

        // todo: replace
        swipeContainer = view.findViewById(R.id.swipe)
        swipeContainer.setOnRefreshListener(this)
        swipeContainer.isRefreshing = true
        // replace

        return view
    }

    private fun initDataset() {
        apiService.getBrews(object : BrewServiceResponseListener {
            override fun onSuccess(response: List<BeverageMenuItem>) {
                handleCallSuccess(response)
            }

            override fun onFailure(message: String) {
                if (recyclerview.adapter?.itemCount == 0) {

                    // todo: replace
                    error_view.visibility = View.VISIBLE
                    // replace

                } else {
                    Toast.makeText(activity?.applicationContext, "Loading of menu failed: $message", Toast.LENGTH_LONG)
                            .apply {
                                show()
                            }
                }

                // todo: replace
                swipeContainer.isRefreshing = false
                // replace

            }
        })
    }

    private fun handleCallSuccess(response: List<BeverageMenuItem>) {
        // todo: check if this is necessary 'run on ui thread'

        if (!response.isNullOrEmpty()) {
            runOnUiThread {
                error_view.visibility = View.GONE
                with(recyclerview.adapter as MyBeverageRecyclerViewAdapter) {
                    clear()
                    addAll(response)
                    swipeContainer.isRefreshing = false
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isMenuFragmentActive.set(true)
        initDataset()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(beverageMenuItem: BeverageMenuItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isMenuFragmentActive.set(false)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(): MenuFragment {
            return MenuFragment()
        }

        val FRAGMENT_TAG = "FRAGMENT_TAG:${MenuFragment::class.java.simpleName}"
    }
}
