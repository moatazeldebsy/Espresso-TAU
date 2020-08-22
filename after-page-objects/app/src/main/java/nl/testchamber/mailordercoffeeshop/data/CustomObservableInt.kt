package nl.testchamber.mailordercoffeeshop.data

import java.util.*

class CustomObservableInt : Observable() {
    private var fatPercentage = 0

    fun setPercentage(fatPercentage: Int) {
        this.fatPercentage = fatPercentage
        setChanged()
        notifyObservers(fatPercentage)
        clearChanged()
    }

    fun getPercentage(): Int {
        return fatPercentage
    }


}