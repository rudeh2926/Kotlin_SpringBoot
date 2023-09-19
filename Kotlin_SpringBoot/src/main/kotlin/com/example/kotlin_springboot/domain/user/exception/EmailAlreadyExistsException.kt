package com.example.kotlin_springboot.domain.user.exception

import com.example.kotlin_springboot.global.error.exception.BusinessException
import com.example.kotlin_springboot.global.error.exception.ErrorCode

object EmailAlreadyExistsException : BusinessException (
    ErrorCode.EMAIL_ALREADY_EXISTS
)