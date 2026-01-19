package com.example.demo.entities;

import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User implements UserDetails {
	
	@Id //anotações para id e geração automatica
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//novos usuarios vem como user por padrao
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.USER;
	
	@NotNull(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido") 
    @Column(unique = true)
	private String cpf;	
	
	@NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
	private String email;
	
	//apenas para deixar valido somente o ultimo token gerado
	@Column(length = 2000) 
    private String currentToken;
	
	private String name;
	private String password;
	private String phone;
	
	public User() {
		
	}
	
	public User(Long id, String name, String email, String password, String phone, String cpf, UserRole role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.cpf = cpf;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getCurrentToken() {
		return currentToken;
	}

	public void setCurrentToken(String currentToken) {
		this.currentToken = currentToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	// metodos da classe user details

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
    	//role de admin
    	if(this.role == role.ADMIN) {
    		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    	}else {
    	
    	
        // definindo perfil de acesso como user
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    }
    	


    @Override
    public String getUsername() {
        return this.email; 
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; 
    }

    @Override
    public boolean isEnabled() {
        return true; 
    }
		
}
