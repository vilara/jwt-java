package com.alura.jpacurso.controller.DTO;

import java.util.ArrayList;
import java.util.List;

import com.alura.jpacurso.modelo.Perfil;
import com.alura.jpacurso.modelo.Usuario;

import org.springframework.data.domain.Page;

public class UsersDTO {


    private Long id;
    private String nome;
	private String email;
	private String senha;
    private List<Perfil> perfis;


    public UsersDTO(Usuario users) {
        this.id = users.getId();
        this.nome = users.getNome();
        this.email = users.getEmail();
        this.senha = users.getSenha();
        this.perfis = users.getPerfis();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }
    
}
