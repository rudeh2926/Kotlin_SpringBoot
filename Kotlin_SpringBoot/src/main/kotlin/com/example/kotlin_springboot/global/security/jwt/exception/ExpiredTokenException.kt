package com.example.kotlin_springboot.global.security.jwt.exception

import com.example.kotlin_springboot.global.error.exception.BusinessException
import com.example.kotlin_springboot.global.error.exception.ErrorCode

object ExpiredTokenException : BusinessException (
    ErrorCode.EXPIRED_TOKEN
)