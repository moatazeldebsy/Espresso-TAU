package nl.testchamber.apiservice.interfaces

import nl.testchamber.apiservice.data.MilkTypeService
import retrofit2.Response

interface MilkTypeServiceResponseListener {

    fun onSuccess(response: MilkTypeService)

    fun onFailure(message: String)
}