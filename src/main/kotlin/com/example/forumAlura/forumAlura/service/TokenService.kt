package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.model.Usuario
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    private val expiration: String = "",
    private val secret: String = "86400000"
) {


    fun gerarToken(authenticate: Authentication): String {

        val hoje = Date()
        val dataExpiracao = Date(hoje.time.plus(expiration.toLong()))

        val principal: Usuario = authenticate.principal as Usuario
        return Jwts.builder().setIssuer("Forum alura")
            .setSubject(principal.id.toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isTokenValido(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getIdUsuario(token: String): Long {
        val body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).body
        return body.subject.toLong()
    }
}
