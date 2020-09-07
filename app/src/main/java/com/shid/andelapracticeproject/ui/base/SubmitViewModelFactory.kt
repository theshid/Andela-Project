package com.shid.andelapracticeproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.repository.MainRepository
import com.shid.andelapracticeproject.data.repository.SubmitRepository
import com.shid.andelapracticeproject.ui.main.viewmodel.MainViewModel
import com.shid.andelapracticeproject.ui.main.viewmodel.SubmitViewModel

class SubmitViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmitViewModel::class.java)) {
            return SubmitViewModel(SubmitRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}