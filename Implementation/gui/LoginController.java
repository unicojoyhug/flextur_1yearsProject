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
import logic.FSControllerImpl;
import logic.Observable;
import logic.Tilstand;

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
				fsController.angivLoginOplysninger(loginId, kodeord);
				
			} catch (NoSuchAlgorithmException e) {
				alert.visLoginFejllDialog();

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
		this.flextur = flextur;

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {

		DialogBox alert = new DialogBoxImpl(window);

		// TODO Auto-generated method stub
		//		if(observable instanceof FSControllerImpl){
		//			FSControllerImpl fs = (FSControllerImpl) observable;
		////			fs.
		//		}

		if(tilstand.equals(Tilstand.LOGIN)){
			try{
				Bruger bruger = fsController.getBruger();
				if(bruger.erKunde()&&bruger.erAktivt()){
					flextur.showMenuKunde();
				}else if(!bruger.erKunde()){
					flextur.showMenuAdmin();
				}
			
		} catch (LoginException e){
			alert.visLoginFejllDialog();
		} catch (RuntimeException e){
			alert.visLoginFejllDialog();
		}
	}


}

@Override
void postInitialize() {
	// TODO Auto-generated method stub

}


}