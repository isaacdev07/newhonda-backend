package com.example.demo.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entities.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	
	public String generateToken(User user) {
		
		try {
			
		//define o algoritimo com a senha
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		String token = JWT.create()
				.withIssuer("newHonda-api")
				.withSubject(user.getEmail()) //identificação do usuario
				.withExpiresAt(genExpirationDate()) //tempo de expiração do token
				.sign(algorithm); //assina e finaliza
		
		return token;
		
		//excessao para tratar possivel erro
		} catch (JWTCreationException exception) {
		
		throw new RuntimeException("Erro ao gerar token", exception);
	}
	
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("newHonda-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException exception) {
			return "";
		}
	}
	
	//pega a data e deixa a validade do token em 2 horas
	private Instant genExpirationDate() {
		
		return LocalDateTime.now()
				.plusHours(2)
				.toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	
}
