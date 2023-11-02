package com.example.handybookwapi.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class SignUp (
    var username: String,
    val fullname: String,
    val email: String,
    val password: String,
)