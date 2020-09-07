package com.shid.andelapracticeproject.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shid.andelapracticeproject.data.repository.MainRepository
import com.shid.andelapracticeproject.data.repository.SubmitRepository
import com.shid.andelapracticeproject.utils.Resource
import kotlinx.coroutines.Dispatchers

class SubmitViewModel(private val submitRepository: SubmitRepository) : ViewModel() {



    fun sendInformation(
        email: String, name: String, first_name: String, link: String
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = submitRepository.sendInformationRetrofit(
                email, name, first_name, link
            )))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}