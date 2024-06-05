package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.dto.AtualizaoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.TopicoView
import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.mapper.NovoTopicoMapper
import com.rodrigues.silva.marcos.forum.mapper.TopicoViewMapper
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var repository: TopicoRepository,

    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoMapper: NovoTopicoMapper,
) {

    val notFoundMessage = "Tópico não encontrado"
    fun listar(): List<TopicoView> {
        return repository
            .findAll()
            .map { topicoViewMapper.map(it) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  repository
            .findById(id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDto): Topico {
        val topico = novoTopicoMapper.map(dto)
        return repository.save(topico)
    }

    fun atualizar(dto: AtualizaoTopicoDto): TopicoView {
        val topico =  repository
            .findById(dto.id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }

        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem

        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}