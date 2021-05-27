package com.sample.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UrlEndpoind {

//  @FormUrlEncoded
    @GET(MyURL.URL)
    fun getDataPost(): Call<ResponseBody>

    @GET(MyURL.URL_DETAIL_POST)
    fun getDataDetailPost(@Path("id")id:String, @Path("comments")comment:String): Call<ResponseBody>
}
