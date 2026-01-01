package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createNewUser(UserDTO userDTO) {
		
			User user = new User();
			
			user.setName(userDTO.getName());
			user.setPassword(userDTO.getPassword());
			user.setEmail(userDTO.getEmail());
			user.setPhone(userDTO.getPhone());
			user.setCpf(userDTO.getCpf());
			
			return userRepository.save(user);
	}

}
