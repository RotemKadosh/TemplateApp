package com.example.myapplication.repository

import com.example.myapplication.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DummyRepositoryImpl @Inject constructor( private val remoteDataSource: RemoteDataSource) : Repository{
    override suspend fun getData(): Flow<DummyDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getDataByFilter(filter: String): Flow<DummyDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshData() {

    }

    override suspend fun removeData(dataToRemove: DummyDataModel) {

    }

    override suspend fun updateData(dataToUpdate: DummyDataModel) {
        TODO("Not yet implemented")
    }

}

data class DummyDataModel(val text: String)