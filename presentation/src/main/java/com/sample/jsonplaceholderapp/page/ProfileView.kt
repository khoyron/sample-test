package com.sample.jsonplaceholderapp.page

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sample.data.model.UserModel
import com.sample.jsonplaceholderapp.databinding.ProfileViewBinding

class ProfileView @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ProfileViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

    fun setDataProfile(data:UserModel){
        binding.tvUserName.text     = data.username
        binding.tvEmail.text        = data.email
        binding.tvAddress.text      = data.address
        binding.tvCompanyName.text  = data.company
    }
}