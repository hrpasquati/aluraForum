package com.example.forumAlura.forumAlura.service

import com.example.forumAlura.forumAlura.dto.request.RespostaRequest
import com.example.forumAlura.forumAlura.exception.RespostaNotFoundException
import com.example.forumAlura.forumAlura.exception.TopicoNotFoundException
import com.example.forumAlura.forumAlura.exception.UserNotFoundException
import com.example.forumAlura.forumAlura.model.Respostas
import com.example.forumAlura.forumAlura.model.enuns.StatusTopico
import com.example.forumAlura.forumAlura.repository.RespostaRepository
import com.example.forumAlura.forumAlura.repository.TopicoRepository
import com.example.forumAlura.forumAlura.repository.UsuarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RespotaService(
    private val respostaRepository: RespostaRepository,
    private val topicoRepository: TopicoRepository,
    private val usuarioRepository: UsuarioRepository
) {

    @Transactional
    fun create(respostaRequest: RespostaRequest, idUsuario: Long, idTopico: Long): Respostas {

        val topico =
            topicoRepository.findById(idTopico).orElseThrow { TopicoNotFoundException("Esse tópico não existe") }

        val usuario =
            usuarioRepository.findById(idUsuario).orElseThrow { UserNotFoundException("Usuario não encontrado") }

        topico.apply {
            this.mensagem = respostaRequest.mensagem
            this.autor = usuario
            this.status = StatusTopico.RESPONDIDO
        }

        topicoRepository.save(topico)

        val resposta = Respostas(
            mensagem = respostaRequest.mensagem,
            topico = topico,
            autor = usuario
        )
        return respostaRepository.save(resposta)
    }

    fun findRespostaById(id: Long): Respostas {
        return respostaRepository.findById(id).orElseThrow { RespostaNotFoundException("Resposta não encontrada") }
    }

    fun atualiza(respostaRequest: RespostaRequest, id: Long): Respostas {
        val resposta = findRespostaById(id)
        resposta.mensagem = respostaRequest.mensagem
        return respostaRepository.save(resposta)
    }
}
