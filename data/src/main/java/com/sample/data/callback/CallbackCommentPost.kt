package com.sample.data.callback

import com.sample.data.model.CommentModel

interface CallbackCommentPost {
    fun success(data: ArrayList<CommentModel>)
    fun failed(massege:String)
}