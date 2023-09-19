package com.example.kotlin_springboot.global.security.principle

import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AuthDetailsService (
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(email : String) : UserDetails {
         val user : User? = email.let { userFacade.getUserEmail(it)}
        return AuthDetails(user!!.email)
    }
}