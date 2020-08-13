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
import model.entities.MP;
import model.entities.Receita;

public class IngredienteDaoJDBC implements IngredienteDao {

	private Connection conn;

	public IngredienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Ingrediente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO calculo_receita.ingrediente "
					+ "(idMP, idReceita, custoMP, qtIngrediente, custoIngrediente, porcenIngrediente) "
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

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
					obj.setId_MP_Receita_PK(id);
					;
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE calculo_receita.ingrediente " + "SET "
					+ "idMP = ?, idReceita = ?, custoMP = ?, "
					+ "qtIngrediente = ?, custoIngrediente = ?, porcenIngrediente = ? " + "WHERE id_MP_Receita_PK = ?");

			st.setInt(1, obj.getMP().getId());
			st.setInt(2, obj.getReceita().getIdReceita());
			st.setDouble(3, obj.getCustoMP());
			st.setDouble(4, obj.getQtIngrediente());
			st.setDouble(5, obj.getCustoIngrediente());
			st.setDouble(6, obj.getPorcenIngrediente());
			st.setDouble(7, obj.getId_MP_Receita_PK());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id_MP_Receita_PK) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM calculo_receita.ingrediente WHERE id_MP_Receita_PK = ?");

			st.setInt(1, id_MP_Receita_PK);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}


	}

	@Override
	public Ingrediente findById(Integer id_MP_Receita_PK) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT calculo_receita.mp.idMP AS 'id MP', " + "calculo_receita.mp.codigoMP AS 'Código MP', "
							+ "calculo_receita.mp.descricaoMP AS 'Descrição MP', "
							+ "calculo_receita.mp.custoMP AS 'Custo da MP Atual', "
							+ "calculo_receita.receita.idReceita AS 'id da Receita', "
							+ "calculo_receita.receita.descricaoReceita AS 'Descriçao Receita', "
							+ "calculo_receita.receita.rendLiqReceita AS 'Rendimento Liquido', "
							+ "calculo_receita.receita.gramaturaReceita AS 'Gramatura', "
							+ "calculo_receita.receita.rendBrutoReceita AS 'Rendimento Bruto Receita', "
							+ "calculo_receita.receita.perdaReceita AS 'Perda Receita', "
							+ "calculo_receita.receita.custoReceita AS 'Custo Receita', "
							+ "calculo_receita.ingrediente.id_MP_Receita_PK AS 'id Ingrediente', "
							+ "calculo_receita.ingrediente.custoMP AS 'Custo da MP no Ingrediente', "
							+ "calculo_receita.ingrediente.qtIngrediente AS 'Quantidade', "
							+ "calculo_receita.ingrediente.custoIngrediente AS 'Custo do Ingrediente', "
							+ "calculo_receita.ingrediente.porcenIngrediente AS 'Porcentagem' "
							+ "FROM calculo_receita.ingrediente " + "INNER JOIN calculo_receita.MP "
							+ "ON calculo_receita.ingrediente.idMP = calculo_receita.mp.idMP "
							+ "INNER JOIN calculo_receita.receita "
							+ "ON calculo_receita.ingrediente.idReceita = calculo_receita.receita.idReceita "
							+ "WHERE calculo_receita.ingrediente.id_MP_Receita_PK = ?;");

			st.setInt(1, id_MP_Receita_PK);
			rs = st.executeQuery();
			if (rs.next()) {
				MP mp = instantiateMP(rs);
				Receita receita = instantiateReceita(rs);
				Ingrediente ingrediente = instantiateIngrediente(rs, mp, receita);
				return ingrediente;
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
	public List<Ingrediente> findAll() {
		// TODO Auto-generated method stub
		return null;
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
		receita.setRendBrutoReceita(rs.getDouble("Rendimento Bruto Receita"));
		receita.setPerdaReceita(rs.getDouble("Perda Receita"));
		receita.setCustoReceita(rs.getDouble("Custo Receita"));
		return receita;
	}

	private Ingrediente instantiateIngrediente(ResultSet rs, MP mp, Receita receita) throws SQLException {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setMP(mp);
		ingrediente.setReceita(receita);
		ingrediente.setId_MP_Receita_PK(rs.getInt("id Ingrediente"));
		ingrediente.setCustoMP(rs.getDouble("Custo da MP no Ingrediente"));
		ingrediente.setQtIngrediente(rs.getDouble("Quantidade"));
		ingrediente.setCustoIngrediente(rs.getDouble("Custo do Ingrediente"));
		ingrediente.setPorcenIngrediente(rs.getDouble("Porcentagem"));
		return ingrediente;
	}

}
