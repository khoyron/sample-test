package com.sample.data.endpoint

import com.sample.data.callback.*
import com.sample.data.mapper.*
import com.sample.data.network.UrlEndpoind
import com.sample.data.usecase.DataRepository
import okhttp3.ResponseBody
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class GetDataNetwork : BaseGetData(), DataRepository {

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

    override fun getDetailPost(id:String,callback: CallbackDetailPost) {
        api.getDataDetailPost("1").enqueue(object : Callback<ResponseBody> {
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

    override fun getPostComment(id:String,callback: CallbackCommentPost) {
        api.getCommentPost(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(CommentMapper().mapping(response.body()?.string().toString()))
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

    override fun getUser(id:String,callback: CallbackUser) {
        api.getUser(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(UserMapper().mapping(response.body()?.string().toString()))
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

    override fun getAlbums(userId: String, callback: CallbackAlbums) {
        api.getAlbums(userId).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(AlbumsMapper().mapping(response.body()?.string().toString()))
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

    override fun getPhoto(albumId: String, callback: CallbackPhoto) {
        api.getPhoto(albumId).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.failed(t.message!!)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    if (response.code()==200){
                        callback.success(PhotoMapper().mapping(response.body()?.string().toString()))
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


}
