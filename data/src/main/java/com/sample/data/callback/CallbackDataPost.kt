package com.sample.data.callback

import com.sample.data.model.PostModel

interface CallbackDataPost {
    fun success(data:ArrayList<PostModel>)
    fun failed(massege:String)
}