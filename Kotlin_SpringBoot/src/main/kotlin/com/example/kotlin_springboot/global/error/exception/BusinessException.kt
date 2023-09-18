package com.example.kotlin_springboot.global.error.exception

import kotlin.RuntimeException

abstract class BusinessException (
    val errorCode : ErrorCode
) : RuntimeException()