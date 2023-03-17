package com.example.myapplication.repository

import com.example.myapplication.network.RemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.myapplication.utils.Result
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface SharedRepository {

    val _mutableDataObservable : MutableStateFlow<Result<List<DummyDataModel>>>
    val observableFlow : StateFlow<Result<List<DummyDataModel>>>

    suspend fun refreshData()
    suspend fun updateData(data : DummyDataModel)

}

class SharedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
    ) : SharedRepository{

    override val _mutableDataObservable: MutableStateFlow<Result<List<DummyDataModel>>> =
        MutableStateFlow(Result.Loading)
    override val observableFlow: StateFlow<Result<List<DummyDataModel>>>
        get() = _mutableDataObservable.asStateFlow()

    override suspend fun refreshData(){
        _mutableDataObservable.emit(Result.Loading)
        try {
            val data = remoteDataSource.getData()
            _mutableDataObservable.emit(Result.Success(data))
        } catch (e: java.lang.Exception){
            _mutableDataObservable.emit(Result.Error(e))
        }
    }

    override suspend fun updateData(data : DummyDataModel) {

    }

}