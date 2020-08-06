package model.dao;

import db.DB;
import model.dao.impl.IngredienteDaoJDBC;
import model.dao.impl.MPDaoJDBC;
import model.dao.impl.ReceitaDaoJDBC;

public class DaoFactory {
	
	public static MPDao createMPDao() {
		return new MPDaoJDBC(DB.getConnection());
	}
	
	public static ReceitaDao createReceitaDao() {
		return new ReceitaDaoJDBC(DB.getConnection());
	}
	
	public static IngredienteDao createIngredienteDao() {
		return new IngredienteDaoJDBC(DB.getConnection());
	}

}
