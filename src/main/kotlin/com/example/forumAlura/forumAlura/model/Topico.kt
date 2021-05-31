package com.example.forumAlura.forumAlura.model

import com.example.forumAlura.forumAlura.model.enuns.StatusTopico
import java.time.LocalDateTime

data class Topico(

    val id: Long,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val autor: Usuario,
    val curso: Curso,
    val respostas: List<Respostas>
)