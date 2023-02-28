package com.example.taskonworkmanger.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskonworkmanger.domain.DevByteVideo

class SharedViewModel : ViewModel() {
    private val _currentItemDetails = MutableLiveData<DevByteVideo>()
    val currentItemDetails: LiveData<DevByteVideo> = _currentItemDetails
    fun setCurrentDetails(currentItem: DevByteVideo) {
        _currentItemDetails.value = currentItem
    }
}