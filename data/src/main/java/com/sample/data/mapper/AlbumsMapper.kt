package com.sample.data.mapper

import com.sample.data.entity.AlbumEntity
import com.sample.data.entity.CommentEntity
import com.sample.data.model.AlbumModel
import com.sample.data.model.CommentModel
import com.sample.data.network.Serializer

class AlbumsMapper {
    fun mapping(response: String): ArrayList<AlbumModel> {
        val mData = Serializer.deserialize(response, Array<AlbumEntity>::class.java)
        val data = ArrayList<AlbumModel>()
        mData.forEach {
            val model = AlbumModel()
            model.idAlbum    = it.id.toString()
            model.title      = it.title
            data.add(model)
        }
        return data
    }
}