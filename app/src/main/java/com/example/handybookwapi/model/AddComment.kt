package com.example.handybookwapi.model

data class AddComment(
    val book_id: Int,
    val reyting: Int,
    val text: String,
    val user_id: Int
)