package com.example.dragdropapplication.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExampleViewModel: ViewModel() {
    val integerListMutableLiveData: MutableLiveData<List<Int>> = MutableLiveData()
}