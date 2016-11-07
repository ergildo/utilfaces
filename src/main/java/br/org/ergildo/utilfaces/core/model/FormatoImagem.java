package br.org.ergildo.utilfaces.core.model;

public enum FormatoImagem {

	JPG("JPG", "image/jpg", "jpg"), PNG("PNG", "image/png", "png");

	private String descricao;
	private String tipo;
	private String contentType;

	private FormatoImagem(String descricao, String tipo, String contentType) {
		this.descricao = descricao;
		this.tipo = tipo;
		this.contentType = contentType;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
