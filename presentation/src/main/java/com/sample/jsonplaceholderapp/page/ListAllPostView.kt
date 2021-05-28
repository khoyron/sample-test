package com.sample.jsonplaceholderapp.page


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sample.data.model.PostModel
import com.sample.data.model.UserModel
import com.sample.data.endpoint.GetDataNetwork
import com.sample.data.callback.CallbackDataPost
import com.sample.jsonplaceholderapp.utility.Constant
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.sample.jsonplaceholderapp.adapter.ListPostAdapter
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerViewListPost
import com.sample.jsonplaceholderapp.callback.PageListPostCallback
import com.sample.jsonplaceholderapp.databinding.ListAllPostViewBinding

class ListAllPostView @JvmOverloads constructor(context: Context,
                                                attrs: AttributeSet? = null,
                                                defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ListAllPostViewBinding.inflate(LayoutInflater.from(context), this, true)

    val adapterListPost by lazy {  ListPostAdapter(context) }
    val dataList = ArrayList<PostModel>()

    lateinit var callbackListPage : PageListPostCallback

    init {
        initRecyclerView()
        getDataList()
    }

    private fun initRecyclerView() {
        binding.rvListPost.apply {
            val lManager = LinearLayoutManager(context)
            lManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = lManager
            itemAnimator = DefaultItemAnimator()
            adapter = adapterListPost
        }

        adapterListPost.setCallbackListener(object : CallbackRecyclerViewListPost {
            override fun callback(view: Int, position: Int, data: UserModel) {
                when (view) {
                    Constant.UPDATE_PROFILE -> {
                        dataList[position].profile = data
                        adapterListPost.notifyItemChanged(position)
                    }
                    Constant.ONCLICK_POST -> {
                        callbackListPage.gotoDetailPost(dataList[position])
                    }
                }
            }
        })
    }

    fun callbackListPostPage(callback: PageListPostCallback){
        callbackListPage = callback
    }

    private fun getDataList(){
        GetDataNetwork().getDataPost(object :CallbackDataPost{
            override fun success(data: ArrayList<PostModel>) {
                dataList.clear()
                dataList.addAll(data)
                adapterListPost.setData(dataList)
            }

            override fun failed(massege: String) {

            }
        })
    }

}