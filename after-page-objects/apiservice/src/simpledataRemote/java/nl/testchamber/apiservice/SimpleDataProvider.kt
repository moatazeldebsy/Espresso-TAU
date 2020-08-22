package nl.testchamber.apiservice

import nl.testchamber.apiservice.data.ApiRequest
import nl.testchamber.apiservice.data.Helpers.buildAndPassResponse
import nl.testchamber.apiservice.interfaces.DataProvider
import nl.testchamber.apiservice.interfaces.DataProviderListener
import okhttp3.*
import java.io.IOException


class SimpleDataProvider : DataProvider {

    override fun execute(request: ApiRequest, listener: DataProviderListener) {
        val okRequest = Request.Builder()
                .url(request.uri.toURL())
                .build()

        OkHttpClient().newCall(okRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                buildAndPassResponse("CANNOT PARSE FILE", listener, 1)
            }

            override fun onResponse(call: Call, response: Response) {
                buildAndPassResponse(response.body()!!.string(), listener, 200)
            }
        })
    }

    companion object {
        private val LOGTAG = SimpleDataProvider::class.java.simpleName
    }
}
