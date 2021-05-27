package com.sample.data.mapper

import com.sample.data.entity.UserEntity
import com.sample.data.model.UserModel
import com.sample.data.network.Serializer

class UserMapper {
    fun mapping(response: String): UserModel {
        val mData = Serializer.deserialize(response,UserEntity::class.java)
        val data = UserModel()
        data.address = "${mData.address.street} ,${mData.address.suite} ${mData.address.city} , ${mData.address.zipcode}"
        data.company = mData.company.name
        data.email   = mData.email
        data.username = mData.username
        return data
    }
}