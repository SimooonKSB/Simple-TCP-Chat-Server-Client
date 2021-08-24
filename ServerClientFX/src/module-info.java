module ServerClientFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens client to javafx.graphics, javafx.fxml;
}
