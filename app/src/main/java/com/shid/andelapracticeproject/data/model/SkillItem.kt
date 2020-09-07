package com.shid.andelapracticeproject.data.model


import com.google.gson.annotations.SerializedName

data class SkillItem(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
)