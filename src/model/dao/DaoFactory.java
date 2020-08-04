package model.dao;

import db.DB;
import model.dao.impl.MPDaoJDBC;

public class DaoFactory {
	
	public static MPDao createMPDao() {
		return new MPDaoJDBC(DB.getConnection());
	}

}
