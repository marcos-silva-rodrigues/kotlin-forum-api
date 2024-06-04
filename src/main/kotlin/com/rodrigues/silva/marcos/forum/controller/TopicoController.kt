package com.rodrigues.silva.marcos.forum.controller

import com.rodrigues.silva.marcos.forum.dto.AtualizaoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.dto.TopicoView
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*


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
    fun cadastrar(@RequestBody @Valid dto: NovoTopicoDto): Topico {
        return service.cadastrar(dto)
    }


    @PutMapping
    fun atualizar(@RequestBody @Valid dto: AtualizaoTopicoDto) {
        service.atualizar(dto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}