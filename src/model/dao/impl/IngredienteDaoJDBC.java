package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.IngredienteDao;
import model.entities.Ingrediente;

public class IngredienteDaoJDBC implements IngredienteDao {
	
	
	private Connection conn;
	
	public IngredienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Ingrediente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO calculo_receita.ingrediente " + "(idMP, idReceita, custoMP, qtIngrediente, custoIngrediente, porcenIngrediente) " 
					+ "VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getMP().getId());
			st.setInt(2, obj.getReceita().getIdReceita());
			st.setDouble(3, obj.getCustoMP());
			st.setDouble(4, obj.getQtIngrediente());
			st.setDouble(5, obj.getCustoIngrediente());
			st.setDouble(6, obj.getPorcenIngrediente());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId_MP_Receita_PK(id);;
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Ingrediente obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id_MP_Receita) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ingrediente findById(Integer id_MP_Receita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingrediente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
