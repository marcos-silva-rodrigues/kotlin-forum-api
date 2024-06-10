package com.rodrigues.silva.marcos.forum.config

import com.rodrigues.silva.marcos.forum.security.JWTAuthenticationFilter
import com.rodrigues.silva.marcos.forum.security.JwtLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeRequests {
                authorize(HttpMethod.POST,"/login", permitAll)
                authorize("/topicos", hasAuthority("LEITURA_ESCRITA"))
                authorize(HttpMethod.GET, "/swagger-ui/*", permitAll)
                authorize(HttpMethod.GET, "/v3/api-docs/**", permitAll)
                authorize("*", authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtLoginFilter(authenticationManager = authenticationManager(), jwtUtil = jwtUtil))
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JWTAuthenticationFilter(jwtUtil))
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            httpBasic {  }
            formLogin { disable() }
        }

        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()
    @Bean
    fun authenticationManager(): AuthenticationManager =
        ProviderManager(DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService)
                it.setPasswordEncoder(encoder())
            })

}