package com.example.myapplication.ui.launchfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(): ViewModel() {
    private val launchScreenDuration = 5000L

    private val _launchScreenEnded = MutableLiveData<Boolean>()
    val launchScreenEnded : LiveData<Boolean>
        get() = _launchScreenEnded

    fun onLaunchStart(){
        MoveToMainScreenAfterTimerEnd()
    }

    private fun MoveToMainScreenAfterTimerEnd() {
        viewModelScope.launch {
            delay(launchScreenDuration)
            _launchScreenEnded.postValue(true)
        }
    }


}