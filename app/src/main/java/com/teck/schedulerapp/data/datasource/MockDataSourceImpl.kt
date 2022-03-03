package com.teck.schedulerapp.data.datasource

import com.teck.schedulerapp.data.model.Classes

class MockDataSourceImpl: DataSource {
    override fun getData(): Classes = getMockData()
}