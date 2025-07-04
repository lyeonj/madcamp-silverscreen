package com.example.silverscreen.ui.seen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is seen Fragment"
    }
    val text: LiveData<String> = _text
}