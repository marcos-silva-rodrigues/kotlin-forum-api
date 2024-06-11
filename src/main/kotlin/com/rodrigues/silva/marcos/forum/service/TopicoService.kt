package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.dto.AtualizaoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.TopicoPorCategoriaDto
import com.rodrigues.silva.marcos.forum.dto.TopicoView
import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.mapper.NovoTopicoMapper
import com.rodrigues.silva.marcos.forum.mapper.TopicoViewMapper
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicoService(
    private var repository: TopicoRepository,

    private val topicoViewMapper: TopicoViewMapper,
    private val novoTopicoMapper: NovoTopicoMapper,
) {

    val notFoundMessage = "Tópico não encontrado"

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso != null) {
            repository.findByCursoNome(nomeCurso, paginacao)
        } else {
            repository.findAll(paginacao)
        }

        return topicos.map { topicoViewMapper.map(it) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  repository
            .findById(id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }

        return topicoViewMapper.map(topico)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(dto: NovoTopicoDto): Topico {
        val topico = novoTopicoMapper.map(dto)
        return repository.save(topico)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(dto: AtualizaoTopicoDto): TopicoView {
        val topico =  repository
            .findById(dto.id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }

        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem
        topico.dataAlteracao = LocalDate.now()


        return topicoViewMapper.map(topico)
    }

    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}