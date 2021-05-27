package com.sample.data.entity

import com.google.gson.annotations.SerializedName


data class AlbumEntity (

    @SerializedName("userId") var userId : Int,
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String

)