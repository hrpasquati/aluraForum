package com.example.forumAlura.forumAlura.model

import com.example.forumAlura.forumAlura.model.enuns.StatusTopico
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @ManyToOne
    val autor: Usuario,
    @ManyToOne
    val curso: Curso,
    @OneToMany(mappedBy = "topico")
    val respostas: List<Respostas>
)