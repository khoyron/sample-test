package com.sample.data.callback

import com.sample.data.model.UserModel

interface CallbackUser {
    fun success(data: UserModel)
    fun failed(massege:String)
}