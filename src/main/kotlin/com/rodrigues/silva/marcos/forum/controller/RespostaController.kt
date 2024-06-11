package com.rodrigues.silva.marcos.forum.controller

import com.rodrigues.silva.marcos.forum.model.Resposta
import com.rodrigues.silva.marcos.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearerAuth")
class RespostaController(
    private val respostaService: RespostaService
) {

    @PostMapping
    fun salvar(@RequestBody @Valid resposta: Resposta) = respostaService.salvar(resposta)
}