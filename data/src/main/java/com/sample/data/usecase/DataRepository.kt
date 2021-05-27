package com.sample.data.usecase

import com.sample.data.callback.CallbackDataPost
import com.sample.data.callback.CallbackDetailPost

interface DataRepository {
    fun getDataPost(callback: CallbackDataPost)
    fun getDetailPost(callback: CallbackDetailPost)
    fun getPostComment(callback: CallbackDataPost)
}