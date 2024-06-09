package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.model.Usuario
import com.rodrigues.silva.marcos.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private var repository: UsuarioRepository
): UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository
            .findById(id)
            .orElseThrow {
                NotFoundException("Usuario não encontrado")
            }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user =  repository.findByEmail(username) ?: throw UsernameNotFoundException("not found")
        return UserDetail(user)
    }

}
