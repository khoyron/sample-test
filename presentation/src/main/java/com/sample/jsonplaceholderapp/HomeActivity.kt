package com.sample.jsonplaceholderapp

import android.view.View.*
import com.sample.data.model.PostModel
import com.sample.jsonplaceholderapp.utility.Constant
import com.sample.jsonplaceholderapp.databinding.HomeMainBinding
import com.sample.jsonplaceholderapp.callback.PageProfileCallback
import com.sample.jsonplaceholderapp.callback.PageListPostCallback
import com.sample.jsonplaceholderapp.callback.PageDetailPostCallback

class HomeActivity : BaseActivity<HomeMainBinding>(),
    PageDetailPostCallback,PageListPostCallback,PageProfileCallback {
    override fun bindLayout(): HomeMainBinding = HomeMainBinding.inflate(layoutInflater)

    var positionPage = Constant.LIST_PAGE_POSITION

    override fun onMain() {
        binding.pageListPost.callbackListPostPage(this)
    }

    override fun backFromDetailPost() {
        binding.pageListPost.visibility       = VISIBLE
        binding.pageDetailListPost.visibility = GONE
        binding.pageProfile.visibility        = GONE
    }

    override fun backFromListPost() {
        closeApplication(getString(R.string.close_app_warning))
    }

    override fun gotoDetailPost(postModel: PostModel) {
        binding.pageListPost.visibility       = GONE
        binding.pageProfile.visibility        = GONE
        binding.pageDetailListPost.visibility = VISIBLE
        binding.pageDetailListPost.setDataDetailListPost(postModel)
        positionPage = Constant.DETAIL_LIST_PAGE_POSITION
    }

    override fun backFromProfile() {
        binding.pageListPost.visibility       = GONE
        binding.pageDetailListPost.visibility = VISIBLE
        binding.pageProfile.visibility        = GONE
    }

    override fun onBackPressed() {
        when(positionPage){
            Constant.LIST_PAGE_POSITION -> {
                closeApplication(getString(R.string.close_app_warning))
            }
            Constant.DETAIL_LIST_PAGE_POSITION -> {
                backFromDetailPost()
            }
            Constant.PROFILE_PAGE_POSITION -> {
                backFromProfile()
            }
        }
    }
}
