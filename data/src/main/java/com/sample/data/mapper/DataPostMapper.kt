package com.sample.data.mapper

import com.khoiron.data.entity.PostEntity
import com.sample.data.model.PostModel
import com.google.gson.Gson



class DataPostMapper {
    fun mapping(response: String): ArrayList<PostModel> {
        val data   = ArrayList<PostModel>()
        val gson = Gson()
        val dataEntity = gson.fromJson(response, Array<PostEntity>::class.java)

        dataEntity.forEach {
            val mData = PostModel()
            mData.idPost   = it.id.toString()
            mData.userId   = it.userId.toString()
            mData.title    = it.title.toString()
            mData.body     = it.body.toString()

            data.add(mData)
        }

        return data
    }
}