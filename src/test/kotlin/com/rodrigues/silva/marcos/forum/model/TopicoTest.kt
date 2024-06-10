package com.rodrigues.silva.marcos.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Rest Api",
        mensagem = "With Spring Boot",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}