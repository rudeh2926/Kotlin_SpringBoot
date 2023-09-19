package com.example.kotlin_springboot.domain.user.service

import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.domain.user.exception.PasswordMissMatchException
import com.example.kotlin_springboot.domain.user.facade.UserFacade
import com.example.kotlin_springboot.domain.user.presentation.dto.request.ChangePasswordRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserPasswordChangeService (
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        val user : User = userFacade.getCurrentUser()
        if (passwordEncoder.matches(changePasswordRequest.validPassword, user.password) || changePasswordRequest.validPassword == changePasswordRequest.newPassword) {
            throw PasswordMissMatchException
        }
        user.changePassword(passwordEncoder.encode(changePasswordRequest.newPassword))
    }
}