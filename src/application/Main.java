package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.IngredienteDao;
import model.dao.MPDao;
import model.dao.ReceitaDao;
import model.entities.Ingrediente;
import model.entities.MP;
import model.entities.Receita;
import model.services.ReceitaService;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/* launch(args); */
		/* Implementar primeiro o banco */
		
		MPDao mpDao = DaoFactory.createMPDao();
		
		ReceitaDao receitaDao = DaoFactory.createReceitaDao();

		IngredienteDao ingredienteDao = DaoFactory.createIngredienteDao();
			
		MP mp1 = mpDao.findByCodigo("800005");
		MP mp2 = mpDao.findByCodigo("800007");
		MP mp3 = mpDao.findByCodigo("800010");
		MP mp4 = mpDao.findByCodigo("800012");
		MP mp5 = mpDao.findByCodigo("800015");
		MP mp6 = mpDao.findByCodigo("800020");
		
		Receita receita1 = new Receita(null, "Receita 1", 20.0, 3.5);
		
		Ingrediente ingrediente1 = new Ingrediente(null, mp1, receita1, 5.0);
		Ingrediente ingrediente2 = new Ingrediente(null, mp2, receita1, 10.0);
		Ingrediente ingrediente3 = new Ingrediente(null, mp3, receita1, 15.0);
		Ingrediente ingrediente4 = new Ingrediente(null, mp4, receita1, 20.0);
		Ingrediente ingrediente5 = new Ingrediente(null, mp5, receita1, 25.0);
		Ingrediente ingrediente6 = new Ingrediente(null, mp6, receita1, 30.0);
		
		ReceitaService receitaService = new ReceitaService();
		
		receitaService.updateReceitaAdd(receita1, ingrediente1);
		receitaService.updateReceitaAdd(receita1, ingrediente2);
		receitaService.updateReceitaAdd(receita1, ingrediente3);
		receitaService.updateReceitaAdd(receita1, ingrediente4);
		receitaService.updateReceitaAdd(receita1, ingrediente5);
		receitaService.updateReceitaAdd(receita1, ingrediente6);
		
		ingredienteDao.deleteById(4);
		
	}
}
