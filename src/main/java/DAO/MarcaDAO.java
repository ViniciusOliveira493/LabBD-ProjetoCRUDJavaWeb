package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Marca;

public class MarcaDAO implements InterfaceDAO<Marca>{
	Conexao conn = null;
	
	public MarcaDAO(Conexao conn) {
		this.conn = conn;
	}

	@Override
	public String create(Marca obj) throws SQLException, Exception {
		String query = "INSERT INTO tbMarca(id,nome) VALUES (?,?)";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			pstm.setString(2, obj.getNome());
			pstm.execute();
			pstm.close();
			return "Marca cadastrada com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public String update(Marca obj) throws SQLException, Exception {
		String query = "UPDATE tbMarca SET nome = ? WHERE id = ?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(2, obj.getId());
			pstm.setString(1, obj.getNome());
			pstm.execute();
			pstm.close();
			return "Marca atualizada com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public String delete(Marca obj) throws SQLException, Exception {
		String query = "DELETE FROM tbMarca WHERE id = ?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			pstm.execute();
			pstm.close();
			return "Marca excluída com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public Marca read(Marca obj) throws SQLException, Exception {
		String query = "SELECT id,nome FROM tbMarca WHERE id=?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				obj = new Marca();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));				
			}	
			pstm.close();
			rs.close();
			return obj;
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public List<Marca> search() throws SQLException, Exception {
		List<Marca> lista = new ArrayList<Marca>();
		String query = "SELECT id,nome FROM tbMarca";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				Marca obj = new Marca();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				
				lista.add(obj);
			}
			pstm.close();
			rs.close();
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
		return lista;
	}
}
