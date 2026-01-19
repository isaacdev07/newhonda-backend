	package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AuthenticationDTO;
import com.example.demo.dtos.LoginResponseDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;
	
	//endpoint para criar um novo usuario
	@PostMapping("/create")
	public ResponseEntity<User> createNewUser(@Valid @RequestBody UserDTO userDTO) {
	    User newUser = userService.createNewUser(userDTO);
	    //retorna sucesso e o usuario criado
	    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO data) {
		
		//procura o usuario pelo email
		User user = this.repository.findByEmail(data.email());
		
		//verifica se a senha e/ou email estao corretos
		if(user != null && passwordEncoder.matches(data.password(), user.getPassword())) {
			String token = tokenService.generateToken(user);
			
			//atualiza o token
			user.setCurrentToken(token);
			
			//salva o token novo
			this.repository.save(user);
			
			//tudo ok retorna o token
			return ResponseEntity.ok(new LoginResponseDTO(token));
		}
		//nao passou do if mensagem de erro
		return ResponseEntity.badRequest().build();		
		
	}
	
}
