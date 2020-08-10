package model.dao;

import java.util.List;

import model.entities.MP;
import model.entities.Receita;

public interface MPDao {

	void insert(MP obj);
	void update(MP obj);
	void deleteById(Integer idMP);
	MP findByCodigo(String codigoMP);
	List<MP> findAll();
	List<MP> findByReceita(Receita receita);
}
