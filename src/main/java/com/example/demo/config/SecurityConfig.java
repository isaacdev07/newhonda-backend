package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	SecurityFilter securityFilter;
	
	//bean para deixar as rotas publicas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		//get hello world para verificar se a api esta rodando
                        .requestMatchers("/hello-world").permitAll() 
                        //post de cadastro e logins publicos
                        .requestMatchers(HttpMethod.POST, "/user/create", "/user/login").permitAll()
                        //get nos veiculos somente autenticado
                        .requestMatchers(HttpMethod.GET, "/vehicle").authenticated()
                        //swagger liberado para todos
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        //somente role admin pode criar veiculos
                        .requestMatchers(HttpMethod.POST, "/vehicle/create").hasRole("ADMIN")
                        
                        .anyRequest().authenticated() 
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // hash de senha password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
