package com.example.forumAlura.forumAlura.security

import com.example.forumAlura.forumAlura.exception.UserNotFoundException
import com.example.forumAlura.forumAlura.repository.UsuarioRepository
import com.example.forumAlura.forumAlura.service.TokenService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoViaTokenFilter(
    private val tokenService: TokenService,
    private val usuarioRepository: UsuarioRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token: String = recuperarToken(request) ?: "Token NULL"
        val valido: Boolean = tokenService.isTokenValido(token)

        if (valido) {
            autenticarCliente(token)
        }

        filterChain.doFilter(request, response)
    }


    private fun recuperarToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null
        }
        return token.substring(7, token.length)
    }

    private fun autenticarCliente(token: String) {
        val usuario = tokenService.getIdUsuario(token)
        val findById = usuarioRepository.findById(usuario).orElseThrow { UserNotFoundException("USUARIO NAO ENCONTRADO") }
        UsernamePasswordAuthenticationToken(findById, null, )
        SecurityContextHolder.getContext().authentication
    }
}