package com.example.forumAlura.forumAlura.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TopicoRequest(

    @NotEmpty @NotNull
    val titulo: String,
    @NotEmpty @NotNull
    val mensagem: String,
    @NotEmpty @NotNull
    val curso: String,
)
