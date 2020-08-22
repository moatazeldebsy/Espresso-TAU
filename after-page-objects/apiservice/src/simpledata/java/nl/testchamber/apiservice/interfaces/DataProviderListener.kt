package nl.testchamber.apiservice.interfaces

import nl.testchamber.apiservice.data.JsonResponse

interface DataProviderListener {
    fun onSuccess(response: JsonResponse)
    fun onFailure(response: JsonResponse)
}