package com.example.myapplication.ui.listfragment.model

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: List<UiData>): UiState()
    data class Error(val msg: String?): UiState()
}