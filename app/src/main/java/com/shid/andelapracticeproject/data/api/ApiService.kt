package com.shid.andelapracticeproject.data.api

import com.shid.andelapracticeproject.data.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("skilliq")
    suspend fun getTopSkill(): List<SkillItem>

    @GET("hours")
    suspend fun getTopLearners(): List<TopLearnersItem>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun sendInformation(
        @Field("entry.1824927963")email:String,
        @Field("entry.1877115667")name:String,
        @Field("entry.2006916086")first_name:String,
        @Field("entry.284483984")link:String,

    ):Call<ResponseBody>
}