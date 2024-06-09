package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val usuario: Usuario
): UserDetails  {
    override fun getAuthorities() = null

    override fun getPassword(): String = usuario.password

    override fun getUsername(): String = usuario.email
}