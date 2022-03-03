package com.teck.schedulerapp.data.datasource

import com.teck.schedulerapp.data.model.Classes

interface DataSource {
    fun getData(): Classes
}