package com.teck.schedulerapp.data.repository

import com.teck.schedulerapp.data.datasource.DataSource
import com.teck.schedulerapp.data.model.Classes
import com.teck.schedulerapp.domain.repository.Repository

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun getData(): Classes = dataSource.getData()
}