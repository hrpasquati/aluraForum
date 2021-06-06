package com.example.forumAlura.forumAlura.repository

import com.example.forumAlura.forumAlura.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Long> {

    override fun findById(id: Long): Optional<Usuario>
}