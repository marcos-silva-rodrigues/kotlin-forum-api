package com.rodrigues.silva.marcos.forum.repository

import com.rodrigues.silva.marcos.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
}