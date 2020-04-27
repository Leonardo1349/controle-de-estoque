package br.com.projeto.ce.principal.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.ce.principal.persistencia.entidade.Usuario;
import br.com.projeto.ce.principal.persistencia.jdbc.UsuarioDAO;

//http:/localhost:8080/br.com.projeto.ce/usucontroller.do

@SuppressWarnings("serial")
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	
    public UsuarioController() {
    	System.out.println("Construtor..");		
	}
    
    @Override
    public void init() throws ServletException { 
    	System.out.println("Init..");
    	super.init();
    }
	
	@Override
	protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
	             throws ServletException, IOException{
		
		resposta.setContentType("text/html");
		String acao = requisicao.getParameter("acao");
		
		if(acao.equals("excluir")) {
		String id = requisicao.getParameter("id");
        Usuario usuario = new Usuario();
		
		if(id!=null) {
		usuario.setId(Integer.parseInt(id));
		}
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usuario);
		
		resposta.sendRedirect("usucontroller.do?acao=listar_todos");
		
		}else if(acao.equals("listar_por_id")) {
			
			String id = requisicao.getParameter("id");
	        Usuario usuario = new Usuario();
			
			if(id!=null) {
			usuario.setId(Integer.parseInt(id));
			}
			int identificador = usuario.getId();
			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.buscarPorId(identificador);
			
			resposta.getWriter().print("Pesquisado com Sucesso! -> "+ usuDAO.buscarPorId(identificador));	
		}
         else if(acao.equals("listar_todos")) {			
			
			UsuarioDAO usuDAO = new UsuarioDAO();
			List<Usuario> lista =  usuDAO.buscarPorTodos();			
				
			requisicao.setAttribute("lista", lista);	
			
			RequestDispatcher dispatcher = requisicao.getRequestDispatcher("WEB-INF/listausuario.jsp");
			dispatcher.forward(requisicao, resposta);
			
		}else if(acao.equals("alterar")) {			
		
			String id = requisicao.getParameter("id");
			UsuarioDAO usuDAO = new UsuarioDAO();
			Usuario usuario = usuDAO.buscarPorId(Integer.parseInt(id));			
			
			requisicao.setAttribute("usuario", usuario);
			
			/*TODO quando clicar em alterar carregar a senha vazio, 
			 * se o usuario nao digitar nada na senha permanecer a 
			 * mesma do banco, porem se digitar uma nova senha 
			 * gravar ela no banco.
			 * 
			 * usuario.setSenha("");*/				
			
			RequestDispatcher dispatcher = requisicao.getRequestDispatcher("WEB-INF/formulariousuario.jsp");
			dispatcher.forward(requisicao, resposta);				
			
		}else if(acao.equals("cadastrar")) {			
			
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			UsuarioDAO usuDAO = new UsuarioDAO();
			
			if(usuario.getNome()!=null && !usuario.getNome().equals(""))
			usuDAO.cadastrar(usuario);
			
           requisicao.setAttribute("usuario", usuario);
			
			RequestDispatcher dispatcher = requisicao.getRequestDispatcher("WEB-INF/formulariousuario.jsp");
			dispatcher.forward(requisicao, resposta);			
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
		
		String id = requisicao.getParameter("id");
		String nome = requisicao.getParameter("nome");
		String login = requisicao.getParameter("login");
		String senha = requisicao.getParameter("senha");
		
		Usuario usuario = new Usuario();
		
		if(id!=null) {
		usuario.setId(Integer.parseInt(id));
		}
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		resposta.sendRedirect("usucontroller.do?acao=listar_todos");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy..");
		super.destroy();
	}

}
