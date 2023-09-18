package com.example.kotlin_springboot.domain.user.service

import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.domain.user.domain.UserRepository
import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserSignupRequest
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class UserSignupService (
    private val userRepository : UserRepository
) {
    @Transactional
    fun signup(userSignupRequest: UserSignupRequest) {

        val user = User(
            id = UUID(0,0),
            email = userSignupRequest.email,
            password = userSignupRequest.password
        )
        userRepository.save(user)
    }
}