package br.com.alphapires.forum.dto;

import java.time.LocalDateTime;

import br.com.alphapires.forum.model.Resposta;

public class RespostaDto {

	private Long id;
	private String nomeAutor;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}
}
