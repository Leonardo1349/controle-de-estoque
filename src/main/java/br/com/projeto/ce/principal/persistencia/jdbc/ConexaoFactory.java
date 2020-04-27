package br.com.projeto.ce.principal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	public static Connection abrirConexao() {
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=LA_CONTROLE_DE_ESTOQUE;user=sa;password=leonardo1989";
		Connection conexao = null;
		try {
			//Força o carregamento do driver para teste no Servlet
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			
			conexao = DriverManager.getConnection(url);
			System.out.println("Conectado com Sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Conexão Falhou!!");
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return conexao;
	}

}
