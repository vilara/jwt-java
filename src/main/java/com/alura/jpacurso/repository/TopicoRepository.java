package com.alura.jpacurso.repository;

import java.util.List;

import com.alura.jpacurso.modelo.Topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<Topico> findByCursoNome(String nomeCurso, Pageable pageble);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome=:nomeCurso")
    List<Topico> carregarPorNomedoCurso(@Param("nomeCurso") String nomeCurso);

    


    
}
