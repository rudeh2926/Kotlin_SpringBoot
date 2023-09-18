package com.example.kotlin_springboot.domain.user.service

import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.domain.user.domain.repository.UserRepository
import com.example.kotlin_springboot.domain.user.presentation.dto.request.UserSignupRequest
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
class UserSignupService (
    private val userRepository: UserRepository
) {
    @Transactional
    fun signup(userSignupRequest: UserSignupRequest) {

        if (userRepository.existsByEmail(userSignupRequest.email)) {}

        userRepository.save(
            User(
                id = UUID(0, 0),
                password = userSignupRequest.password,
                email = userSignupRequest.email

            )
        )
    }
}