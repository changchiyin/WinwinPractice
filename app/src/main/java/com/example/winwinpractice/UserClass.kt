package com.example.winwinpractice

import com.google.gson.annotations.SerializedName

data class UserClass (
    @SerializedName("nickName") val nickName: String,
    @SerializedName("imageUrl") val imageUrl: String
)