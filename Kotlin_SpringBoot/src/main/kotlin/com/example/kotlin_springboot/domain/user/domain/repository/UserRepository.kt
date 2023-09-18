package com.example.kotlin_springboot.domain.user.domain.repository

import com.example.kotlin_springboot.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun existsByEmail(email : String) : Boolean
    fun findByEmail(email: String) : Optional<User>
}