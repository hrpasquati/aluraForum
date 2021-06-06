package com.example.forumAlura.forumAlura.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class LoginForm(

    @field:NotNull @field:NotEmpty
    val email: String,
    @field:NotNull @field:NotEmpty
    val senha: String
)