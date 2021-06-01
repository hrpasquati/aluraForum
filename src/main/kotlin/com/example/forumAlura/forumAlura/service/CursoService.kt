package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.dto.request.CursoRequestAndResponse
import com.example.forumAlura.forumAlura.exception.CursoJaExisteException
import com.example.forumAlura.forumAlura.exception.CursoNotFoundException
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
        val procuraCursoPorNome = cursoRepository.findByNome(cursoRequestAndResponse.nome)
        if (procuraCursoPorNome != null && procuraCursoPorNome.nome == cursoRequestAndResponse.nome) {
            throw CursoJaExisteException("JÁ TEM UM CURSO COM ESSE NOME")
        }
        val curso = Curso(
            nome = cursoRequestAndResponse.nome,
            categoria = cursoRequestAndResponse.categoria
        )
        return cursoRepository.save(curso)
    }

    fun procurarCursoPorId(id: Long): Curso {
        return cursoRepository.findById(id).orElseThrow { CursoNotFoundException("Curso não encontrado") }
    }

    fun procuraCursoPorNome(nomeCurso: String): Curso {
        return cursoRepository.findByNome(nomeCurso) ?: throw CursoNotFoundException("Curso nao encontrado")
    }

    fun procuraTodosOsCursos(pageable: Pageable): Page<Curso> {
        return cursoRepository.findAll(pageable)
    }

    fun delete(id: Long): Any? {
        val findById = cursoRepository.findById(id).orElseThrow { CursoNotFoundException("Esse curso não existe") }
        return cursoRepository.delete(findById)
    }


    @Transactional
    fun atualizar(id: Long, cursoRequestAndResponse: CursoRequestAndResponse): Curso {
        val cursoBancoDeDados = procurarCursoPorId(id)
         cursoBancoDeDados.copy(
            nome = cursoRequestAndResponse.nome,
            categoria = cursoRequestAndResponse.categoria
        )

        cursoRepository.save(cursoBancoDeDados)

        return cursoBancoDeDados
    }
}