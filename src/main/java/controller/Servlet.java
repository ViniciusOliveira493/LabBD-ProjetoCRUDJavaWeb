package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;

public abstract class Servlet extends HttpServlet {
	
	protected int obterInteiro(String num) throws IOException,Exception{
		if(num.equals("") || num.equals(null)) {
			throw new IOException("numero nao foi digitado");
		}else {
			try {
				return Integer.parseInt(num);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}			
		}
	}
	
	protected String obterString(String texto) throws IOException {
		if(texto.equals("") || texto.equals(null)) {
			throw new IOException("texto nao foi digitado");
		}else {
			return texto;
		}
	}
	
	protected double obterDouble(String num) throws IOException,Exception{
		if(num.equals("") || num.equals(null)) {
			throw new IOException("numero nao foi digitado");
		}else {
			try {
				return Double.parseDouble(num);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}			
		}
	}
}
