package com.example.kotlin_springboot.domain.user.service

import JwtTokenProvider
import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.domain.user.exception.PasswordMissMatchException
import com.example.kotlin_springboot.domain.user.facade.UserFacade
import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserLoginRequest
import com.example.kotlin_springboot.global.security.jwt.dto.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
){
    fun login(userLoginRequest: UserLoginRequest) : TokenResponse {
        val user : User = userFacade.getUserEmail(userLoginRequest.email)
            if (passwordEncoder.matches(userLoginRequest.password, user.password)) {
                PasswordMissMatchException
            }
        return jwtTokenProvider.getToken(user)
    }
}