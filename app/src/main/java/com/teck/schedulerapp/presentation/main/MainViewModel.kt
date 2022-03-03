package com.teck.schedulerapp.presentation.main

import com.teck.schedulerapp.domain.AppState
import com.teck.schedulerapp.domain.repository.Repository
import com.teck.schedulerapp.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : BaseViewModel() {
    override fun getData() {
        viewModelCoroutineScope.launch {
            liveData.postValue(AppState.Success(repository.getData()))
        }
    }

    override fun handleError(throwable: Throwable) {
        viewModelCoroutineScope.launch {
            liveData.postValue(AppState.Error(throwable))
        }
    }
}