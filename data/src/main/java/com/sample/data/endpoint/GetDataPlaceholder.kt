package com.sample.data.endpoint

import com.sample.data.callback.CallbackDataPost
import com.sample.data.callback.CallbackDetailPost
import com.sample.data.network.UrlEndpoind
import com.sample.data.usecase.DataRepository
import com.sample.data.mapper.DataPostMapper
import com.sample.data.mapper.DetailPostMapper
import okhttp3.ResponseBody
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class GetDataPlaceholder : BaseGetData(), DataRepository {

    @Inject
    lateinit var api : UrlEndpoind

    init {
        apiDependency().inject(this)
    }

    override fun getDataPost(callback: CallbackDataPost) {
        api.getDataPost().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(DataPostMapper().mapping(response.body()?.string().toString()))
                    }
                    else{
                        callback.failed("something error")
                    }
                }catch (e:Exception){
                    callback.failed(messageFailed)
                }
            }
        })
    }

    override fun getDetailPost(callback: CallbackDetailPost) {
        api.getDataDetailPost("1","").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(DetailPostMapper().mapping(response.body()?.string().toString()))
                    }
                    else{
                        callback.failed("something error")
                    }
                }catch (e:Exception){
                    callback.failed(messageFailed)
                }
            }
        })
    }

    override fun getPostComment(callback: CallbackDataPost) {

    }


}
