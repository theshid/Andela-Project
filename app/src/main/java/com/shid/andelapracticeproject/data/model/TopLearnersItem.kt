package com.shid.andelapracticeproject.data.model


import com.google.gson.annotations.SerializedName

data class TopLearnersItem(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String
)