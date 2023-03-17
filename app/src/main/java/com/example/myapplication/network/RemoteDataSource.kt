package com.example.myapplication.network

import com.example.myapplication.repository.DummyDataModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: ApiService
    ) {
    suspend fun getData(): List<DummyDataModel>{
        return service.getRemoteData()
    }
}