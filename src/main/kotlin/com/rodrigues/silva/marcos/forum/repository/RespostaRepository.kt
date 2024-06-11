package com.rodrigues.silva.marcos.forum.repository

import com.rodrigues.silva.marcos.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long> {
}