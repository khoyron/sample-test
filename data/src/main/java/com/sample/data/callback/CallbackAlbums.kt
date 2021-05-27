package com.sample.data.callback

import com.sample.data.model.AlbumModel
import com.sample.data.model.UserModel

interface CallbackAlbums {
    fun success(data: ArrayList<AlbumModel>)
    fun failed(massege:String)
}