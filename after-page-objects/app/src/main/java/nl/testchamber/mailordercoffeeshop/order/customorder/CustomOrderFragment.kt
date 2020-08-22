package nl.testchamber.mailordercoffeeshop.order.customorder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import nl.testchamber.apiservice.data.BeverageMenuItem
import nl.testchamber.mailordercoffeeshop.R
import nl.testchamber.mailordercoffeeshop.order.OrderViewModel
import nl.testchamber.mailordercoffeeshop.orderoverview.OrderOverviewActivity

class CustomOrderFragment : androidx.fragment.app.Fragment(), AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            orderViewModel.milkType = parent.getItemAtPosition(position) as String
            if (orderViewModel.milkType != "No milk") {
                orderViewModel.showMilkOptions.set(true)
                if (orderViewModel.milkType == "Custom %") {
                    orderViewModel.showCustomFatPercentageControls.set(true)
                } else {
                    orderViewModel.showCustomFatPercentageControls.set(false)
                }
            } else {
                orderViewModel.showMilkOptions.set(false)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        parent.getItemAtPosition(0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
    }

    companion object {
        fun newInstance(): CustomOrderFragment {
            return CustomOrderFragment()
        }

        val FRAGMENT_TAG = "FRAGMENT_TAG:${CustomOrderFragment::class.java.simpleName}"
    }

    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        orderViewModel = ViewModelProviders.of(activity!!).get(OrderViewModel::class.java)
        val mapBinding: nl.testchamber.mailordercoffeeshop.databinding.FragmentCustomOrderBinding = DataBindingUtil.inflate<nl.testchamber.mailordercoffeeshop.databinding.FragmentCustomOrderBinding>(inflater, R.layout.fragment_custom_order, container, false).apply {
            viewModel = orderViewModel
            handlers = Handlers()
        }
        return mapBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel.isCustomOrderFragmentActive.set(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        orderViewModel.isCustomOrderFragmentActive.set(false)
    }

    private fun isValidBeverage(): Boolean {
        return orderViewModel.getEspressoShotCounter() > 0
    }

    private fun showOrderErrors() {
        if (orderViewModel.getEspressoShotCounter() < 1) Toast.makeText(activity?.applicationContext, "A minimum of 1 Espresso shot is required for each order", Toast.LENGTH_LONG)
                .apply {
                    show()
                }
    }

    private fun reviewOrder(beverageMenuItem: BeverageMenuItem) {
        val intent = Intent(activity, OrderOverviewActivity::class.java)
        intent.putExtra(BeverageMenuItem.PARCEL_NAME, beverageMenuItem)
        startActivity(intent)
    }

    inner class Handlers {

        val reviewOrderListener: View.OnClickListener
            get() {
                return View.OnClickListener {
                    if (isValidBeverage()) {
                        reviewOrder(BeverageMenuItem(getString(R.string.default_order_title), 0, orderViewModel.getIngredientsList(), ""))
                    } else {
                        showOrderErrors()
                    }
                }
            }

        val addEspressoShotListener: View.OnClickListener
            get() {
                return View.OnClickListener {
                    orderViewModel.addShot()

                }
            }

        val detractEspressoShotListener: View.OnClickListener
            get() {
                return View.OnClickListener {
                    if (orderViewModel.getEspressoShotCounter() > 0) {
                        orderViewModel.detractShot()
                    } else {
                        Toast.makeText(activity?.applicationContext, "You can't order less than zero espresso shots", Toast.LENGTH_SHORT)
                                .apply {
                                    show()
                                }
                    }
                }
            }

        val seekBarOnChangeListener: SeekBar.OnSeekBarChangeListener
            get() {
                return object : SeekBar.OnSeekBarChangeListener {
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        orderViewModel.setMilkFatPercentage(progress)
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }
                }
            }

        val spinnerListener: AdapterView.OnItemSelectedListener
            get() {
                return this@CustomOrderFragment
            }

        val spinnerAdapter: SpinnerAdapter
            get() {
                val spinnerArray = arrayOf("No milk", "Soy", "Low fat", "Half & half", "Cream", "Custom %")
                val spinnerArrayAdapter: ArrayAdapter<String> = object : ArrayAdapter<String>(activity?.applicationContext, R.layout.spinner_item, spinnerArray) {
                    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val view = super.getView(position, convertView, parent)
                        view.setPadding(0, view.paddingTop, 0, view.paddingBottom)
                        return view
                    }
                }
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item)
                return spinnerArrayAdapter
            }
    }
}