package nl.testchamber.mailordercoffeeshop.orderoverview

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import nl.testchamber.apiservice.data.BeverageMenuItem
import nl.testchamber.mailordercoffeeshop.Utils
import nl.testchamber.mailordercoffeeshop.order.beveragesmenu.getCupVolumeDrawableResource

class OrderOverviewViewModel(beverageMenuItem: BeverageMenuItem) : ViewModel() {
    var orderName = beverageMenuItem.name
    var volume = beverageMenuItem.volume
    var ingredients = beverageMenuItem.ingredients

    val calculatedVolume = ingredients.sumBy { it.volumeInML }

    val beverageDetailIngredientsListing = ingredients.joinToString("\n", prefix = "Ingredients:\n") { it.ingredientName }

    val volumeText = "${calculatedVolume} ml."

    val beverageDetailVolumeDrawableResource = getCupVolumeDrawableResource(calculatedVolume)

    val customerNameTextBox = ObservableField<String>("")
    fun getCustomerName(): String {
        return customerNameTextBox.get().toString()
    }

    val emailTextBox = ObservableField<String>("")
    fun getEmail(): String {
        return emailTextBox.get().toString()
    }

    val customOrderNameTextBox = ObservableField<String>("")
    fun getCustomOrderName(): String {
        return customOrderNameTextBox.get().toString()
    }

    val isCustomOrder: ObservableBoolean
        get() {
            if (!orderName.equals(CUSTOM_DRINK_NAME)) {
                customOrderNameTextBox.set(orderName)
            }
            return (ObservableBoolean(orderName.equals(CUSTOM_DRINK_NAME)))
        }

    private val CUSTOM_DRINK_NAME = "Your custom drink"

    fun isValidOrder(): Boolean {
        return !getCustomerName().isBlank() and !getCustomOrderName().isBlank() and hasValidEmail()
    }

    fun hasValidEmail(): Boolean {
        return getEmail().isBlank() || Utils.isValidEmail(getEmail())
    }

    var emailInputError = ObservableField<String>()
    var customerNameInputError = ObservableField<String>()
    var customOrderNameInputError = ObservableField<String>()
}