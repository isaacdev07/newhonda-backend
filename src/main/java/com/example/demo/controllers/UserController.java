package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//endpoint para criar um novo usuario
	@PostMapping("/create")
	public ResponseEntity<User> createNewUser(@Valid @RequestBody UserDTO userDTO) {
	    User newUser = userService.createNewUser(userDTO);
	    //retorna sucesso e o usuario criado
	    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

}
