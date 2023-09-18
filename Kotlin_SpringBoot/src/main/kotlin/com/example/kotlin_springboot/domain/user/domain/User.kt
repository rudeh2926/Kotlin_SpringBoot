package com.example.kotlin_springboot.domain.user.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_user")
class User (
    @Id
    val id : UUID,
    val password : String,
    val email : String
)