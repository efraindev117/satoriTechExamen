package com.xsoftcdmx.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class NetworkResult(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("url")
    val url: String? = ""
)