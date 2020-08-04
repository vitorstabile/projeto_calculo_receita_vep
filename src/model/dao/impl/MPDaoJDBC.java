package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MP obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MP findByCodigo(String codigoMP) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM calculo_receita.mp WHERE codigoMP =?");
			
			st.setString(1, codigoMP);
			rs = st.executeQuery();
			if(rs.next()) {
				MP mp = new MP();
				mp.setId(rs.getInt("idMP"));
				mp.setCodigoMP(rs.getString("codigoMP"));
				mp.setDescricaoMP(rs.getString("descricaoMP"));
				mp.setCustoMP(rs.getDouble("custoMP"));
				return mp;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
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
