package gui;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import domain.Bruger;
import domain.BrugerImpl;
import exception.LoginException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;

/**
 * FXML Controller class
 *
 * @author Jonas Mørch, Juyoung Choi
 */
public class LoginController extends FSPane implements Initializable {

	@FXML
	private PasswordField password;
	@FXML
	private TextField username, ShowPassword;
	@FXML
	private CheckBox checkBox;
	private FlexturGUI flextur;
	private Stage window;

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
		DialogBox alert = new DialogBoxImpl(window);

		Bruger bruger = new BrugerImpl();
		String loginId = username.getText();
		try {
			bruger.setAndEncryptPassword(password.getText());
			String kodeord = bruger.getEncryptedKodeord();
			bruger = fsController.checkLogin(loginId, kodeord);

			if(bruger.erAktivt()){
				if(bruger.erKunde()){
					flextur.showMenuKunde();
				}else{
					flextur.showMenuAdmin();
				}
			}
		} catch (NoSuchAlgorithmException e) {
			alert.visLoginFejllDialog();
		} catch (LoginException e){
			alert.visLoginFejllDialog();
		}
		//		System.out.println(user + " har prøvet at logge ind med koden: " + pass);
		//		flextur.Login(user, pass);
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

	@Override
	public void update(Observable observable, Object tilstand) {
		// TODO Auto-generated method stub

	}
}