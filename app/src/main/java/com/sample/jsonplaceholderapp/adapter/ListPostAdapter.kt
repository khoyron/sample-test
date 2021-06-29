package com.sample.jsonplaceholderapp.adapter

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.sample.data.model.PostModel
import com.sample.data.model.UserModel
import com.sample.data.callback.CallbackUser
import com.sample.data.endpoint.GetDataNetwork
import androidx.recyclerview.widget.RecyclerView
import com.sample.jsonplaceholderapp.utility.Constant
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerViewListPost
import com.sample.jsonplaceholderapp.databinding.ItemListpostAdapterBinding

class ListPostAdapter(val context :Context) : RecyclerView.Adapter<ListPostAdapter.PostViewHolder>() {

    lateinit var callback: CallbackRecyclerViewListPost
    var items: MutableList<PostModel> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemListpostAdapterBinding.inflate(LayoutInflater.from(context), parent, false)

        return PostViewHolder(binding)
    }

    fun setCallbackListener(onclickListenerRecyclerView: CallbackRecyclerViewListPost){
        this.callback = onclickListenerRecyclerView
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder) {
            with(items[position]){
                binding.tvTitle.text        = title
                binding.tvBody.text         = body
                binding.tvCompanyName.text  = profile.company
                binding.tvName.text         = profile.username
                if (profile.username.isEmpty()){
                    getDataProfile(userId,position)
                }

                itemView.setOnClickListener {
                    if (profile.username.isNotEmpty()){
                        callback.callback(Constant.ONCLICK_POST,position,items[position].profile)
                    }
                }
            }

        }

    }

    private fun getDataProfile(userId: String,position: Int) {
        GetDataNetwork().getUser(userId,object :CallbackUser{
            override fun success(data: UserModel) {
                callback.callback(Constant.UPDATE_PROFILE,position,data)
            }

            override fun failed(massege: String) {

            }
        })
    }

    fun setData(data: MutableList<PostModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: ItemListpostAdapterBinding)
        :RecyclerView.ViewHolder(binding.root)
}