package model.dao;

import java.util.List;

import model.entities.MP;

public interface MPDao {

	void insert(MP obj);
	void update(MP obj);
	MP findByCodigo(String codigoMP);
	List<MP> findAll();
	
}
