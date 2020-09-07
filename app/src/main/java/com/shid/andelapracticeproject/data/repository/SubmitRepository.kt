package com.shid.andelapracticeproject.data.repository

import com.shid.andelapracticeproject.data.api.ApiHelper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitRepository(private val apiHelper: ApiHelper) {

    var call:Call<ResponseBody>?=null

    fun sendInformationRetrofit(email: String, name: String, first_name: String, link: String) {

        call = apiHelper.sendInformation(email, name, first_name, link)
        call!!.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}