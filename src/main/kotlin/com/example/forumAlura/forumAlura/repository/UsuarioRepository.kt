package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Long> {
}