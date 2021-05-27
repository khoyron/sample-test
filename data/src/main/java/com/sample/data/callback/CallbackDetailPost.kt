package com.sample.data.callback

import com.sample.data.model.PostModel

interface CallbackDetailPost {
    fun success(data: PostModel)
    fun failed(massege:String)
}