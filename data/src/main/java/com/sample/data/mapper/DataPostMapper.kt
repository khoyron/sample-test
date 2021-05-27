package com.sample.data.mapper

import com.sample.data.model.PostModel
import com.sample.data.entity.PostEntity
import com.sample.data.network.Serializer


class DataPostMapper {
    fun mapping(response: String): ArrayList<PostModel> {
        val data   = ArrayList<PostModel>()
        val dataEntity = Serializer.deserialize(response, Array<PostEntity>::class.java)

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