package nl.testchamber.apiservice.interfaces

interface ApiService {

    fun getBrews(apiServiceResponseListener: BrewServiceResponseListener)

    fun getMilkTypes(milkTypeServiceResponseListener: MilkTypeServiceResponseListener)
}