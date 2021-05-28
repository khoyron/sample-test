package com.sample.jsonplaceholderapp.page

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sample.jsonplaceholderapp.databinding.DetailPostViewBinding

class ProfileView @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = DetailPostViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }
}