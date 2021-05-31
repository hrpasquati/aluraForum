package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {

    fun findByNome(nome: String): Curso
}