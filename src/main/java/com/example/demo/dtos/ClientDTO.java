package com.example.demo.dtos;

import com.example.demo.entities.User;
//dto apenas para retornar o nome e email ao inves de todas as informações
public class ClientDTO {
    
    private Long id;
    private String name;
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }

 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}