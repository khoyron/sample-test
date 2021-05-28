package com.sample.jsonplaceholderapp.callback

import com.sample.data.model.UserModel

interface PageProfileCallback {
    fun backFromProfile()
    fun gotoProfilePage(profile: UserModel)
}