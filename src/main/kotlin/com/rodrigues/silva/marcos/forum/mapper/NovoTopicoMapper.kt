package com.rodrigues.silva.marcos.forum.mapper

import com.rodrigues.silva.marcos.forum.dto.NovoTopicoDto
import com.rodrigues.silva.marcos.forum.model.Topico
import com.rodrigues.silva.marcos.forum.service.CursoService
import com.rodrigues.silva.marcos.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
): Mapper<NovoTopicoDto, Topico> {

    override fun map(dto: NovoTopicoDto): Topico {
        return Topico(
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        )
    }
}
