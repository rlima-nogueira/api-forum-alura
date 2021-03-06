package br.com.cursoalura.forum.controller;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cursoalura.forum.controller.dto.DetalhesTopicoDTO;
import br.com.cursoalura.forum.controller.dto.TopicoDTO;
import br.com.cursoalura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.cursoalura.forum.controller.form.TopicoForm;
import br.com.cursoalura.forum.model.Topico;
import br.com.cursoalura.forum.repository.CursoRepository;
import br.com.cursoalura.forum.repository.TopicoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/topicos")
@Api(value="Topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    
    @ApiOperation(value = "Mostra todos os tópicos")
    @GetMapping
    @Cacheable(value ="listaDeTopicos")
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, 
       @PageableDefault(sort="id", direction= Direction.DESC, page = 0, size = 10) Pageable paginacao) {
    
        if (Objects.isNull(nomeCurso)) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
            return TopicoDTO.converter(topicos);
        }
    }

    @ApiOperation(value = "Cria um novo tópico")
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/[id]").buildAndExpand(topico.getId()).toUri(); //
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @ApiOperation(value = "Busca um tópico específico")
    @GetMapping("/{id}")
 
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
        }
        
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Altera um tópico já existente")
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        
        if(optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }

        return ResponseEntity.notFound().build();
    }
    
    @ApiOperation(value = "Deleta um tópico")
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
         }

         return ResponseEntity.notFound().build();
    }
    
}