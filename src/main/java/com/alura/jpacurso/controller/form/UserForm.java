package com.alura.jpacurso.controller.form;

import com.alura.jpacurso.modelo.Usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserForm {

    private String nome;
	private String email;
	private String senha;

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

    public Usuario converter(){

        String senhaCripto = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.nome, this.email, senhaCripto);
    }

    
    
}
