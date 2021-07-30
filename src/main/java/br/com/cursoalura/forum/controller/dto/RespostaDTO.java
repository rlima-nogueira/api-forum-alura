package br.com.cursoalura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.cursoalura.forum.model.Resposta;

public class RespostaDTO {
    
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public Long getId() {
        return this.id;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public String getNomeAutor() {
        return this.nomeAutor;
    }
    
    public RespostaDTO(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

}
