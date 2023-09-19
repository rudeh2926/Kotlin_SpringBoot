package com.example.kotlin_springboot.domain.user.presentation

import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserLoginRequest
import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserSignupRequest
import com.example.kotlin_springboot.domain.user.service.UserLoginService
import com.example.kotlin_springboot.domain.user.service.UserSignupService
import com.example.kotlin_springboot.global.security.jwt.dto.TokenResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userSignupService: UserSignupService,
    private val userLoginService: UserLoginService
) {
    @PostMapping
    fun signup (userSignupRequest: UserSignupRequest) {
        userSignupService.signup(userSignupRequest)
    }

    @PostMapping
    fun login (userLoginRequest: UserLoginRequest) : TokenResponse {
        return userLoginService.login(userLoginRequest)
    }

}