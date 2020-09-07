package com.shid.andelapracticeproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.repository.MainRepository
import com.shid.andelapracticeproject.ui.main.viewmodel.MainViewModel

class MainViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}