package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.mapper.NovoTopicoMapper
import com.rodrigues.silva.marcos.forum.mapper.TopicoViewMapper
import com.rodrigues.silva.marcos.forum.model.TopicoTest
import com.rodrigues.silva.marcos.forum.model.TopicoViewTest
import com.rodrigues.silva.marcos.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val paginacao: Pageable = mockk()


    val topicos = PageImpl(listOf(TopicoTest.build()))
    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: NovoTopicoMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository,
        topicoViewMapper,
        topicoFormMapper
    )


    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Kotlin avançado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando nome do curso for nulo`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar not found exception quando o topico nao for achado`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException>{
            topicoService.buscarPorId(1)
        }

        assertThat(atual.message).isEqualTo("Tópico não encontrado")
    }
}