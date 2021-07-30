package br.com.cursoalura.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.cursoalura.forum.model.Topico;
import br.com.cursoalura.forum.repository.TopicoRepository;

public class AtualizacaoTopicoForm {

    @NotNull @NotEmpty @Length(min=5)
    private String titulo;

    @NotNull @NotEmpty @Length(min=10)
    private String mensagem;
    
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Optional<Topico> atualizar(Long id, TopicoRepository topicoRepository) {
        Optional<Topico> optional = topicoRepository.findById(id);
        
        optional.get().setTitulo(this.titulo);
        optional.get().setMensagem(this.mensagem);
        
        return optional;
    }
    
}
