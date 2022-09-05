package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Marca;
import model.Produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Conexao;
import DAO.MarcaDAO;
import DAO.ProdutoDAO;

public class ProdutoServlet extends Servlet {
	private static final long serialVersionUID = 1L;
       
    public ProdutoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Conexao conn = new Conexao();
		MarcaDAO dao = new MarcaDAO(conn);
		List<Marca> lista = new ArrayList<Marca>();
		String erro = "";
		try {
			lista = dao.search();
		} catch (SQLException e) {
			erro = "SQL ERROR: 0201 - "+e.getMessage();
		} catch (Exception e) {
			erro = "ERROR: 0201 - "+e.getMessage();
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");
			request.setAttribute("erro", erro);
			request.setAttribute("lista", lista);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("txtID");
		String nome = request.getParameter("txtNome");
		String valor = request.getParameter("txtPreco");
		String marca = request.getParameter("cbMarca");
		String btn = request.getParameter("btn");		
		
		String erro = "";
		String saida = "";
		
		Produto produto = new Produto();
		Marca m = new Marca();
		List<Produto> produtos = new ArrayList<Produto>();
		List<Marca> marcas = new ArrayList<Marca>();
		
		
		Conexao conn = new Conexao();
		ProdutoDAO pDao = new ProdutoDAO(conn);
		MarcaDAO mDao = new MarcaDAO(conn);
		try {			
			produto = validarProduto(id, nome, valor, marca, btn);
			switch (btn) {
			case "Buscar":				
				produto = pDao.read(produto);
				m = mDao.read(produto.getMarca());
				break;
			case "Inserir":
				saida = pDao.create(produto);
				produto = new Produto();
				break;
			case "Atualizar":
				saida = pDao.update(produto);
				produto = new Produto();
				break;
			case "Excluir":
				saida = pDao.delete(produto);
				produto = new Produto();
				break;
			default:
				produtos = pDao.search();
				break;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				marcas = mDao.search();
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("produtos", produtos);
			request.setAttribute("produto", produto);
			request.setAttribute("marcas", marcas);
			request.setAttribute("marca", m);
			rd.forward(request, response);
		}
	}
	
	private Produto validarProduto(String id,String nome,String valor,String marca,String botao) throws IOException, Exception {
		Produto p = new Produto();
		Marca m = new Marca();
		switch (botao) {
		case "Buscar":
			p.setId(obterInteiro(id));
			break;
		case "Inserir":
			p.setId(obterInteiro(id));
			p.setNome(obterString(nome));
			p.setPreco(obterDouble(valor));
			m.setId(obterInteiro(marca));
			p.setMarca(m);
			break;
		case "Atualizar":
			p.setId(obterInteiro(id));
			p.setNome(obterString(nome));
			p.setPreco(obterDouble(valor));
			m.setId(obterInteiro(marca));
			p.setMarca(m);
			break;
		case "Excluir":
			p.setId(obterInteiro(id));
			break;
		default:
			break;
		}
		return p;
	}

}
