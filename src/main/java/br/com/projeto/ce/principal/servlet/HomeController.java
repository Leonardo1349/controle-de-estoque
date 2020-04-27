package br.com.projeto.ce.principal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/home.do")
public class HomeController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
			throws ServletException, IOException {
		
		requisicao.getRequestDispatcher("WEB-INF/index.jsp").forward(requisicao, resposta);
		
		
	
	}

}
