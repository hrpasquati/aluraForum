package com.example.forumAlura.forumAlura.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Respostas(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var mensagem: String,

    @ManyToOne
    val topico: Topico,

    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val autor: Usuario,

    val solucao: Boolean = false
)
