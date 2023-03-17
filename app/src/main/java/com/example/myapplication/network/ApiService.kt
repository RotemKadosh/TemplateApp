package com.example.myapplication.network


import com.example.myapplication.repository.DummyDataModel
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getRemoteData(): List<DummyDataModel>
}