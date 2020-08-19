package application;

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
		
		MP mp1 = new MP ();
		MP mp2 = new MP ();
		MP mp3 = new MP ();
		MP mp4 = new MP ();
		
		mp1 = mpDao.findByCodigo("800092");
		mp2 = mpDao.findByCodigo("800093");
		mp3 = mpDao.findByCodigo("800094");
		mp4 = mpDao.findByCodigo("800095");
		
		Receita receita4 = new Receita(null, "Receita de Teste", 5.0, 2.0);
		
		Ingrediente ingrediente1 = new Ingrediente(null, mp1, receita4, 15.0);
		Ingrediente ingrediente2 = new Ingrediente(null, mp2, receita4, 20.0);
		Ingrediente ingrediente3 = new Ingrediente(null, mp3, receita4, 8.0);
		Ingrediente ingrediente4 = new Ingrediente(null, mp4, receita4, 5.0);	
		
		receita4.addIngrediente(ingrediente1);
		
		System.out.println(receita4);
		
		receita4.addIngrediente(ingrediente2);
		
		System.out.println(receita4);
		
		receita4.addIngrediente(ingrediente3);
		
		System.out.println(receita4);
		
		receita4.addIngrediente(ingrediente4);
		
		System.out.println(receita4);
		
		receita4.removeIngrediente(ingrediente1);
		
		System.out.println(receita4);
	}
}
