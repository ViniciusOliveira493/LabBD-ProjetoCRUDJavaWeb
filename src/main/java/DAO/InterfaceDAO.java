package DAO;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<T> {	
	public String create(T obj) throws SQLException,Exception;
	public String update(T obj) throws SQLException,Exception;
	public String delete(T obj) throws SQLException,Exception;
	public T read(T obj) throws SQLException,Exception;
	public List<T> search() throws SQLException,Exception;
}
