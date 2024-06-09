package com.rodrigues.silva.marcos.forum.config

import com.rodrigues.silva.marcos.forum.service.UsuarioService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtUtil(
    private val usuarioService: UsuarioService
) {

    private val expiration : Long = 1000 * 60 * 60 * 10

    @Value("\${jwt.secret}")
    private lateinit var secret : String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts.builder()
            .subject(username)
            .claim("role", authorities)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secretKey(), Jwts.SIG.HS256)
            .compact()
    }

    private fun secretKey(): SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun isValid(jwt: String?): Boolean {
        return try {
            extracted(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun extracted(jwt: String?): Jws<Claims>? {
        return Jwts.parser()
            .verifyWith(secretKey())
            .build()
            .parseSignedClaims(jwt)
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = extracted(jwt)?.payload?.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }

}