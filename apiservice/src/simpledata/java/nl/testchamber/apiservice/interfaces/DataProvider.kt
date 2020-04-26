package nl.testchamber.apiservice.interfaces

import nl.testchamber.apiservice.data.ApiRequest

interface DataProvider {

    fun execute(request: ApiRequest, listener: DataProviderListener)
}