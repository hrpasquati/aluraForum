package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.CursoRequestAndResponse
import com.example.forumAlura.forumAlura.service.CursoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/curso")
class CursoController(
    private val cursoService: CursoService
) {

    @PostMapping
    fun crete(
        @RequestBody cursoRequestAndResponse: CursoRequestAndResponse,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<CursoRequestAndResponse> {
        val criandoUmCurso = cursoService.criandoUmCurso(cursoRequestAndResponse)
        val toUri = uriComponentsBuilder.path("/curso").buildAndExpand(criandoUmCurso).toUri()
        return ResponseEntity.created(toUri).build()
    }

}