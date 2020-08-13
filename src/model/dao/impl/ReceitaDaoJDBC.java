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
import model.entities.Ingrediente;
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
			st = conn.prepareStatement("INSERT INTO calculo_receita.receita "
					+ "(descricaoReceita, rendLiqReceita, gramaturaReceita, rendBrutoReceita, perdaReceita, custoReceita) "
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDescricaoReceita());
			st.setDouble(2, obj.getRendLiqReceita());
			st.setDouble(3, obj.getGramaturaReceita());
			st.setDouble(4, obj.getRendBrutoReceita());
			st.setDouble(5, obj.getPerdaReceita());
			st.setDouble(6, obj.getCustoReceita());

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
			st = conn.prepareStatement("UPDATE calculo_receita.receita " + "SET "
					+ "descricaoReceita = ?, rendLiqReceita=?, " + "gramaturaReceita=?, rendBrutoReceita =?, "
					+ "perdaReceita=?, custoReceita=?" + "WHERE idReceita = ?");

			st.setString(1, obj.getDescricaoReceita());
			st.setDouble(2, obj.getRendLiqReceita());
			st.setDouble(3, obj.getGramaturaReceita());
			st.setDouble(4, obj.getRendBrutoReceita());
			st.setDouble(5, obj.getPerdaReceita());
			st.setDouble(6, obj.getCustoReceita());
			st.setInt(7, obj.getIdReceita());

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
							+ "WHERE calculo_receita.receita.idReceita = ?;");

			st.setInt(1, idReceita);
			rs = st.executeQuery();

			Receita receita = new Receita();

			List<Ingrediente> listIngrediente = new ArrayList<>();

			while (rs.next()) {

				MP mp = instantiateMP(rs);
				receita = instantiateReceita(rs);
				Ingrediente ingrediente = instantiateIngrediente(rs, mp, receita);
				listIngrediente.add(ingrediente);
			}
			for (Ingrediente ingrediente : listIngrediente) {
				receita.addIngrediente(ingrediente);
			}
			return receita;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Receita> findAll() {
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
							+ "ON calculo_receita.ingrediente.idReceita = calculo_receita.receita.idReceita ");

			rs = st.executeQuery();

			List<Receita> listReceita = new ArrayList<>();

			List<Ingrediente> listIngrediente = new ArrayList<>();
			
			Map<Integer, MP> mapMP = new HashMap<>();
			
			Map<Integer, Receita> mapReceita = new HashMap<>();

			while (rs.next()) {

				MP mp = new MP();

				mp = mapMP.get(rs.getInt("id MP"));

				if (mp == null) {
					mp = instantiateMP(rs);
					mapMP.put(rs.getInt("id MP"), mp);
				}
				
				Receita receita = new Receita();

				receita = mapReceita.get(rs.getInt("id da Receita"));

				if (receita == null) {
					receita = instantiateReceita(rs);
					mapReceita.put(rs.getInt("id da Receita"), receita);
					listReceita.add(receita);
				}
				Ingrediente ingrediente = instantiateIngrediente(rs, mp, receita);
				listIngrediente.add(ingrediente);
			}
			for(Ingrediente ingrediente : listIngrediente) {
				for(Receita receita : listReceita) {
					if(receita.getIdReceita() == ingrediente.getReceita().getIdReceita()) {
						receita.addIngrediente(ingrediente);
					}
				}
			}
			return listReceita;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Receita> findByMP(MP materiaPrima) {
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
							+ "WHERE calculo_receita.mp.codigoMP = ? "
							+ "ORDER BY calculo_receita.receita.descricaoReceita;");

			st.setString(1, materiaPrima.getCodigoMP());

			rs = st.executeQuery();

			List<Receita> listReceita = new ArrayList<>();

			List<Ingrediente> listIngrediente = new ArrayList<>();
			
			Map<Integer, MP> mapMP = new HashMap<>();
			
			Map<Integer, Receita> mapReceita = new HashMap<>();

			while (rs.next()) {

				MP mp = new MP();

				mp = mapMP.get(rs.getInt("id MP"));

				if (mp == null) {
					mp = instantiateMP(rs);
					mapMP.put(rs.getInt("id MP"), mp);
				}
				
				Receita receita = new Receita();

				receita = mapReceita.get(rs.getInt("id da Receita"));

				if (receita == null) {
					receita = instantiateReceita(rs);
					mapReceita.put(rs.getInt("id da Receita"), receita);
					listReceita.add(receita);
				}
				Ingrediente ingrediente = instantiateIngrediente(rs, mp, receita);
				listIngrediente.add(ingrediente);
			}
			for(Ingrediente ingrediente : listIngrediente) {
				for(Receita receita : listReceita) {
					if(receita.getIdReceita() == ingrediente.getReceita().getIdReceita()) {
						receita.addIngrediente(ingrediente);
					}
				}
			}
			return listReceita;
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
