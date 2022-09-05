package model;

public class Produto {
	private int id;	
	private String nome;
	private double preco;		
	private Marca marca;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}	
	@Override
	public String toString() {
		return "Produto: "+id+" | "+nome+" | "+preco+" | "+marca;
	}
}
