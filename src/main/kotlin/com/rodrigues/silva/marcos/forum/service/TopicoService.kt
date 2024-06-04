package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.model.Curso
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {
    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter {
            it.id == id
        }.findFirst().get()
    }

    fun cadastrar(dto: NovoTopicoDto): Topico {
        val topico = Topico(
            id = topicos.size.toLong() + 1,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        )
        topicos = topicos.plus(topico)
        return topico
    }
}