package com.example.forumAlura.forumAlura.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class RespostaRequest(
    @field:NotNull @field: NotEmpty
    val mensagem: String
)
