package com.jordiee.kotlindev.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JordieeViewModel : ViewModel() {
    val workResultLiveData = MutableLiveData<Int>();


     fun setWorkResult(result : Int) {
        workResultLiveData.setValue(result)
    }
}