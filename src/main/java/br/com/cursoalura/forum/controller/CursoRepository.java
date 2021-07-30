package br.com.cursoalura.forum.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoalura.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

   Curso findByNome(String nome);

}
