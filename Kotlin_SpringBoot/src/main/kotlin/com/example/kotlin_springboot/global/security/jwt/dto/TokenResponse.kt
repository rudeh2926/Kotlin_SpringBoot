package com.example.kotlin_springboot.global.security.jwt.dto

class TokenResponse (
    val accessToken : String,
    val expiredAt : Long
)