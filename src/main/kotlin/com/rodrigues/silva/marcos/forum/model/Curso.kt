package com.rodrigues.silva.marcos.forum.model

import jakarta.persistence.*

@Entity
data class Curso(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val nome: String,
    val categoria: String
)
