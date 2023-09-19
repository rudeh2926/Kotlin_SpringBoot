package com.example.kotlin_springboot.domain.user.presentation.dto.request

class ChangePasswordRequest (
    val validPassword : String,
    val newPassword : String
)