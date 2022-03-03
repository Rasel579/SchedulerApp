package com.teck.schedulerapp.domain.repository

import com.teck.schedulerapp.data.model.Classes

interface Repository {
    fun getData(): Classes
}