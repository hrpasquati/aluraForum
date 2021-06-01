package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.dto.request.CursoRequestAndResponse
import com.example.forumAlura.forumAlura.exception.FindByCursoException
import com.example.forumAlura.forumAlura.model.Curso
import com.example.forumAlura.forumAlura.repository.CursoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CursoService(
    private val cursoRepository: CursoRepository
) {

    @Transactional
    fun criandoUmCurso(cursoRequestAndResponse: CursoRequestAndResponse): Curso {
        val curso = Curso(
            nome = cursoRequestAndResponse.nome,
            categoria = cursoRequestAndResponse.categoria
        )
        return cursoRepository.save(curso)
    }

    fun procurarCursoPorId(id: Long): Curso {
       return cursoRepository.findById(id).orElseThrow { FindByCursoException("Curso não encontrado") }
    }

    fun procuraCursoPorNome(nomeCurso: String): Curso {
        return cursoRepository.findByNome(nomeCurso) ?: throw FindByCursoException("Curso nao encontrado")
    }

    fun procuraTodosOsCursos(pageable: Pageable): Page<Curso> {
        return cursoRepository.findAll(pageable)
    }

    fun delete(id: Long): Any? {
        val findById = cursoRepository.findById(id).orElseThrow { FindByCursoException("Esse curso não existe") }
        return cursoRepository.delete(findById)
    }
}