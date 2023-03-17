package com.example.myapplication.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getData(): Flow<DummyDataModel>
    suspend fun getDataByFilter(filter: String): Flow<DummyDataModel>
    suspend fun refreshData()
    suspend fun updateData(dataToUpdate: DummyDataModel)
    suspend fun removeData(dataToRemove: DummyDataModel)
}