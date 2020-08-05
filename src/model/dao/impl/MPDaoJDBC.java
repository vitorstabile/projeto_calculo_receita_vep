package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MPDao;
import model.entities.MP;

public class MPDaoJDBC implements MPDao {

	private Connection conn;

	public MPDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(MP obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO calculo_receita.mp " + "(codigoMP, descricaoMP, custoMP) " + "VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getCodigoMP());
			st.setString(2, obj.getDescricaoMP());
			st.setDouble(3, obj.getCustoMP());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} 
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(MP obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer idMP) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM calculo_receita.mp WHERE idMP = ?");
			
			st.setInt(1, idMP);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public MP findByCodigo(String codigoMP) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM calculo_receita.mp WHERE codigoMP =?");

			st.setString(1, codigoMP);
			rs = st.executeQuery();
			if (rs.next()) {
				MP mp = new MP();
				mp.setId(rs.getInt("idMP"));
				mp.setCodigoMP(rs.getString("codigoMP"));
				mp.setDescricaoMP(rs.getString("descricaoMP"));
				mp.setCustoMP(rs.getDouble("custoMP"));
				return mp;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<MP> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
