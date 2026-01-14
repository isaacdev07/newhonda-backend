package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User createNewUser(UserDTO userDTO) {
		
			User user = new User();
			
			//Validação usando o metodo do repository para verficar se o email ja existe
			if(userRepository.existsByEmail(userDTO.getEmail())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Este email ja esta cadastrado");
			}
			//Validação usando o metodo do repository para verificar se o cpf ja existe
			if(userRepository.existsByCpf(userDTO.getCpf())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Este CPF já esta cadastrado");
			}
			
			user.setName(userDTO.getName());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setCpf(userDTO.getCpf());
			user.setEmail(userDTO.getEmail());
			user.setPhone(userDTO.getPhone());	
			
			return userRepository.save(user);
	}
	
	

}
