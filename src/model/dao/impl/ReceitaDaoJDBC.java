package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ReceitaDao;
import model.entities.MP;
import model.entities.Receita;

public class ReceitaDaoJDBC implements ReceitaDao {
	
	private Connection conn;
	
	public ReceitaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Receita obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO calculo_receita.receita " + "(descricaoReceita, rendLiqReceita, gramaturaReceita) " 
					+ "VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDescricaoReceita());
			st.setDouble(2, obj.getRendLiqReceita());
			st.setDouble(3, obj.getGramaturaReceita());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdReceita(id);
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
	public void update(Receita obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer idReceita) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM calculo_receita.receita WHERE idReceita = ?");

			st.setInt(1, idReceita);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Receita findById(Integer idReceita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receita> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receita> findByMP(MP mp) {
		// TODO Auto-generated method stub
		return null;
	}

}
