package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UsuarioService(
    var usuarios: List<Usuario>
) {

    init {
        val usuario = Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "ana@email.com.br"
        )
        usuarios = Arrays.asList(usuario)
    }
    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter {
            it.id == id
        }.findFirst().get()
    }

}
