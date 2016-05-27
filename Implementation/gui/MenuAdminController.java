/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch, Juyoung Choi
 * 
 */
public class MenuAdminController extends FSPane implements Initializable {

	@FXML
	private Label usernameL;
	private FlexturGUI flexturGUI;
	private String user = "Den der bruger systemet";

	@FXML
	private void handleRegistrerFlextur(ActionEvent event) {
		flexturGUI.showRegistrerFlex();
	}

	@FXML
	private void handleSeBestillinger(ActionEvent event) {
		flexturGUI.showBestillingsOversigt();
	}

	@FXML
	private void handleSeHistorik(ActionEvent event) {
		flexturGUI.showHistorikAdmin();
	}

	@FXML
	private void handleLogUd(ActionEvent event) {

		flexturGUI.showLogin();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		usernameL.setText(user);
	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		// TODO Auto-generated method stub

	}

	@Override
	void postInitialize() {
		// TODO Auto-generated method stub

	}

}