package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.model.Curso
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList()
) {
    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter {
            it.id == id
        }.findFirst().get()
    }

    fun cadastrar(topico: Topico): Topico {
        topicos.plus(topico)
        return topico
    }
}