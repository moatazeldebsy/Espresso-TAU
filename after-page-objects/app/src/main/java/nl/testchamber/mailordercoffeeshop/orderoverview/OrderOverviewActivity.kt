package nl.testchamber.mailordercoffeeshop.orderoverview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import nl.testchamber.apiservice.data.BeverageMenuItem
import nl.testchamber.mailordercoffeeshop.R
import nl.testchamber.mailordercoffeeshop.data.Order
import nl.testchamber.mailordercoffeeshop.databinding.ActivityOrderOverviewBinding

class OrderOverviewActivity : AppCompatActivity() {

    val orderOverviewViewModel: OrderOverviewViewModel by lazy {
        ViewModelProviders.of(this, OrderOverviewViewModelFactory(intent.getParcelableExtra<BeverageMenuItem>(BeverageMenuItem.PARCEL_NAME))).get(OrderOverviewViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_overview)
        val binding: ActivityOrderOverviewBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_order_overview)

        binding.viewModel = orderOverviewViewModel
        binding.listeners = Listeners()
    }

    private fun submitOrder() {
        if (orderOverviewViewModel.isValidOrder()) {
            nl.testchamber.mailordercoffeeshop.Utils.hideKeyboard(this)
            val order = Order(orderOverviewViewModel.getCustomerName(), orderOverviewViewModel.getEmail(), orderOverviewViewModel.getCustomOrderName(), orderOverviewViewModel.ingredients)
            composeEmail(order)
        } else {
            showOrderErrors()
        }
    }

    private fun showOrderErrors() {
        if (!orderOverviewViewModel.hasValidEmail()) {
            orderOverviewViewModel.emailInputError?.set(getString(R.string.email_invalid_error))
        }
        if (orderOverviewViewModel.getCustomerName().isBlank()) {
            orderOverviewViewModel.customerNameInputError?.set(getString(R.string.customer_name_error))
        }
        if (orderOverviewViewModel.getCustomOrderName().isBlank()) {
            orderOverviewViewModel.customOrderNameInputError?.set(getString(R.string.order_name_error))
        }
    }

    private fun composeEmail(order: Order) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            var addresses = mutableListOf("coffeeshop@valori.nl")
            if (order.customerEmail.isNotEmpty()) addresses.add(order.customerEmail)
            putExtra(Intent.EXTRA_EMAIL, addresses.toTypedArray())
            putExtra(Intent.EXTRA_SUBJECT, "Order: ${order.customerName} - ${order.orderName}")
            putExtra(Intent.EXTRA_TEXT, order.ingredients.joinToString("\n", prefix = "Ingredients:\n") { it.ingredientName })
        }
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }

    inner class Listeners {
        val submitOrderListener: View.OnClickListener
            get() {
                return View.OnClickListener {
                    submitOrder()
                }
            }

        val customerNameOnFocusChangeListener: View.OnFocusChangeListener
            get() {
                return View.OnFocusChangeListener { _, _ -> if (orderOverviewViewModel.getCustomerName().isNotBlank()) orderOverviewViewModel.customerNameInputError?.set("") }
            }

        val emailOnFocusChangeListener: View.OnFocusChangeListener
            get() {
                return View.OnFocusChangeListener { _, _ -> if (orderOverviewViewModel.hasValidEmail()) orderOverviewViewModel.emailInputError?.set("") }
            }

        val customOrderNameOnFocusChangeListener: View.OnFocusChangeListener
            get() {
                return View.OnFocusChangeListener { _, _ -> if (orderOverviewViewModel.getCustomOrderName().isNotBlank()) orderOverviewViewModel.customOrderNameInputError?.set("") }
            }
    }
}
