package com.example.theboredapp.retrofit

import com.example.theboredapp.model.ServicesSetterGetter
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("activity")
    fun getServices() : Call<ServicesSetterGetter>

}