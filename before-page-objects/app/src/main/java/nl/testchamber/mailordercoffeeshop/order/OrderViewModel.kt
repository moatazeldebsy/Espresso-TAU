package nl.testchamber.mailordercoffeeshop.order

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import nl.testchamber.apiservice.data.Ingredient
import nl.testchamber.mailordercoffeeshop.data.CustomObservableInt
import java.util.*

class OrderViewModel : ViewModel() {

    val espressoShotCounter = ObservableInt()

    var milkType = ""

    var milkFatPercentage = CustomObservableInt()

    var milkFatPercentageText = ObservableField("Milk fat percentage: 0")

    val isCustomOrderFragmentActive = ObservableBoolean()

    val isMenuFragmentActive = ObservableBoolean()

    val milkFatPercentageObserver: Observer = object : Observer {
        override fun update(o: Observable?, arg: Any?) {
            milkFatPercentageText.set("Milk fat percentage: ${(arg as Int).toInt()}")
        }
    }

    init {
        milkFatPercentage.addObserver(milkFatPercentageObserver)
    }

    var showMilkOptions = ObservableBoolean(false)

    var showCustomFatPercentageControls = ObservableBoolean(false)

    var hotCoffee = true

    var chocolate = false

    var steamedMilk = false

    var foamedMilk = false

    var whippedMilk = false

    var scaldedMilk = false

    fun addShot() {
        espressoShotCounter.set(espressoShotCounter.get() + 1)
    }

    fun detractShot() {
        espressoShotCounter.set(espressoShotCounter.get() - 1)
    }

    fun setMilkFatPercentage(fatPercentage: Int) {
        milkFatPercentage.setPercentage(fatPercentage)

    }

    fun getEspressoShotCounter(): Int {
        return espressoShotCounter.get()
    }


    // todo: cleanup this code and extract responsibilities
    fun getIngredientsList(): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        var espressoTemperature = ""
        if (!hotCoffee) {
            espressoTemperature = "cold "
        }
        if (getEspressoShotCounter() > 1) {
            ingredients.add(Ingredient("${getEspressoShotCounter()} shots of ${espressoTemperature}espresso", (getEspressoShotCounter() * 30)))
        } else {
            ingredients.add(Ingredient("1 shot of ${espressoTemperature}espresso", 30))
        }
        if (chocolate) ingredients.add(Ingredient("Chocolate", 30))
        if (isMilkTypeSelected()) {
            var milkType = milkType
            if (milkType == "Custom %") {
                when (milkFatPercentage.getPercentage()) {
                    in 0..12 -> milkType = "${milkFatPercentage.getPercentage()}% fat milk"
                    in 13..30 -> milkType = "Cottage Cheese"
                    in 31..40 -> milkType = "Cheese"
                }

            }
            val milk = "${getMilkPreparation()} ${milkType}"
            ingredients.add(Ingredient(milk, 30))
        }
        return ingredients
    }

    private fun isMilkTypeSelected() =
            milkType.isNotEmpty() && milkType != "No milk"

    private fun getMilkPreparation(): String {
        var milkPreparation = ""
        if (steamedMilk) milkPreparation = "Steamed"
        if (foamedMilk) milkPreparation = "Foamed"
        if (whippedMilk) milkPreparation = "Whipped"
        if (scaldedMilk) milkPreparation = "Scalded"
        return milkPreparation
    }

}