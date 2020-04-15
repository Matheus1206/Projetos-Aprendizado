package br.com.bamt.modelo;

public class Pessoa {
	
	private Integer id;
	private String nome;
	private String endereco;
	
	public Pessoa(Integer id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Pessoa(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Pessoa() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
