package com.cascer.loginsampleapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_page")
    val totalPage: Int?,
    @SerializedName("data")
    val data: List<UserResponse>?
)