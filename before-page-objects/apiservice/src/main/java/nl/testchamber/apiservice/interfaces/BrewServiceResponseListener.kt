package nl.testchamber.apiservice.interfaces

import nl.testchamber.apiservice.data.BeverageMenuItem

interface BrewServiceResponseListener {

    fun onSuccess(response: List<BeverageMenuItem>)

    fun onFailure(message: String)
}