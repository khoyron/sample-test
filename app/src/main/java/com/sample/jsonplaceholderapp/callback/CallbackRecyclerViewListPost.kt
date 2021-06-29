package com.sample.jsonplaceholderapp.callback

import com.sample.data.model.UserModel

interface CallbackRecyclerViewListPost {
    fun callback(view: Int,position: Int,data:UserModel)
}