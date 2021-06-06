package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByTitulo(titulo: String): Topico?

}
