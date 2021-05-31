package com.example.forumAlura.forumAlura.model

import java.time.LocalDateTime

data class Respostas(
    val id: Long,
    val mensagem: String,
    val topico: Topico,
    val dataCriacao: LocalDateTime,
    val autor: Usuario,
    val solucao: Boolean = false
)
