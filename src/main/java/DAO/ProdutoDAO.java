package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Marca;
import model.Produto;

public class ProdutoDAO implements InterfaceDAO<Produto>{
	Conexao conn = null;
	
	public ProdutoDAO(Conexao conn) {
		this.conn = conn;
	}
	@Override
	public String create(Produto obj) throws SQLException, Exception {
		String query = "INSERT INTO tbProduto(id,nome,preco,idMarca) VALUES (?,?,?,?)";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			pstm.setString(2, obj.getNome());
			pstm.setDouble(3, obj.getPreco());
			pstm.setInt(4, obj.getMarca().getId());
			pstm.execute();
			pstm.close();
			return "Produto cadastrado com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public String update(Produto obj) throws SQLException, Exception {
		String query = "UPDATE tbProduto SET nome = ?,preco = ?,idMarca= ? WHERE id = ?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);			
			pstm.setString(1, obj.getNome());
			pstm.setDouble(2, obj.getPreco());
			pstm.setInt(3, obj.getMarca().getId());
			pstm.setInt(4, obj.getId());
			pstm.execute();
			pstm.close();
			return "Produto atualizado com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public String delete(Produto obj) throws SQLException, Exception {
		String query = "DELETE FROM tbProduto WHERE id = ?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			pstm.execute();
			pstm.close();
			return "Produto excluído com sucesso";			
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
	}

	@Override
	public Produto read(Produto obj) throws SQLException, Exception {
		String query = 	"SELECT"
							+ " p.id AS pid,p.nome AS pnome,p.preco AS ppreco,p.idMarca AS pidMarca,"
							+ " m.id AS mid,m.nome AS mnome"
						+ " FROM tbProduto AS p"
							+ " INNER JOIN tbMarca AS m"
							+ " ON p.idMarca = m.id"
						+ " WHERE p.id = ?";
		Connection cn = null;
		try {
			cn = conn.getConexao();
			PreparedStatement pstm = cn.prepareStatement(query);
			pstm.setInt(1, obj.getId());
			ResultSet rs = pstm.executeQuery();			
			while(rs.next()) {			
				obj = new Produto();
				obj.setId(rs.getInt("pid"));
				obj.setNome(rs.getString("pnome"));
				obj.setPreco(rs.getDouble("ppreco"));
				Marca m = new Marca();
				m.setId(rs.getInt("mid"));
				m.setNome(rs.getString("mnome"));
				obj.setMarca(m);
			}
			pstm.close(); 
			rs.close();
		} catch (Exception e) {			
			throw e;
		}finally {
			conn.close(cn);
		}
		return obj;
	}

	@Override
	public List<Produto> search() throws SQLException, Exception {
		List<Produto> lista = new ArrayList<Produto>();
		String query = 	"SELECT"
							+ " p.id AS pid,p.nome AS pnome,p.preco AS ppreco,p.idMarca AS pidMarca,"
							+ " m.id AS mid,m.nome AS mnome"
						+ " FROM tbProduto AS p"
							+ " INNER JOIN tbMarca AS m"
							+ " ON p.idMarca = m.id";
			Connection cn = null;
			try {
				cn = conn.getConexao();
				PreparedStatement pstm = cn.prepareStatement(query);
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {		
					Produto obj = new Produto();
					obj = new Produto();
					obj.setId(rs.getInt("pid"));
					obj.setNome(rs.getString("pnome"));
					obj.setPreco(rs.getDouble("ppreco"));
					Marca m = new Marca();
					m.setId(rs.getInt("mid"));
					m.setNome(rs.getString("mnome"));
					obj.setMarca(m);
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
