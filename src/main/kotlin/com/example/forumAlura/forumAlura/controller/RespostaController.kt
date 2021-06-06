package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.RespostaRequest
import com.example.forumAlura.forumAlura.model.Respostas
import com.example.forumAlura.forumAlura.service.RespotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/respostas")
class RespostaController(
    private val respostaService: RespotaService
) {


    @PostMapping("/{id}")
    fun criarResposta(
        @RequestBody respostaRequest: RespostaRequest,
        @RequestHeader idUsuario: Long,
        @PathVariable idTopico: Long): ResponseEntity<Respostas>{

        return ResponseEntity.ok(respostaService.create(respostaRequest, idUsuario, idTopico))
    }

}