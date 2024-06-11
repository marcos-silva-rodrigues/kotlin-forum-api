package com.rodrigues.silva.marcos.forum.service

import com.rodrigues.silva.marcos.forum.model.Resposta
import com.rodrigues.silva.marcos.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {
    fun salvar(resposta: Resposta) {
        respostaRepository.save(resposta)
        val emailAutor  = resposta.topico.autor.email
        emailService.notificar(emailAutor)
    }

}