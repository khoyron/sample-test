package com.sample.data.mapper

import com.sample.data.entity.PostEntity
import com.sample.data.model.PostModel
import com.sample.data.network.Serializer

class DetailPostMapper {
    fun mapping(response: String): PostModel {
        val mData = Serializer.deserialize(response, PostEntity::class.java)
        val data = PostModel()
        data.idPost  = mData.id.toString()
        data.userId  = mData.userId.toString()
        data.title   = mData.title.toString()
        data.body    = mData.body.toString()
        return data
    }
}