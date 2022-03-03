package com.teck.schedulerapp.domain

sealed class AppState{
    data class Success(val data: Any) : AppState()
    object Loading: AppState()
    data class Error(val error: Throwable) : AppState()
}
