package nl.testchamber.apiservice

import nl.testchamber.apiservice.data.*
import nl.testchamber.apiservice.interfaces.ApiService
import nl.testchamber.apiservice.interfaces.MilkTypeServiceResponseListener
import nl.testchamber.apiservice.interfaces.BrewServiceResponseListener
import nl.testchamber.apiservice.interfaces.RetroFitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HttpApiService : ApiService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    private val apiService = retrofit.create(RetroFitApiService::class.java)

    override fun getBrews(apiServiceResponseListener: BrewServiceResponseListener) {
        apiService.getBrews().enqueue(object : Callback<List<BeverageMenuItem>> {
            override fun onFailure(call: Call<List<BeverageMenuItem>>, t: Throwable) {
                val checkedError: String = if (t.message.isNullOrBlank()) {
                    API_GENERIC_ERROR
                } else {
                    t.message!!
                }
                apiServiceResponseListener.onFailure(checkedError)
            }

            override fun onResponse(call: Call<List<BeverageMenuItem>>, response: Response<List<BeverageMenuItem>>) {
                val checkedResponse: List<BeverageMenuItem> = if (response.body().isNullOrEmpty()) {
                    emptyList()
                } else {
                    response.body()!!
                }
                apiServiceResponseListener.onSuccess(checkedResponse)
            }
        })
    }

    override fun getMilkTypes(milkTypeServiceResponseListener: MilkTypeServiceResponseListener) {
        apiService.getMilkTypes().enqueue(object : Callback<MilkTypeService> {
            override fun onFailure(call: Call<MilkTypeService>, t: Throwable) {
                val checkedError: String = if (t.message.isNullOrBlank()) {
                    API_GENERIC_ERROR
                } else {
                    t.message!!
                }
                milkTypeServiceResponseListener.onFailure(checkedError)
            }

            override fun onResponse(call: Call<MilkTypeService>, response: Response<MilkTypeService>?) {
                val checkedResponse: MilkTypeService = if (response?.body() == null) {
                    MilkTypeService(MilkTypes(emptyList()))
                } else {
                    response.body()!!
                }
                milkTypeServiceResponseListener.onSuccess(checkedResponse)
            }
        })
    }
}
