package com.shid.andelapracticeproject.data.repository

import com.shid.andelapracticeproject.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getSkills() = apiHelper.getSkills()
    suspend fun getTopLearners() = apiHelper.getTopLearners()

}