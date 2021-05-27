package com.sample.data.endpoint

import android.util.Log
import com.sample.data.di.*
import com.sample.data.di.ApiComponent
import com.sample.data.di.NetworkComponent
import com.sample.data.di.NetworkModule

open class BaseGetData {

    var baseUrl       = "https://jsonplaceholder.typicode.com/"
    val messageFailed = "failed mapping data"

    fun apiDependency() : ApiComponent {
        val mApiComponentWeather = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build()
        return mApiComponentWeather
    }

    fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(baseUrl))
                .build()
    }

    fun setLog(message:String){
        Log.e("Test Data",message)
    }

    fun setLog(tag:String,message:String){
        Log.e(tag,message)
    }

}