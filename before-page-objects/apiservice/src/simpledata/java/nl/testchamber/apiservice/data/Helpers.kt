package nl.testchamber.apiservice.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import nl.testchamber.apiservice.interfaces.DataProviderListener

/**
 * Created by rutger on 09/02/2018.
 */
object Helpers {

    fun addToExistingMap(map: Map<String, String>, existingMap: MutableMap<String, String>): MutableMap<String, String> {
        for ((key, value) in map) {
            existingMap[key] = value
        }
        return existingMap
    }

    fun <T> parseJsonResponseToList(response: JsonResponse, responseObject: Class<T>): List<T>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, responseObject)
        val jsonAdapter = moshi.adapter<List<T>>(type)
        return jsonAdapter.fromJson(response.body)
    }

    fun <T> parseJsonResponse(response: JsonResponse, responseObject: Class<T>): T? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(responseObject)
        return jsonAdapter.fromJson(response.body)
    }

    fun buildAndPassResponse(body: String, listener: DataProviderListener, code: Int) {
        val response = JsonResponse(code, HashMap(), body)
        if (code == 200) {
            listener.onSuccess(response)
        } else {
            listener.onFailure(response)
        }
    }
}
