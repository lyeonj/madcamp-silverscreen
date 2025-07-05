package com.example.silverscreen.ui.seen

import androidx.lifecycle.ViewModel
import com.example.silverscreen.R

class SeenViewModel : ViewModel() {

    fun getImageFileNames(): List<String> {
        return (1..20).map{"seen_poster/poster_$it.png"}
    }
}