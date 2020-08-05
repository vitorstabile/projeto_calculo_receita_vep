package model.dao;

import java.util.List;

import model.entities.MP;
import model.entities.Receita;

public interface ReceitaDao {
	
	void insert(Receita obj);
	void update(Receita obj);
	void deleteById(Integer idReceita);
	Receita findById(Integer idReceita);
	List<Receita> findAll();
	List<Receita> findByMP(MP mp);
	
}
