package br.com.projeto.ce.principal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.ce.principal.persistencia.entidade.Usuario;
import br.com.projeto.ce.principal.persistencia.jdbc.UsuarioDAO;

@SuppressWarnings("serial")
@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) 
			throws ServletException, IOException {
		
		//Se a sessao nao existir nao cria outra(false)
		HttpSession sessao = requisicao.getSession(false);
		
		//Se a sessao for diferente de nulo, invalida.
		if(sessao!=null) {
			sessao.invalidate();
		}
		
		
		//Toda vez que o método doGet é chamado será redirecionado para tela de login.
		resposta.sendRedirect("login.html");
	}
	
    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
    	
    	// 1) Capturando dados da tela
    	String login = requisicao.getParameter("login");
    	String senha = requisicao.getParameter("senha");
    	// 2) Colocando dados em objeto Usuario
    	Usuario usuario = new Usuario();
    	usuario.setLogin(login);
    	usuario.setSenha(senha);
    	// 3) Consultando se usuario existe no banco
    	UsuarioDAO usuDAo = new UsuarioDAO();
    	Usuario usuarioAutenticado = usuDAo.autenticar(login, senha);

    	// 4) Verificando se usuario foi encontrado
    	if(usuarioAutenticado!=null) { 
    		// 5) Colocando usuario na sessao
    		HttpSession sessao = requisicao.getSession();
    		sessao.setAttribute("usuarioAutenticado", usuarioAutenticado);
    		
    	/* Após 5 minutos de inatividade da session 
    	 * o usuario terá que se autenticar novamente*/
    		sessao.setMaxInactiveInterval(60*5);
    		
    		// 6) Redirecionando usuario para tela principal
    		requisicao.getRequestDispatcher("WEB-INF/index.jsp").forward(requisicao, resposta);
    		
    	}else {
    		resposta.getWriter().print("<script> window.alert('Não encontrado!');location.href='login.html';</script>");
    	}
	 
  }	

}
