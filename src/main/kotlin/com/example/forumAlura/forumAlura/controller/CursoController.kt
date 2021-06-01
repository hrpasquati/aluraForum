package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.CursoRequestAndResponse
import com.example.forumAlura.forumAlura.model.Curso
import com.example.forumAlura.forumAlura.service.CursoService
import org.apache.coyote.Response
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/cursos")
class CursoController(
    private val cursoService: CursoService
) {

    @PostMapping
    fun create(
        @RequestBody cursoRequestAndResponse: CursoRequestAndResponse,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<CursoRequestAndResponse> {
        val criandoUmCurso = cursoService.criandoUmCurso(cursoRequestAndResponse)
        val toUri = uriComponentsBuilder.path("/curso").buildAndExpand(criandoUmCurso).toUri()
        return ResponseEntity.created(toUri).build()
    }

    @GetMapping("/all")
    fun recuperaTodosOsCursos(@PageableDefault(page = 0, size = 10)pageable: Pageable): ResponseEntity<Page<Curso>>{
        return ResponseEntity.ok(cursoService.procuraTodosOsCursos(pageable))
    }

    @GetMapping("/{id}")
    fun procuraPorId(@PathVariable id: Long): ResponseEntity<Curso> {
        return ResponseEntity.ok(cursoService.procurarCursoPorId(id))
    }

    @GetMapping("/nomeCurso/{nomeCurso}")
    fun procuraCursoPorNome(@PathVariable nomeCurso: String): ResponseEntity<Curso> {
        return ResponseEntity.ok(cursoService.procuraCursoPorNome(nomeCurso))
    }

    @DeleteMapping("/delete/{id}")
    fun deletarUmCurso(@PathVariable() id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(cursoService.delete(id))
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody cursoRequestAndResponse: CursoRequestAndResponse): ResponseEntity<Curso> {
        return ResponseEntity.ok(cursoService.atualizar(id, cursoRequestAndResponse))
    }
}