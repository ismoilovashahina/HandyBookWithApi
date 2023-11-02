package com.example.handybookwapi.model

data class User(
    val access_token: String,
    val id: Int,
    val fullname:String,
    val username: String
)