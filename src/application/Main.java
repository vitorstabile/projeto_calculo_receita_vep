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
		
		MP mp10 = new MP (null, "123456", "teste", 25.5);
		
		mpDao.insert(mp10);
		
		MP mp11 = new MP();
		
		mp11 = mpDao.findByCodigo("123456");
		
		mp11.setDescricaoMP("OutroTeste");
		mp11.setCustoMP(30.0);
		
		mpDao.update(mp11);
		
	}
}
