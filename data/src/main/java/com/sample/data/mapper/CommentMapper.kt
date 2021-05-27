package com.sample.data.mapper

import com.sample.data.entity.CommentEntity
import com.sample.data.model.CommentModel
import com.sample.data.network.Serializer

class CommentMapper {
    fun mapping(response: String): ArrayList<CommentModel> {
        val mData = Serializer.deserialize(response, Array<CommentEntity>::class.java)
        val data = ArrayList<CommentModel>()
        mData.forEach {
            val model = CommentModel()
            model.body       = it.body.toString()
            model.autorName  = it.name.toString()
            data.add(model)
        }
        return data
    }
}