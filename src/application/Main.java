package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.MPDao;
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

		MP mp1 = mpDao.findByCodigo("800001");
		MP mp2 = mpDao.findByCodigo("800005");
		MP mp3 = mpDao.findByCodigo("800002");
		MP mp4 = mpDao.findByCodigo("800010");
		MP mp5 = mpDao.findByCodigo("800011");
		MP mp6 = mpDao.findByCodigo("800015");

		System.out.println(mp1);
		System.out.println(mp2);
		System.out.println(mp3);
		System.out.println(mp4);
		System.out.println(mp5);
		System.out.println(mp6);
		
		Receita receita1 = new Receita(1, "Massa de Batata", 30.0, 15.0);
		Receita receita2 = new Receita(2, "Recheio de Carne", 10.0, 3.0);
		Receita receita3 = new Receita(3, "Empano de Farinha", 5.0, 2.0);
		
		Ingrediente ingrediente1 = new Ingrediente(mp1, receita1, mp1.getCustoMP(), 15.0);
		Ingrediente ingrediente2 = new Ingrediente(mp2, receita1, mp2.getCustoMP(), 20.0);
		Ingrediente ingrediente3 = new Ingrediente(mp2, receita2, mp2.getCustoMP(), 8.0);
		Ingrediente ingrediente4 = new Ingrediente(mp1, receita2, mp1.getCustoMP(), 5.0);
		Ingrediente ingrediente5 = new Ingrediente(mp5, receita3, mp5.getCustoMP(), 2.5);
		Ingrediente ingrediente6 = new Ingrediente(mp6, receita3, mp6.getCustoMP(), 2.5);
		
		receita1.addIngrediente(ingrediente1);
		receita1.addIngrediente(ingrediente2);
		
		receita2.addIngrediente(ingrediente3);
		receita2.addIngrediente(ingrediente4);
		
		receita3.addIngrediente(ingrediente5);
		receita3.addIngrediente(ingrediente6);
		
		System.out.println(receita1);
		System.out.println(receita2);
		System.out.println(receita3);
		
		Ingrediente ingrediente7 = new Ingrediente(mp4, receita2, mp4.getCustoMP(), 2.5);
		Ingrediente ingrediente8 = new Ingrediente(mp3, receita1, mp3.getCustoMP(), 3.0);
		
		receita2.addIngrediente(ingrediente7);
		receita1.addIngrediente(ingrediente8);
		
		System.out.println(receita1);
		System.out.println(receita2);
		
	}
}
