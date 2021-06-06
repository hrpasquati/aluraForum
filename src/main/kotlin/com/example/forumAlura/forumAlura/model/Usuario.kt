package com.example.forumAlura.forumAlura.model

import javax.persistence.*

@Entity
@Table(name = "USUARIO")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String
)
