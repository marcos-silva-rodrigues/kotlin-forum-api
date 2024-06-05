package com.rodrigues.silva.marcos.forum.repository

import com.rodrigues.silva.marcos.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}