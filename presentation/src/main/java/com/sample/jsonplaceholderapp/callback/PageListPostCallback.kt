package com.sample.jsonplaceholderapp.callback

import com.sample.data.model.PostModel

interface PageListPostCallback {
    fun backFromListPost()
    fun gotoDetailPost(postModel: PostModel)
}