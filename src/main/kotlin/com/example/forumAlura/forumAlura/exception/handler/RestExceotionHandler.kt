package com.example.forumAlura.forumAlura.exception.handler

import com.example.forumAlura.forumAlura.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceotionHandler {

    @ExceptionHandler(
        CursoJaExisteException::class,
        TopicoJaxisteException::class
    )
    fun handlerBadRequest(runtimeException: RuntimeException): ResponseEntity<BadRequestExcepetionDetail> {
        return ResponseEntity(
            BadRequestExcepetionDetail(
                titulo = "Bad Request Exception. Check the documentation",
                status = HttpStatus.BAD_REQUEST.value(),
                mensagem = runtimeException.message ?: "Não veio mensagem",
                timesTamp = LocalDateTime.now()
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(
        CursoNotFoundException::class,
        UserNotFoundException::class,
        TopicoNotFoundException::class,
        UsuarioNotFoundException::class,
        RespostaNotFoundException::class
    )
    fun handlerNotFound(runtimeException: RuntimeException): ResponseEntity<BadRequestExcepetionDetail> {
        return ResponseEntity(
            BadRequestExcepetionDetail(
                titulo = "NOT FOUND Exception",
                status = HttpStatus.NOT_FOUND.value(),
                mensagem = runtimeException.message ?: "Não veio a mensagem",
                timesTamp = LocalDateTime.now()
            ), HttpStatus.NOT_FOUND
        )
    }

}