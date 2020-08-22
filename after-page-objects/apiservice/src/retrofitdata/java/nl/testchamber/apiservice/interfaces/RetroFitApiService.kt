package nl.testchamber.apiservice.interfaces

import nl.testchamber.apiservice.data.BeverageMenuItem
import nl.testchamber.apiservice.data.MilkTypeService
import retrofit2.Call
import retrofit2.http.GET

interface RetroFitApiService {

    @GET("5d88a3f13300002c0ed7da8b")
    fun getMilkTypes(): Call<MilkTypeService>

    @GET("5d8baaad3500006200d47193")
    fun getBrews(): Call<List<BeverageMenuItem>>
}