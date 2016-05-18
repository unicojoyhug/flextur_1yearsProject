package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shadowsilver
 */
public class LoginController implements Initializable {

	@FXML
	private PasswordField password;
	@FXML
	private TextField username, ShowPassword;
	@FXML
	private CheckBox checkBox;
	private FlexturGUI flextur;

	@FXML
	private void handleOpretProfil(ActionEvent event) {
		flextur.showOpretProfil();
	}

	@FXML
	private void handleKunde(ActionEvent event) {
		flextur.showMenuKunde();
	}

	@FXML
	private void handleAdmin(ActionEvent event) {
		flextur.showMenuAdmin();
	}

	@FXML
	private void handleLogin(ActionEvent event) {
		String user = username.getText();
		String pass = password.getText();
		System.out.println(user + " har prøvet at logge ind med koden: " + pass);
		flextur.Login(user, pass);
	}

	private void ShowPassword() {
		ShowPassword.managedProperty().bind(checkBox.selectedProperty());
		ShowPassword.visibleProperty().bind(checkBox.selectedProperty());
		password.managedProperty().bind(checkBox.selectedProperty().not());
		password.visibleProperty().bind(checkBox.selectedProperty().not());
		ShowPassword.textProperty().bindBidirectional(password.textProperty());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ShowPassword();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;

	}
}