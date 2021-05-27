package com.sample.data.entity

import com.google.gson.annotations.SerializedName

data class CommentEntity(

	@field:SerializedName("postId")
	val postId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("body")
	val body: String? = null

)