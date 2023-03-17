package com.example.myapplication.ui.listfragment

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.DummyDataModel
import com.example.myapplication.repository.SharedRepository
import com.example.myapplication.ui.listfragment.model.UiData
import com.example.myapplication.ui.listfragment.model.UiState
import com.example.myapplication.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: SharedRepository
    ): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        repository.observableFlow.map {result ->
            onNewDataState(result)
        }
    }

    private suspend fun onNewDataState(result: Result<List<DummyDataModel>>) {
        val newUiState = result.toUiState()
        when (result) {
            is Result.Success -> {
                _uiState.emit(newUiState)
            }
            is Result.Error -> {
                if ((_uiState.value is UiState.Error && newUiState != _uiState.value) || _uiState.value is UiState.Loading) {
                    _uiState.emit(newUiState)
                }
            }
            else -> {
                if (_uiState.value !is UiState.Success) {
                    _uiState.emit(newUiState)
                }
            }
        }
    }


    fun getEventClickListener() : EventListener{
        val onClick = {
            //TODO - Add click handling logic
        }
        return EventListener(onClick)
    }


    class EventListener(private val onItemClickListener: () -> Unit){
        fun onClick() = onItemClickListener
    }
}

fun Result<List<DummyDataModel>>.toUiState(): UiState{
    return when(this){
        is Result.Loading -> UiState.Loading
        is Result.Error -> UiState.Error(this.exception?.message)
        is Result.Success -> UiState.Success(this.data.map { it.toUiData() })
        else -> UiState.Error("No Such case")
    }
}

fun DummyDataModel.toUiData():UiData{
    //TODO - Add logic of converting data to ui data
    return UiData(this.text)
}