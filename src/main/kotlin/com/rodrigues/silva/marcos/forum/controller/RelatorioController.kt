package com.rodrigues.silva.marcos.forum.controller

import com.rodrigues.silva.marcos.forum.dto.TopicoPorCategoriaDto
import com.rodrigues.silva.marcos.forum.service.TopicoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Controller
@RequestMapping("/relatorios")
class RelatorioController(
    private val topicoService: TopicoService
) {

    @GetMapping
    fun relatorio(model: Model): String {
        model.addAttribute("topicosPorCategorias", topicoService.relatorio())
        return "relatorio"
    }
}