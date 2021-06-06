package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Respostas
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Respostas, Long> {
}