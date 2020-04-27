package br.com.projeto.ce.principal.filtro;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Irá interceptar as requisiçõs
@WebFilter(dispatcherTypes= {DispatcherType.REQUEST}, urlPatterns="/*")
public class FiltroAutenticacao implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequisicao = (HttpServletRequest) requisicao;
		HttpServletResponse httpResposta = (HttpServletResponse) resposta;
		
		//Pega o caminho da requisição feita pelo usuario	
		String uri = httpRequisicao.getRequestURI();
		
		//Verificar se a sessao nao existe
		HttpSession sessao = httpRequisicao.getSession(false);
		
		//Se usuario estiver na sessao, ou estiver tentando acessar a tela de login ou a de autenticador.do ele será autenticado.
		if(sessao!=null || uri.lastIndexOf("login.html")!=-1 || uri.lastIndexOf("autenticador.do")!=-1) {		
		chain.doFilter(requisicao, resposta);
		}else {
			httpResposta.sendRedirect("login.html");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	

}
