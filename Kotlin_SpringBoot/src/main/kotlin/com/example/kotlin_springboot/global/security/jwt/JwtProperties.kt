package com.example.kotlin_springboot.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("auth.jwt")
class JwtProperties (
    val secretKey : String,
    val accessKey : String,
    val accessExp : Long,
    val header : String,
    val prefix : String
)