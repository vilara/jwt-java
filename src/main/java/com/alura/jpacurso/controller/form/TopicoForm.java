package com.alura.jpacurso.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alura.jpacurso.modelo.Curso;
import com.alura.jpacurso.modelo.Topico;
import com.alura.jpacurso.repository.CursoRepository;

import org.hibernate.validator.constraints.Length;




public class TopicoForm {

    @NotNull @NotEmpty @Length(min =5)
    private String titulo;
    @NotNull @NotEmpty @Length(min =5)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

	public Topico convverter(CursoRepository cursorepository) {

        Curso curso = cursorepository.findByNome("nomeCurso");
		return new Topico(titulo, mensagem, curso);
	}
}
