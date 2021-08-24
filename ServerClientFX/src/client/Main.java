package client;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static String serverIP = "localhost";
	public static int serverPort = 12864;
	
	@Override
	public void start(Stage win) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/client/View.fxml"));
		win.setScene(new Scene(root));
		win.getScene().getStylesheets().add(getClass().getResource("View.css").toString());
		win.setTitle("Chatsystem - Client");
		win.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
