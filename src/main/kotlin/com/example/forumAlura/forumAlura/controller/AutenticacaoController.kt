package com.example.forumAlura.forumAlura.controller

import com.example.forumAlura.forumAlura.dto.request.TokenDTO
import com.example.forumAlura.forumAlura.model.LoginForm
import com.example.forumAlura.forumAlura.service.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.naming.AuthenticationException
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AutenticacaoController(
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping
    fun autenticar(@RequestBody @Valid loginForm: LoginForm): ResponseEntity<TokenDTO> {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(loginForm.email, loginForm.senha)
        return try {
            val authenticate: Authentication = authManager.authenticate(usernamePasswordAuthenticationToken)
            val token = tokenService.gerarToken(authenticate)
            ResponseEntity.ok(TokenDTO(token, "Bearer"))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        }

    }

}