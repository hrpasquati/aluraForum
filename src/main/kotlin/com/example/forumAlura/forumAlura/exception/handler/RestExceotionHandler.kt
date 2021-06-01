package com.example.forumAlura.forumAlura.exception.handler

import com.example.forumAlura.forumAlura.exception.BadRequestExcepetionDetail
import com.example.forumAlura.forumAlura.exception.FindByCursoException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceotionHandler {

    @ExceptionHandler(
        FindByCursoException::class)
    fun handlerBadRequest(runtimeException: RuntimeException) : ResponseEntity<BadRequestExcepetionDetail> {
        return ResponseEntity(
            BadRequestExcepetionDetail(
            titulo = "Bad Request Exception. Check the documentation",
                status = HttpStatus.BAD_REQUEST.value(),
                mensagem = runtimeException.message ?: "Não veio mensagem",
                timesTamp = LocalDateTime.now()
        ), HttpStatus.BAD_REQUEST)
    }

}