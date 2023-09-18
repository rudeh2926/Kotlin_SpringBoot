package com.example.kotlin_springboot.global.error

import java.time.LocalDateTime

data class ErrorResponse(
    val status: Int,
    val massage: String,
    val timestamp: LocalDateTime
)