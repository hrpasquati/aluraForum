package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE c.nome = :nome")
    fun findByNome(@Param("nome") nomeCurso: String): Curso?

}