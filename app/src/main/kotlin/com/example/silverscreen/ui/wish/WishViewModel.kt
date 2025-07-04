package com.example.silverscreen.ui.wish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is wish Fragment"
    }
    val text: LiveData<String> = _text
}