package com.sample.data.usecase

import com.sample.data.callback.*

interface DataRepository {
    fun getDataPost(callback: CallbackDataPost)
    fun getDetailPost(id:String,callback: CallbackDetailPost)
    fun getPostComment(id:String,callback: CallbackCommentPost)
    fun getUser(userId:String,callback: CallbackUser)
    fun getAlbums(userId:String,callback: CallbackAlbums)
    fun getPhoto(userId:String,callback: CallbackPhoto)
}