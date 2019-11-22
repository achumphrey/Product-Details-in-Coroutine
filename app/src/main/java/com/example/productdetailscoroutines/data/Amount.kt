package com.example.productdetailscoroutines.data

import com.google.gson.annotations.SerializedName

data class Amount(
    @SerializedName("rate")
    val rate: String
)