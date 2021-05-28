package com.sample.jsonplaceholderapp.page

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.data.callback.CallbackCommentPost
import com.sample.data.endpoint.GetDataNetwork
import com.sample.data.model.CommentModel
import com.sample.data.model.PostModel
import com.sample.jsonplaceholderapp.adapter.ListCommentAdapter
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerView
import com.sample.jsonplaceholderapp.databinding.DetailPostViewBinding

class DetailPostView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = DetailPostViewBinding.inflate(LayoutInflater.from(context), this, true)

    val commentAdapter by lazy { ListCommentAdapter(context) }
    val dataList = ArrayList<CommentModel>()

    init {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvComment.apply {
            val lManager = LinearLayoutManager(context)
            lManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = lManager
            itemAnimator = DefaultItemAnimator()
            adapter = commentAdapter
        }

        commentAdapter.setCallbackListener(object :CallbackRecyclerView{
            override fun callback(view: Int, position: Int) {

            }
        })
    }

    fun setDataDetailListPost(data:PostModel){
        binding.tvName.text  = data.profile.username
        binding.tvTitle.text = data.title
        binding.tvBody.text  = data.body
        getDataComment(data.idPost)
    }

    private fun getDataComment(idPost: String) {
        GetDataNetwork().getPostComment(idPost,object :CallbackCommentPost{
            override fun success(data: ArrayList<CommentModel>) {
                dataList.clear()
                dataList.addAll(data)
                commentAdapter.setData(data)
            }

            override fun failed(massege: String) {

            }
        })
    }
}