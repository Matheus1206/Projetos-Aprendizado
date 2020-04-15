package br.com.bamt.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.bamt.dao.ConnectionFactory;
import br.com.bamt.dao.PessoaDAOImplementation;
import br.com.bamt.modelo.Pessoa;

public class Main {

	public static void main(String[] args) throws SQLException {
		try(Connection connection = new ConnectionFactory().fazConexao()){
			PessoaDAOImplementation psi = new PessoaDAOImplementation(connection);
			Pessoa teste = new Pessoa("Maria","Rua Penha de França");
			//Pesquisando um nome no banco
			psi.pesquisar("Matheus");
			//Listando os nomes com endereço do banco
			List<Pessoa> lista = psi.listaPessoa();
			lista.stream().forEach(lp -> System.out.println(lp.getNome() + " ---- " + lp.getEndereco()));
			//Adicionando um usuário no banco
			psi.adicionar(teste);
			//Alterando um usuário no banco
			psi.alterarNome("Maria", "Ananda");
			//Deletando um usuário no banco
			psi.deletar("Ananda");
		}
	
	}

}
