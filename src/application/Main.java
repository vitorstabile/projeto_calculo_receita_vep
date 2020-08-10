package application;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.MPDao;
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
		
		Receita receita = new Receita();
		
		receita.setIdReceita(1);
		
		List<MP> list = mpDao.findByReceita(receita);
		for(MP obj : list) {
			System.out.println(obj);
		}
		
	}
}
