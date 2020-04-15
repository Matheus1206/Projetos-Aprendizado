package br.com.bamt.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	private final String URL = "jdbc:mysql://localhost:3304/teste?useTimezone=true&serverTimezone=UTC"; 
	private final String USUARIO = "root";
	private final String SENHA = "220418";
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPoolesDataSource = new ComboPooledDataSource();
		comboPoolesDataSource.setJdbcUrl(URL);
		comboPoolesDataSource.setUser(USUARIO);
		comboPoolesDataSource.setPassword(SENHA);
		
		comboPoolesDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPoolesDataSource;
	}
	
	public Connection fazConexao() throws SQLException {
		return dataSource.getConnection();
	}
	

}
