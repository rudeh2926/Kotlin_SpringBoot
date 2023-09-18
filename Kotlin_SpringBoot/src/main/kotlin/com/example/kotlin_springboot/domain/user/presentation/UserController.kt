package com.example.kotlin_springboot.domain.user.presentation

import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserSignupRequest
import com.example.kotlin_springboot.domain.user.service.UserSignupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userSignupService: UserSignupService
) {
    @PostMapping
    fun signup (userSignupRequest: UserSignupRequest) {
        userSignupService.signup(userSignupRequest)
    }
}