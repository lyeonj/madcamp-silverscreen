package com.example.silverscreen.ui.seen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "My Ticket"
    }

    val text: LiveData<String> = _text

    fun getImageFileNames(): List<String> {
        return (1..20).map{"seen_poster/poster_$it.png"}
    }
}