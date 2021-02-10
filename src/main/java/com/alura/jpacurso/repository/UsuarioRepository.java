package com.alura.jpacurso.repository;

import java.util.Optional;

import com.alura.jpacurso.modelo.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
