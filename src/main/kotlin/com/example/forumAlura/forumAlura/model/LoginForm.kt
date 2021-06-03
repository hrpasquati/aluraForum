package com.example.forumAlura.forumAlura.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class LoginForm(

    @NotNull @NotEmpty
    val email: String,
    @NotNull @NotEmpty
    val senha: String
)