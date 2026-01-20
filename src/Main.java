import client.Pond;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pond root = new Pond(100);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
//		primaryStage.setWidth(600);
//		primaryStage.setHeight(500);
		primaryStage.setTitle("Pond Game");
		primaryStage.show();
	}

}
