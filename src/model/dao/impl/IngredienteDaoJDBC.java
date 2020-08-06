package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.IngredienteDao;
import model.entities.Ingrediente;

public class IngredienteDaoJDBC implements IngredienteDao {
	
	
	private Connection conn;
	
	public IngredienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Ingrediente obj) {
		// TODO Auto-generated method stub
		
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
