package com.sample.jsonplaceholderapp.adapter

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.sample.data.model.UserModel
import com.sample.data.model.AlbumModel
import com.sample.data.callback.CallbackUser
import com.sample.data.endpoint.GetDataNetwork
import androidx.recyclerview.widget.RecyclerView
import com.sample.jsonplaceholderapp.utility.Constant
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerViewListPost
import com.sample.jsonplaceholderapp.databinding.ItemAdapterGaleryBinding

class GaleryAdapter(val context :Context) : RecyclerView.Adapter<GaleryAdapter.GaleryViewHolder>() {

    lateinit var callback: CallbackRecyclerViewListPost
    var items: MutableList<AlbumModel> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleryViewHolder {
        val binding = ItemAdapterGaleryBinding.inflate(LayoutInflater.from(context), parent, false)

        return GaleryViewHolder(binding)
    }

    fun setCallbackListener(onclickListenerRecyclerView: CallbackRecyclerViewListPost){
        this.callback = onclickListenerRecyclerView
    }

    override fun onBindViewHolder(holder: GaleryViewHolder, position: Int) {
        with(holder) {
            with(items[position]){
                val phothoAdapter = ListPhotoAdapter(context)
                binding.tvGaleryName.text        = title
                binding.rvPhoto.apply {
                    val lManager = LinearLayoutManager(context)
                    lManager.orientation = LinearLayoutManager.VERTICAL
                    layoutManager = lManager
                    itemAnimator = DefaultItemAnimator()
                    adapter = phothoAdapter
                }
                if (tumbnails.isNotEmpty()){
                    phothoAdapter.setData(tumbnails)
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

    fun setData(data: MutableList<AlbumModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class GaleryViewHolder(val binding: ItemAdapterGaleryBinding) :RecyclerView.ViewHolder(binding.root)
}