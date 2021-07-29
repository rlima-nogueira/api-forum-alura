package br.com.cursoalura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoalura.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
    
}
