package com.sample.jsonplaceholderapp.page

import android.view.View
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.sample.jsonplaceholderapp.R

class DetailPostView : LinearLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    var pictureImagePath          = ""

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setOrientation(VERTICAL)
        View.inflate(context, R.layout.detail_post_view, this)

    }
}