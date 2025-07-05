package com.example.silverscreen.ui.wish

data class WishItem(
    val imageRes: Int,
    val title: String,
    val date: String,
    val description: String,
    var liked: Boolean = false
)