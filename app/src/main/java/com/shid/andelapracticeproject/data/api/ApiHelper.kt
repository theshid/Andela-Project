package com.shid.andelapracticeproject.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getSkills() = apiService.getTopSkill()
    suspend fun getTopLearners() = apiService.getTopLearners()
    fun sendInformation(
        email: String,
        name: String,
        first_name: String,
        link: String
    ) = apiService.sendInformation(email,name,first_name,link)
}