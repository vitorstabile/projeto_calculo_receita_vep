package model.dao;

import java.util.List;

import model.entities.Ingrediente;

public interface IngredienteDao {

	void insert(Ingrediente obj);
	void update(Ingrediente obj);
	void deleteById(Integer id_MP_Receita);
	Ingrediente findById(Integer id_MP_Receita);
	List<Ingrediente> findAll();
	
}
