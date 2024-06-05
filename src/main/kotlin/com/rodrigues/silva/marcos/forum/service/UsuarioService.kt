package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.model.Usuario
import com.rodrigues.silva.marcos.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private var repository: UsuarioRepository
) {

    fun buscarPorId(id: Long): Usuario {
        return repository
            .findById(id)
            .orElseThrow {
                NotFoundException("Usuario não encontrado")
            }
    }

}
