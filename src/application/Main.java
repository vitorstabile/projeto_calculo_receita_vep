package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.MPDao;
import model.entities.MP;

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
		MP mp3 = mpDao.findByCodigo("800004");

		System.out.println(mp1);
		System.out.println(mp2);
		System.out.println(mp3);
		
		
	}
}
