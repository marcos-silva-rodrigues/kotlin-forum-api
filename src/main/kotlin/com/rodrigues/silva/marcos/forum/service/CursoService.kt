package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.exception.NotFoundException
import com.rodrigues.silva.marcos.forum.model.Curso
import com.rodrigues.silva.marcos.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService (
    private var repository: CursoRepository
) {

    fun buscarPorId(id: Long): Curso {
        return repository
            .findById(id)
            .orElseThrow {
                NotFoundException("Curso não encontrado")
            }
    }

}
