package com.rodrigues.silva.marcos.forum.controller

import com.rodrigues.silva.marcos.forum.dto.AtualizaoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.TopicoView
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.service.TopicoService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService
) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid dto: NovoTopicoDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Topico> {
        val topicoView = service.cadastrar(dto)

        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }


    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid dto: AtualizaoTopicoDto): ResponseEntity<TopicoView> {
        val topicoView = service.atualizar(dto)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}