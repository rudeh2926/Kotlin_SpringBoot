package com.example.kotlin_springboot.global.security.auth

import com.example.kotlin_springboot.domain.user.domain.User
import org.springframework.security.core.GrantedAuthority

class AuthDetails (
    private val user: User
) {
    fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    fun getPassword(): String? {
        return null
    }

    fun getUsername(): String {
        return user.email
    }

    fun isAccountNonExpired(): Boolean {
        return true
    }

    fun isAccountNonLocked(): Boolean {
        return true
    }

    fun isCredentialsNonExpired(): Boolean {
        return true
    }

    fun isEnabled(): Boolean {
        return true
    }
}