package com.rodrigues.silva.marcos.forum.repository

import com.rodrigues.silva.marcos.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findByEmail(email: String?): Usuario?
}