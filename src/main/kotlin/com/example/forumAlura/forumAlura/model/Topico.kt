package com.example.forumAlura.forumAlura.model

import com.example.forumAlura.forumAlura.model.enuns.StatusTopico
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    var status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @ManyToOne
    var autor: Usuario,
    @ManyToOne
    val curso: Curso,
    @OneToMany(mappedBy = "topico")
    val respostas: List<Respostas> = ArrayList()
)