package br.com.cursoalura.forum.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoalura.forum.model.Curso;
import br.com.cursoalura.forum.model.Topico;
import br.com.cursoalura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    
    @RequestMapping("/topicos")
    public List<TopicoDTO> lista(String nomeCurso) {

        if (Objects.isNull(nomeCurso)) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }
}
