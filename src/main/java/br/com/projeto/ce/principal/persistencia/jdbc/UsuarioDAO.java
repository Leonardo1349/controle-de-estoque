package br.com.projeto.ce.principal.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.ce.principal.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection conexao = ConexaoFactory.abrirConexao();
	
	public void cadastrar(Usuario usuario) {		
		
		String sql = "INSERT INTO USUARIO(NOME, LOGIN, SENHA) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());//Substitui o ? pelo dado do usuario
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			
			//Executa o comando SQL no Banco de Dados
			ps.execute();			
			
		} catch (SQLException e) {
			System.out.println("Falha ao executar o comando SQL!!");
			e.printStackTrace();
		}

		
	}
	
	public void alterar(Usuario usuario) {
		
		String sql = "UPDATE USUARIO SET NOME = ?, LOGIN = ?, SENHA = ? WHERE ID = ?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println("Falha ao executar o comando SQL!!");
			e.printStackTrace();
		}
		
	}
	
  public void excluir(Usuario usuario) {
	  
	  String sql = "DELETE FROM USUARIO WHERE ID = ?";	  
	  
	  try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, usuario.getId());
		
		ps.execute();
		
	} catch (SQLException e) {
		System.out.println("Falha ao executar o comando SQL!!");
		e.printStackTrace();
	}
  }
  
  public Usuario buscarPorId(Integer id) {
	  
	  String sql = "SELECT ID, NOME, LOGIN, SENHA FROM USUARIO WHERE ID = ?";
	  
	  try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		
         ResultSet resultado = ps.executeQuery();
        
		//Posicionando o cursor no primeiro registro
		if(resultado.next()){
		Usuario usuario = new Usuario();
		usuario.setId(resultado.getInt("ID"));
		usuario.setNome(resultado.getString("NOME"));
		usuario.setLogin(resultado.getString("LOGIN"));
		usuario.setSenha(resultado.getString("SENHA"));
		
		return usuario;
		}
		
	} catch (SQLException e) {
		System.out.println("Falha ao executar o comando SQL!!");
		e.printStackTrace();
	}
	return null;
	  
  }
  
  public List<Usuario> buscarPorTodos() {
	  
	  String sql = "SELECT ID, NOME, LOGIN, SENHA FROM USUARIO";
	  
	  List<Usuario> lista = new ArrayList<Usuario>();
	  
	  try {
		PreparedStatement ps = conexao.prepareStatement(sql);		
		
		ResultSet resultado = ps.executeQuery(); 
		
		while(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("ID"));
			usuario.setNome(resultado.getString("NOME"));
			usuario.setLogin(resultado.getString("LOGIN"));
			usuario.setSenha(resultado.getString("SENHA"));			
			
			//Adicionando usuario na lista
			 lista.add(usuario);			
		}
		
	} catch (SQLException e) {
		System.out.println("Falha ao executar o comando SQL!!");
		e.printStackTrace();
	}
	return lista;
	  
  }
  
  public Usuario autenticar(String login, String senha) {
	 
	  String sql = "SELECT ID, NOME, LOGIN, SENHA FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";
	  
	  try {
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, login);
		ps.setString(2, senha);
		
		ResultSet resultado = ps.executeQuery();
		
		if(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("ID"));
			usuario.setNome(resultado.getString("NOME"));
			usuario.setLogin(resultado.getString("LOGIN"));
			usuario.setSenha(resultado.getString("SENHA"));
			
			return usuario;			
		}		
		
	} catch (SQLException e) {
		System.out.println("Falha ao executar o comando SQL!!");
		e.printStackTrace();
	}
	  
	return null;
	  
  }
  
 //se o usuario ja possuir o id so faz alteração dos  dados, se nao cadastra ele no BD
 public void salvar(Usuario usuario){
		if(usuario.getId()!=null && usuario.getId()!=0){
		alterar(usuario);
		}else{
		cadastrar(usuario);
		}
	}
  

}
