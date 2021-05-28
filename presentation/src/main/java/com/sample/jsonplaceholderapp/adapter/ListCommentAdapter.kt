package com.sample.jsonplaceholderapp.adapter

import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import com.sample.data.model.CommentModel
import androidx.recyclerview.widget.RecyclerView
import com.sample.jsonplaceholderapp.callback.CallbackRecyclerView
import com.sample.jsonplaceholderapp.databinding.ItemCommentAdapterBinding

class ListCommentAdapter(val context :Context) : RecyclerView.Adapter<ListCommentAdapter.CommentViewHolder>() {

    lateinit var callback: CallbackRecyclerView
    var items: MutableList<CommentModel> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentAdapterBinding.inflate(LayoutInflater.from(context), parent, false)

        return CommentViewHolder(binding)
    }

    fun setCallbackListener(onclickListenerRecyclerView: CallbackRecyclerView){
        this.callback = onclickListenerRecyclerView
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        with(holder) {
            with(items[position]){
                binding.tvAutorName.text = autorName
                binding.tvBody.text      = body
            }
        }

    }

    fun setData(data: MutableList<CommentModel>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(val binding: ItemCommentAdapterBinding)
        :RecyclerView.ViewHolder(binding.root)
}