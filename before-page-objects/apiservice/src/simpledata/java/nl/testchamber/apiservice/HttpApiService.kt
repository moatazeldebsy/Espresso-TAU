package nl.testchamber.apiservice

import nl.testchamber.apiservice.data.*
import nl.testchamber.apiservice.data.Helpers.parseJsonResponse
import nl.testchamber.apiservice.data.Helpers.parseJsonResponseToList
import nl.testchamber.apiservice.interfaces.ApiService
import nl.testchamber.apiservice.interfaces.BrewServiceResponseListener
import nl.testchamber.apiservice.interfaces.DataProviderListener
import nl.testchamber.apiservice.interfaces.MilkTypeServiceResponseListener
import java.net.URI

class HttpApiService : ApiService {

    override fun getBrews(apiServiceResponseListener: BrewServiceResponseListener) {
        val apiRequest = ApiRequest(uri = URI("${BASE_URL}5d8baaad3500006200d47193"))
            SimpleDataProvider().execute(apiRequest, object : DataProviderListener {
                override fun onSuccess(response: JsonResponse) {
                    val parsedResponse = parseJsonResponseToList(response, BeverageMenuItem::class.java)
                            ?: emptyList()
                    apiServiceResponseListener.onSuccess(parsedResponse)
                }

                override fun onFailure(response: JsonResponse) {
                    apiServiceResponseListener.onFailure(response.body)
                }
            })
    }

    override fun getMilkTypes(milkTypeServiceResponseListener: MilkTypeServiceResponseListener) {
        val apiRequest = ApiRequest(uri = URI("${BASE_URL}5d88a3f13300002c0ed7da8b"))

        SimpleDataProvider().execute(apiRequest, object : DataProviderListener {
            override fun onSuccess(response: JsonResponse) {
                val parsedResponse = parseJsonResponse(response, MilkTypeService::class.java)
                        ?: MilkTypeService(MilkTypes(emptyList()))
                milkTypeServiceResponseListener.onSuccess(parsedResponse)
            }

            override fun onFailure(response: JsonResponse) {
                milkTypeServiceResponseListener.onFailure(response.body)
            }
        })
    }
}
