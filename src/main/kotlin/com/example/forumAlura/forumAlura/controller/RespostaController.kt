package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.RespostaRequest
import com.example.forumAlura.forumAlura.model.Respostas
import com.example.forumAlura.forumAlura.service.RespotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
class RespostaController(
    private val respostaService: RespotaService
) {


    @PostMapping("/{id}")
    fun criarResposta(
        @RequestBody respostaRequest: RespostaRequest,
        @RequestHeader idUsuario: Long,
        @PathVariable idTopico: Long
    ): ResponseEntity<Respostas> {

        return ResponseEntity.ok(respostaService.create(respostaRequest, idUsuario, idTopico))
    }

    @GetMapping("/{id}")
    fun findRespostaById(@PathVariable id: Long): ResponseEntity<Respostas> {
        return ResponseEntity.ok(respostaService.findRespostaById(id))
    }

    @PutMapping("/{id}")
    fun atualizarResposta(
        @RequestBody @Valid respostaRequest: RespostaRequest,
        @PathVariable("id") id: Long
    ): ResponseEntity<Respostas> {
        return ResponseEntity.ok(respostaService.atualiza(respostaRequest, id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void>{
        respostaService.delete(id)
        return ResponseEntity.ok().build()
    }

}