package com.example.theboredapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.theboredapp.model.ServicesSetterGetter
import com.example.theboredapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<ServicesSetterGetter>()

    fun getServicesApiCall(): MutableLiveData<ServicesSetterGetter> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<ServicesSetterGetter> {
            override fun onFailure(call: Call<ServicesSetterGetter>, t: Throwable) {
                //Handle Failure Case
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ServicesSetterGetter>,
                response: Response<ServicesSetterGetter>
            ) {
                //onSuccess Response
                Log.e("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data!!.activity
                serviceSetterGetter.value = ServicesSetterGetter(msg)
            }
        })

        return serviceSetterGetter
    }
}