package br.com.projeto.ce.principal;

import java.util.List;

import br.com.projeto.ce.principal.persistencia.entidade.Usuario;
import br.com.projeto.ce.principal.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

public static void main(String[] args) {		
		//testCadastrar();
		//testeAlterar();
		//testeExcluir();
		//testeBuscarPorId();
		testeBuscarPorTodos();
		//testeAutenticar();
		//testSalvar();
}
	
public static void testCadastrar(){
		
   //Criando o usuario
	Usuario usuario = new Usuario();
	usuario.setNome("Leonardo");
	usuario.setLogin("Leo");
	usuario.setSenha("102030");

	//Cadastrando o usuario no Banco de Dados
	 UsuarioDAO usuDAO = new UsuarioDAO();
	 usuDAO.cadastrar(usuario);

	System.out.println("Usuário Cadastrado com Sucesso!!");	
}

public static void testeAlterar() {
	
	Usuario usuario = new Usuario();
	usuario.setNome("Diego Oliveira Santos");
	usuario.setLogin("d.oliveira");
	usuario.setSenha("123456");
	usuario.setId(2);
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	usuDAO.alterar(usuario);
	
	System.out.println("Usuário Alterado com Sucesso!!");	
	
}

public static void testeExcluir() {
	
	Usuario usuario = new Usuario();
	usuario.setId(2);
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	usuDAO.excluir(usuario);
	
	System.out.println("Usuário Excluído com Sucesso!!");
}

@SuppressWarnings("unused")
private static void testeBuscarPorId() {
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	Usuario usuario = usuDAO.buscarPorId(1);
	
	System.out.println(usuario);
}

@SuppressWarnings("unused")
private static void testeBuscarPorTodos() {
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	List<Usuario> lista =  usuDAO.buscarPorTodos();
	
	for (Usuario usuario : lista) {
		System.out.println(usuario);
	}
	
}

@SuppressWarnings("unused")
private static void testeAutenticar() {
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	Usuario usuario = usuDAO.autenticar("PE", "8767");
	
	System.out.println(usuario);
	
}

public static void testSalvar(){
	Usuario usuario = new Usuario();
	usuario.setId(6);
	usuario.setNome("Mateus");
	usuario.setLogin("login");
	usuario.setSenha("senha");
	
	UsuarioDAO usuDAO = new UsuarioDAO();
	usuDAO.salvar(usuario);
	
	 System.out.println(" Usuario Salvo com Sucesso!!");
}

}
