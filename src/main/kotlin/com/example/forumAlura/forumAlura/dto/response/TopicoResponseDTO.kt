package com.example.forumAlura.forumAlura.dto.response

import com.example.forumAlura.forumAlura.model.Topico
import java.time.LocalDateTime

data class TopicoResponseDTO(

    val id: Long,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime
) {

    fun mapperTopicoForTopicoResponseDTO(topico: Topico): TopicoResponseDTO {
        return TopicoResponseDTO(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao
        )
    }
}