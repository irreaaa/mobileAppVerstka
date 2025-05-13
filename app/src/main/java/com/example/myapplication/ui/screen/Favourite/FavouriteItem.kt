package com.example.myapplication.ui.screen.Favourite

data class FavoriteItem(
    val id: Int,
    val title: String,
    val name: String,
    val price: String,
    val imageRes: Int,
    val isFavorite: Boolean
)