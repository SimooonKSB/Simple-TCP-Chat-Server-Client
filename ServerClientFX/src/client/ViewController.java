package client;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ViewController implements Initializable {

	private Dialog<String> dialog = new Dialog<>();
	private Pane dialogPane = new Pane();
	private TextField dialogTextField = new TextField();
	private Optional<String> dialogResult;
	private Client client;
	private String message;

	@FXML
	private MenuBar menubar;
	@FXML
	private Pane pane;
	@FXML
	private Menu connectionMenu;
	@FXML
	private Menu helpMenu;
	@FXML
	private MenuItem ipconfigMenu;
	@FXML
	private MenuItem connectMenu;
	@FXML
	private MenuItem disconnectMenu;
	@FXML
	private MenuItem aboutMenu;
	@FXML
	private TextArea receiveMsgTa;
	@FXML
	private TextField sendMsgTf;
	@FXML
	private Button sendButton;

	@Override
	public void initialize(URL fxmlUrl, ResourceBundle arg1) {

		sendButton.setDefaultButton(true);

		dialog.setTitle("Enter IP and Port");
		dialog.setHeaderText("Enter the IP-adress and the port of the server.");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialogPane.getChildren().add(dialogTextField);
		dialogTextField.setPromptText("127.0.0.1:12864");
		dialogTextField.setLayoutX(85);
		dialogTextField.setLayoutY(30);

		dialog.getDialogPane().setContent(dialogPane);

		ipconfigMenu.setOnAction(event -> {
			dialogTextField.setText(Main.serverIP + ":" + Main.serverPort);
			dialogResult = dialog.showAndWait();

			dialogResult.ifPresent(str -> {
				if(!str.isBlank()) {
				String[] splittedStr = str.split(":");
				Main.serverIP = splittedStr[0];
				Main.serverPort = Integer.parseInt(splittedStr[1]);
				}
			});
		});
		
		dialog.setResultConverter(but -> {
			if (but == ButtonType.OK) {
				return dialogTextField.getText();
			}
			return "";
		});
		
		connectMenu.setOnAction(event ->{
			try {
				client = new Client(Main.serverIP, Main.serverPort, message);
				client.run();
			}catch (IOException e) {
				e.printStackTrace();
				//Add StackTrace dialog
			}
		});
		
		sendButton.setOnAction(event -> {
			if(client != null) {
			client.sendMsg(sendMsgTf.getText());
			sendMsgTf.setText("");
			}
		});
	}
}
