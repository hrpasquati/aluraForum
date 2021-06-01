package com.example.forumAlura.forumAlura.exception

import java.time.LocalDateTime

data class BadRequestExcepetionDetail(
    val titulo: String,
    val status: Int,
    val mensagem: String,
    val timesTamp: LocalDateTime
)