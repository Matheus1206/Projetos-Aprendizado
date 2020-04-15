package br.com.bamt.dao;

import java.util.List;

import br.com.bamt.modelo.Pessoa;

public interface PessoaDAO {
	public List<Pessoa> listaPessoa();
	public void adicionar(Pessoa pessoa);
	public Pessoa pesquisar(String usuario);
	public void deletar(String usuario);
	public void alterarNome(String nomeAntigo,String nomeNovo);
}
