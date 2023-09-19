package com.example.kotlin_springboot.global.security.jwt

import JwtTokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter (
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearer : String? = jwtTokenProvider.resolveToken(request)
        SecurityContextHolder.clearContext()
        bearer.let { SecurityContextHolder.getContext().authentication = jwtTokenProvider.authentication(bearer) }

        filterChain.doFilter(request, response)
    }
}