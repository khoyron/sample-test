package com.sample.jsonplaceholderapp.adapter

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.sample.data.model.UserModel
import com.sample.data.callback.CallbackUser
import com.sample.data.endpoint.GetDataNetwork
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.model.PhotoModel
import com.sample.jsonplaceholderapp.utility.Constant
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerViewListPost
import com.sample.jsonplaceholderapp.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class ListPhotoAdapter(val context :Context) : RecyclerView.Adapter<ListPhotoAdapter.PhotoViewHolder>() {

    lateinit var callback: CallbackRecyclerViewListPost
    var items: MutableList<PhotoModel> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(context), parent, false)

        return PhotoViewHolder(binding)
    }

    fun setCallbackListener(onclickListenerRecyclerView: CallbackRecyclerViewListPost){
        this.callback = onclickListenerRecyclerView
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        with(holder) {
            with(items[position]){
                Picasso.get()
                    .load(thumbnail)
                    .fit()
                    .into(binding.tvImage)
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

    fun setData(data: MutableList<PhotoModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(val binding: ItemPhotoBinding)
        :RecyclerView.ViewHolder(binding.root)
}