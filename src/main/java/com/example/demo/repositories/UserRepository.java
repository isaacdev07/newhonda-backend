package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	boolean existsByEmail(String email); //booleano para verificar no banco se o email ja existe
	boolean existsByCpf(String cpf); //booleano para verificar no banco se o cps ja existe
}
