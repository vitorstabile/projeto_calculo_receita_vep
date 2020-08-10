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
							"INSERT INTO calculo_receita.receita "
							+ "(descricaoReceita, rendLiqReceita, gramaturaReceita) " 
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
							"UPDATE calculo_receita.receita " 
							+ "SET "
							+ "descricaoReceita = ?, rendLiqReceita=?, gramaturaReceita=? " 
							+ "WHERE idReceita = ?");

			st.setString(1, obj.getDescricaoReceita());
			st.setDouble(2, obj.getRendLiqReceita());
			st.setDouble(3, obj.getGramaturaReceita());
			st.setInt(4, obj.getIdReceita());
			obj.setRendBrutoReceita();
			obj.setPerdaReceita();
			obj.setCustoReceita();
			obj.setPorcenIngrediente();

			st.executeUpdate();
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT calculo_receita.receita.idReceita AS 'id da Receita', "
							+ "calculo_receita.receita.descricaoReceita AS 'Descri�ao Receita', "
							+ "calculo_receita.receita.rendLiqReceita AS 'Rendimento Liquido', "
							+ "calculo_receita.receita.gramaturaReceita AS 'Gramatura' "
							+ "FROM calculo_receita.receita "
							+ "WHERE calculo_receita.receita.idReceita =?");

			st.setInt(1, idReceita);
			rs = st.executeQuery();
			if (rs.next()) {
				Receita receita = instantiateReceita(rs);

				return receita;
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
	public List<Receita> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Receita> findByMP(MP materiaPrima) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT calculo_receita.mp.idMP AS 'id MP', " 
							+ "calculo_receita.mp.codigoMP AS 'C�digo MP', "
							+ "calculo_receita.mp.descricaoMP AS 'Descri��o MP', "
							+ "calculo_receita.mp.custoMP AS 'Custo da MP Atual', "
							+ "calculo_receita.receita.idReceita AS 'id da Receita', "
							+ "calculo_receita.receita.descricaoReceita AS 'Descri�ao Receita', "
							+ "calculo_receita.receita.rendLiqReceita AS 'Rendimento Liquido', "
							+ "calculo_receita.receita.gramaturaReceita AS 'Gramatura' "
							+ "FROM calculo_receita.ingrediente " 
							+ "INNER JOIN calculo_receita.MP "
							+ "ON calculo_receita.ingrediente.idMP = calculo_receita.mp.idMP "
							+ "INNER JOIN calculo_receita.receita "
							+ "ON calculo_receita.ingrediente.idReceita = calculo_receita.receita.idReceita "
							+ "WHERE calculo_receita.mp.idMP = ? " 
							+ "ORDER BY calculo_receita.mp.descricaoMP;");

			st.setInt(1, materiaPrima.getId());

			rs = st.executeQuery();

			List<Receita> list = new ArrayList<>();
			Map<Integer, MP> map = new HashMap<>();

			while (rs.next()) {

				MP mp = new MP();

				mp = map.get(rs.getInt("id MP"));

				if (mp == null) {
					mp = instantiateMP(rs);
					map.put(rs.getInt("id MP"), mp);
				}
				Receita receita = instantiateReceita(rs);
				list.add(receita);
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
		mp.setCodigoMP(rs.getString("C�digo MP"));
		mp.setDescricaoMP(rs.getString("Descri��o MP"));
		mp.setCustoMP(rs.getDouble("Custo da MP Atual"));
		return mp;
	}
	
	private Receita instantiateReceita(ResultSet rs) throws SQLException {
		Receita receita = new Receita();
		receita.setIdReceita(rs.getInt("id da Receita"));
		receita.setDescricaoReceita(rs.getString("Descri�ao Receita"));
		receita.setRendLiqReceita(rs.getDouble("Rendimento Liquido"));
		receita.setGramaturaReceita(rs.getDouble("Gramatura"));
		receita.setRendBrutoReceita();
		receita.setPerdaReceita();
		receita.setCustoReceita();
		receita.setPorcenIngrediente();
		return receita;
	}

}
