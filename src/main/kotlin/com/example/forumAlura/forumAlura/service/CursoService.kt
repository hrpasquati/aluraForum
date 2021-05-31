package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.dto.request.CursoRequestAndResponse
import com.example.forumAlura.forumAlura.model.Curso
import com.example.forumAlura.forumAlura.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val cursoRepository: CursoRepository
) {

    fun criandoUmCurso(cursoRequestAndResponse: CursoRequestAndResponse): Curso {
        val curso = Curso(
            nome = cursoRequestAndResponse.nome,
            categoria = cursoRequestAndResponse.categoria
        )
        return cursoRepository.save(curso)
    }

    fun procurarCursoPorId(id: Long): Curso {
       return cursoRepository.findById(id).orElseThrow { RuntimeException("Curso não encontrado") }
    }

    fun procuraCursoPorNome(nomeCurso: String): Curso {
        return cursoRepository.findByNome(nomeCurso)
    }


}