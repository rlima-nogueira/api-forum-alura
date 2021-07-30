package br.com.cursoalura.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cursoalura.forum.controller.dto.DetalhesTopicoDTO;
import br.com.cursoalura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.cursoalura.forum.controller.form.TopicoForm;
import br.com.cursoalura.forum.model.Curso;
import br.com.cursoalura.forum.model.Topico;
import br.com.cursoalura.forum.repository.TopicoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    
    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso) {

        if (Objects.isNull(nomeCurso)) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/[id]").buildAndExpand(topico.getId()).toUri(); //
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDTO detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.getById(id);

        return new DetalhesTopicoDTO(topico);
    }

    // @PutMapping("/{id}")
    // @Transactional
    // public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
    //     Optional<Topico> optional = topicoRepository.findById(id);
    //     if (optional.isPresent()) {
    //         Optional<Topico> topico = form.atualizar(id, topicoRepository);
    //         topicoRepository.save(topico);
    //         return ResponseEntity.ok(new TopicoDTO(topico));
    //     }

    // return ResponseEntity.notFound().build();
    // }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
    
}