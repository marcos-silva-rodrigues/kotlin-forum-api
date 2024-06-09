package com.rodrigues.silva.marcos.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtil {

    private val expiration : Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret : String

    fun generateToken(username: String): String? {
        val keySecret = Keys.hmacShaKeyFor(secret.toByteArray())
        return Jwts.builder()
            .subject(username)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(keySecret, Jwts.SIG.HS256)
            .compact()
    }
}