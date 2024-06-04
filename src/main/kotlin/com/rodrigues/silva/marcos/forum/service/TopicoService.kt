package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.dto.AtualizaoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.TopicoView
import com.rodrigues.silva.marcos.forum.mapper.NovoTopicoMapper
import com.rodrigues.silva.marcos.forum.mapper.TopicoViewMapper
import com.rodrigues.silva.marcos.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),

    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoMapper: NovoTopicoMapper,
) {
    fun listar(): List<TopicoView> {
        return topicos.stream()
            .map { topicoViewMapper.map(it) }
            .collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  topicos.stream()
            .filter { it.id == id }
            .findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDto): Topico {
        val topico = novoTopicoMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topico
    }

    fun atualizar(dto: AtualizaoTopicoDto): TopicoView {
        val topico =  topicos.stream()
            .filter { it.id == dto.id }
            .findFirst().get()

        val topicoAtualizado = Topico(
            id = dto.id,
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
        topicos = topicos.minus(topico)
    }
}