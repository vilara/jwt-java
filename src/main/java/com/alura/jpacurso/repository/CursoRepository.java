package com.alura.jpacurso.repository;

import com.alura.jpacurso.modelo.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Curso findByNome(String nome);
    
}
