package gui;

import java.net.URL;
import java.util.ResourceBundle;
import exception.LoginException;
import exception.MissingOplysningExcpetion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 * GUI controller klasse til Login
 *
 * @author Jonas Mørch & Juyoung Choi
 */
public class LoginController extends FSPane implements Initializable {

	@FXML
	private PasswordField password;
	@FXML
	private TextField username, ShowPassword;
	@FXML
	private CheckBox checkBox;
	private FlexturGUI flexturGUI;
	private Stage window;

	@FXML
	private void handleOpretProfil(ActionEvent event) {
		flexturGUI.showOpretProfil();
	}

	@FXML
	private void handleKunde(ActionEvent event) {
		flexturGUI.showMenuKunde();
	}

	@FXML
	private void handleAdmin(ActionEvent event) {
		flexturGUI.showMenuAdmin();
	}

	@FXML
	private void handleLogin(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);

		try {
			fsController.angivLoginOplysninger(username.getText(), password.getText());

		} catch (LoginException e ) {
			alert.visLoginFejllDialog();
		} catch (MissingOplysningExcpetion e){
			alert.visOplysningManglerAdvarselDialog();
		}
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
		this.flexturGUI = flextur;

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {

		if (tilstand.equals(Tilstand.LOGIN_KUNDE)) {
			flexturGUI.showMenuKunde();
		}else if (tilstand.equals(Tilstand.LOGIN_BM)) {
			flexturGUI.showMenuAdmin();
		}

	}

	@Override
	void postInitialize() {
		// TODO Auto-generated method stub

	}
}