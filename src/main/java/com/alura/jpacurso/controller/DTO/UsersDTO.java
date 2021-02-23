package com.alura.jpacurso.controller.DTO;

import com.alura.jpacurso.modelo.Usuario;

import org.springframework.data.domain.Page;

public class UsersDTO {

    private String nome;
	private String email;
	private String senha;

    public UsersDTO(Usuario users) {
        this.nome = users.getNome();
        this.email = users.getEmail();
        this.senha = users.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Page<UsersDTO> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsersDTO::new);
	}
    
}
