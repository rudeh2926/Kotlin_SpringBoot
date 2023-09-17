package com.example.kotlin_springboot.domain.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/hello")
    fun hello() : String{
        return "hello"
    }
}