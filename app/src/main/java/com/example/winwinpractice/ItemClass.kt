package com.example.winwinpractice

import com.google.gson.annotations.SerializedName

data class ItemClass(
    @SerializedName("user") val user: UserClass,
    @SerializedName("tags") val tags: List<String>,
)