package com.sample.data.callback

import com.sample.data.model.PhotoModel

interface CallbackPhoto {
    fun success(data: ArrayList<PhotoModel>)
    fun failed(massege:String)
}