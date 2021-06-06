package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.dto.request.TopicoRequest
import com.example.forumAlura.forumAlura.exception.CursoNotFoundException
import com.example.forumAlura.forumAlura.exception.TopicoJaxisteException
import com.example.forumAlura.forumAlura.exception.TopicoNotFoundException
import com.example.forumAlura.forumAlura.exception.UsuarioNotFoundException
import com.example.forumAlura.forumAlura.model.Topico
import com.example.forumAlura.forumAlura.repository.CursoRepository
import com.example.forumAlura.forumAlura.repository.TopicoRepository
import com.example.forumAlura.forumAlura.repository.UsuarioRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val cursoRepository: CursoRepository
) {

    @Transactional
    fun create(topicoRequest: TopicoRequest, id: Long): Topico {

        val topicoBanco = topicoRepository.findByTitulo(topicoRequest.titulo)
            ?: throw TopicoNotFoundException("Tópico não encontrado")

        val usuario = usuarioRepository.findById(id).orElseThrow { UsuarioNotFoundException("Usuario não encontrado") }

        val curso = cursoRepository.findByNome(topicoRequest.curso) ?: throw CursoNotFoundException("Curso não encontrado")

        if (topicoBanco.titulo == topicoRequest.titulo) {
            throw TopicoJaxisteException("Não foi possível criar um tópico com esse titulo. Ele já existe")
        }

        val topico = Topico(
            titulo = topicoRequest.titulo,
            mensagem = topicoRequest.mensagem,
            autor = usuario,
            curso = curso,
        )

        return topicoRepository.save(topico)
    }

    fun findAll(pageable: Pageable): Page<Topico> {
        return topicoRepository.findAll(pageable)
    }

    fun findById(id: Long): Topico {
        return topicoRepository.findById(id).orElseThrow { TopicoNotFoundException("Tópico não encontrado") }
    }

    fun delete(id: Long): Any {
        val findById = findById(id)
        return topicoRepository.delete(findById)
    }

}
