package com.example.forumAlura.forumAlura.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Respostas(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val mensagem: String,

    @ManyToOne
    val topico: Topico,

    val dataCriacao: LocalDateTime,

    @ManyToOne
    val autor: Usuario,

    val solucao: Boolean = false
)
