package com.example.forumAlura.forumAlura.security

import com.example.forumAlura.forumAlura.repository.UsuarioRepository
import com.example.forumAlura.forumAlura.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val tokenService: TokenService,
    private val usuarioRepository: UsuarioRepository
) : WebSecurityConfigurerAdapter() {

    //Configuracoes de autorizacao, URL, quem pode acessar, perfil de acesso
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/cursos").permitAll()
            .antMatchers(HttpMethod.GET, "/cursos/*").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .anyRequest()
            .authenticated().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter().javaClass)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
}