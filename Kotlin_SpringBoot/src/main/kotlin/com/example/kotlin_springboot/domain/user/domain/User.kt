package com.example.kotlin_springboot.domain.user.domain

import java.util.UUID
import javax.persistence.Entity

@Entity(name = "tbl_user")
class User (
    val id : UUID,
    val password : String,
    val email : String
)