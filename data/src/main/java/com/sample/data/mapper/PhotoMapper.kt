package com.sample.data.mapper

import com.sample.data.entity.AlbumEntity
import com.sample.data.entity.CommentEntity
import com.sample.data.entity.PhotoEntity
import com.sample.data.model.AlbumModel
import com.sample.data.model.CommentModel
import com.sample.data.model.PhotoModel
import com.sample.data.network.Serializer

class PhotoMapper {
    fun mapping(response: String): ArrayList<PhotoModel> {
        val mData = Serializer.deserialize(response, Array<PhotoEntity>::class.java)
        val data = ArrayList<PhotoModel>()
        mData.forEach {
            val model = PhotoModel()
            model.url        = it.url
            model.title      = it.title
            model.thumbnail  = it.thumbnailUrl
            data.add(model)
        }
        return data
    }
}