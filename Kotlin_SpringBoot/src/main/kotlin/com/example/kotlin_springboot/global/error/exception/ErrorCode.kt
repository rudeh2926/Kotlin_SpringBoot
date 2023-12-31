package com.example.kotlin_springboot.global.error.exception

enum class ErrorCode (
    val status: Int,
    val message: String
) {
    INVALID_TOKEN(401,"Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    USER_NOT_FOUND(404, "User Not Found"),
    EMAIL_ALREADY_EXISTS(409, "EMAIL Already Exists"),
    PASSWORD_MISS_MATCH(400, "Password Miss Match"),
}