package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.MPDao;
import model.entities.MP;
import model.entities.Receita;

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
							"INSERT INTO calculo_receita.mp " 
							+ "(codigoMP, descricaoMP, custoMP) " 
							+ "VALUES (?, ?, ?)",
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
	public void update(MP obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
							"UPDATE calculo_receita.mp " 
							+ "SET "
							+ "codigoMP = ?, descricaoMP=?, custoMP=? " 
							+ "WHERE idMP = ?");

			st.setString(1, obj.getCodigoMP());
			st.setString(2, obj.getDescricaoMP());
			st.setDouble(3, obj.getCustoMP());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer idMP) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM calculo_receita.mp WHERE idMP = ?");

			st.setInt(1, idMP);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public MP findByCodigo(String codigoMP) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT calculo_receita.mp.idMP AS 'id MP', " 
							+ "calculo_receita.mp.codigoMP AS 'Código MP', "
							+ "calculo_receita.mp.descricaoMP AS 'Descrição MP', "
							+ "calculo_receita.mp.custoMP AS 'Custo da MP Atual' "
							+ "FROM calculo_receita.mp "
							+ "WHERE calculo_receita.mp.codigoMP = ?");
					
			st.setString(1, codigoMP);
			rs = st.executeQuery();
			if (rs.next()) {
				MP mp = instantiateMP(rs);
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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT calculo_receita.mp.idMP AS 'id MP', " 
							+ "calculo_receita.mp.codigoMP AS 'Código MP', "
							+ "calculo_receita.mp.descricaoMP AS 'Descrição MP', "
							+ "calculo_receita.mp.custoMP AS 'Custo da MP Atual' "
							+ "FROM calculo_receita.mp "
							+ "ORDER BY calculo_receita.mp.codigoMP;");

			rs = st.executeQuery();

			List<MP> list = new ArrayList<>();

			while (rs.next()) {
				
				MP mp = instantiateMP(rs);
				list.add(mp);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<MP> findByReceita(Receita receita) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT calculo_receita.mp.idMP AS 'id MP', " 
							+ "calculo_receita.mp.codigoMP AS 'Código MP', "
							+ "calculo_receita.mp.descricaoMP AS 'Descrição MP', "
							+ "calculo_receita.mp.custoMP AS 'Custo da MP Atual', "
							+ "calculo_receita.receita.idReceita AS 'id da Receita', "
							+ "calculo_receita.receita.descricaoReceita AS 'Descriçao Receita', "
							+ "calculo_receita.receita.rendLiqReceita AS 'Rendimento Liquido', "
							+ "calculo_receita.receita.gramaturaReceita AS 'Gramatura' "
							+ "FROM calculo_receita.ingrediente " 
							+ "INNER JOIN calculo_receita.MP "
							+ "ON calculo_receita.ingrediente.idMP = calculo_receita.mp.idMP "
							+ "INNER JOIN calculo_receita.receita "
							+ "ON calculo_receita.ingrediente.idReceita = calculo_receita.receita.idReceita "
							+ "WHERE calculo_receita.receita.idReceita = ? " 
							+ "ORDER BY calculo_receita.mp.descricaoMP;");

			st.setInt(1, receita.getIdReceita());

			rs = st.executeQuery();

			List<MP> list = new ArrayList<>();
			Map<Integer, Receita> map = new HashMap<>();

			while (rs.next()) {

				Receita receita2 = new Receita();

				receita2 = map.get(rs.getInt("id da Receita"));

				if (receita2 == null) {
					receita2 = instantiateReceita(rs);
					map.put(rs.getInt("id da Receita"), receita);
				}
				MP mp = instantiateMP(rs);
				list.add(mp);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private MP instantiateMP(ResultSet rs) throws SQLException {
		MP mp = new MP();
		mp.setId(rs.getInt("id MP"));
		mp.setCodigoMP(rs.getString("Código MP"));
		mp.setDescricaoMP(rs.getString("Descrição MP"));
		mp.setCustoMP(rs.getDouble("Custo da MP Atual"));
		return mp;
	}
	
	private Receita instantiateReceita(ResultSet rs) throws SQLException {
		Receita receita = new Receita();
		receita.setIdReceita(rs.getInt("id da Receita"));
		receita.setDescricaoReceita(rs.getString("Descriçao Receita"));
		receita.setRendLiqReceita(rs.getDouble("Rendimento Liquido"));
		receita.setGramaturaReceita(rs.getDouble("Gramatura"));
		receita.setRendBrutoReceita();
		receita.setPerdaReceita();
		receita.setCustoReceita();
		receita.setPorcenIngrediente();
		return receita;
	}
	
}
