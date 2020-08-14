package br.com.alphapires.forum.config.validacao;

public class ErroResquestDto {
	
	private String campo;
	
	private String erro;

	public ErroResquestDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}
