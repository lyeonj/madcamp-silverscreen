package com.example.silverscreen.ui.wish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Today's Recommends"
    }
    val text: LiveData<String> = _text
}