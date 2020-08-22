package nl.testchamber.mailordercoffeeshop.order.beveragesmenu

import androidx.lifecycle.ViewModel
import nl.testchamber.apiservice.data.BeverageMenuItem

class MenuViewModel(val beverage: BeverageMenuItem) : ViewModel() {

    var name = beverage.name
    var volume = "${beverage.volume} ml."
    var image = getCupVolumeDrawableResource(beverage.volume)
}