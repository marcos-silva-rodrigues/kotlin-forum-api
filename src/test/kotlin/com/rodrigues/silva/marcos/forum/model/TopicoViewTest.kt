package com.rodrigues.silva.marcos.forum.model

import com.rodrigues.silva.marcos.forum.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {

    fun build() = TopicoView(
        id = 1,
        titulo = "Spring boot",
        mensagem = "With Kotlin",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )

}
