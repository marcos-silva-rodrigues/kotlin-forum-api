package com.rodrigues.silva.marcos.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Usuario (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val nome: String,
    val email: String,

    @JsonIgnore
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, )
    @JoinTable(name = "usuario_role",
        joinColumns = arrayOf(JoinColumn(name = "usuario_id", referencedColumnName = "id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id"))
    )
    val role: List<Role> = mutableListOf()
)
