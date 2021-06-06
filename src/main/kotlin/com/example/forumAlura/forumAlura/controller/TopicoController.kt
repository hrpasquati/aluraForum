package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.TopicoRequest
import com.example.forumAlura.forumAlura.model.Topico
import com.example.forumAlura.forumAlura.service.TopicoService
import org.apache.catalina.connector.Response
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val topicoService: TopicoService
) {

    @PostMapping
    fun create(@RequestBody @Valid topicoRequest: TopicoRequest,
               @RequestHeader id: Long, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<Topico> {
        val topico = topicoService.create(topicoRequest, id)
        val uri = uriComponentsBuilder.path("/topico").buildAndExpand(topico).toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun allTopicos(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<Topico>> {
        return ResponseEntity.ok(topicoService.findAll(pageable))
    }

    @GetMapping("/{id}")
    fun findTopico(@PathVariable id: Long): ResponseEntity<Topico> {
        return ResponseEntity.ok(topicoService.findById(id))
    }

    @DeleteMapping("/id")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(topicoService.delete(id))
    }
}