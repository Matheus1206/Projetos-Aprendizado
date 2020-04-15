package br.com.bamt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.bamt.modelo.Pessoa;

public class PessoaDAOImplementation implements PessoaDAO{

	private Connection connection;
	private List<Pessoa> lista = new ArrayList<Pessoa>();

	public PessoaDAOImplementation(Connection connection){
		this.connection = connection;
	}

	@Override
	public List<Pessoa> listaPessoa() {
		try(PreparedStatement pst = connection.prepareStatement("SELECT ID,NOME,ENDERECO FROM PESSOA")){
			try(ResultSet rst = pst.executeQuery()){
				while(rst.next()) {
					Pessoa pessoa = new Pessoa(rst.getInt("ID"),rst.getString("NOME"),rst.getString("ENDERECO"));
					lista.add(pessoa);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

	@Override
	public void adicionar(Pessoa pessoa) {
		try(PreparedStatement pst = connection.prepareStatement("INSERT INTO PESSOA (NOME,ENDERECO) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS)){
			pst.setString(1, pessoa.getNome());
			pst.setString(2, pessoa.getEndereco());
			pst.executeUpdate();

			try(ResultSet rst = pst.getGeneratedKeys()){
				while(rst.next()) {
					pessoa.setId(rst.getInt(1));
					System.out.println("Usuário adicionado com sucesso!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pessoa pesquisar(String usuario) {
		Pessoa pessoa = null;
		try(PreparedStatement ps = connection.prepareStatement("SELECT ID,NOME,ENDERECO FROM PESSOA WHERE NOME = ?")){
			ps.setString(1, usuario);
			try(ResultSet rst = ps.executeQuery()){
				if(rst.next()) {
					pessoa = new Pessoa();
					pessoa.setId(rst.getInt(1));
					pessoa.setNome(rst.getString(2));
					pessoa.setEndereco(rst.getString(3));
					System.out.println("Pessoa existe no banco");
				}else {
					System.out.println("Pessoa não existe no banco");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoa;
	}

	@Override
	public void deletar(String usuario) {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM PESSOA WHERE NOME = ?")){
			ps.setString(1, usuario);
			int linhaModificada = ps.executeUpdate();
			if(linhaModificada == 0) {
				throw new RuntimeException("ERRO ao executar query de DELETE");
			}else {
				System.out.println("Usuário " + usuario + " deletado com sucesso! " );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void alterarNome(String nomeAntigo,String nomeNovo) {
		try(PreparedStatement ps = connection.prepareStatement("UPDATE PESSOA SET NOME = ? WHERE NOME = ?")){
			ps.setString(1, nomeNovo);
			ps.setString(2, nomeAntigo);
			int linhaModificada = ps.executeUpdate();
			if(linhaModificada == 0) {
				throw new RuntimeException("ERRO ao executar query de UPDATE!");
			}else {
				System.out.println("Nome alterado com sucesso!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
