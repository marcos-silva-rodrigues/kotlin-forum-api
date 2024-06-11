package com.rodrigues.silva.marcos.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(emailAutor: String) {
        val mensagem = SimpleMailMessage()
        mensagem.setSubject("[Alura] Resposta Recebida")
        mensagem.setText("Ola, o seu tópico foi respondido. Vamos la conferir?")
        mensagem.setTo(emailAutor)

        javaMailSender.send(mensagem)
    }
}