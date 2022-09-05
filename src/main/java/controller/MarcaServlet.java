package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Marca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DAO.Conexao;
import DAO.MarcaDAO;

public class MarcaServlet extends Servlet {
	private static final long serialVersionUID = 1L; 
    public MarcaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("txtID");
		String nome = request.getParameter("txtNome");
		String btn = request.getParameter("btn");
		
		String erro = "";
		String saida = "";
		Marca marca = new Marca();
		List<Marca> lista = new ArrayList<Marca>();
		
		try {
			marca = validarMarca(id, nome, btn);
			Conexao conn = new Conexao();
			MarcaDAO md = new MarcaDAO(conn);
			
			switch (btn) {
				case "Buscar":				
					marca = md.read(marca);
					break;
				case "Inserir":
					saida = md.create(marca);
					marca = new Marca();
					break;
				case "Atualizar":
					saida = md.update(marca);
					marca = new Marca();
					break;
				case "Excluir":
					saida = md.delete(marca);
					marca = new Marca();
					break;
				default:
					lista = md.search();
					break;
			}
		} catch (IOException e) {
			if(e.getMessage().equals("texto nao foi digitado")) {
				erro = "Digite o nome da Marca";
			}
			
			if(e.getMessage().equals("numero nao foi digitado")) {
				erro = "Digite o id da Marca";
			}
		}catch (Exception e) {
			erro = e.getMessage();
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher("marcas.jsp");
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("marca", marca);
			request.setAttribute("lista", lista);
			rd.forward(request, response);
		}
	}
	
	private Marca validarMarca(String id,String nome,String botao) throws IOException,Exception{
		Marca marca = new Marca();
		switch (botao) {
		case "Buscar":
			marca.setId(obterInteiro(id));
			break;
		case "Inserir":
			marca.setId(obterInteiro(id));
			marca.setNome(obterString(nome));
			break;
		case "Atualizar":
			marca.setId(obterInteiro(id));
			marca.setNome(obterString(nome));
			break;
		case "Excluir":
			marca.setId(obterInteiro(id));
			break;
		default:
			break;
		}
		return marca;
	}
}
