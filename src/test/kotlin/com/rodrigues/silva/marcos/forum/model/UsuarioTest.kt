package com.rodrigues.silva.marcos.forum.model

object UsuarioTest {

    fun build() = Usuario(
        id = 1,
        nome = "Fulano",
        email = "fulano@email.com",
        password = "xpto09123",
        role = listOf(
            Role(id = 1, nome = "USUARIO")
        )
    )

}
