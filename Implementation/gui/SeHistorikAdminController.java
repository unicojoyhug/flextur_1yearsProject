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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author Jonas Mørch
 */
public class SeHistorikAdminController implements Initializable {

	@FXML
	private Label label;
	private FlexturGUI flexturGUI;
	@FXML
	private TableView<?> tableView;
	@FXML
	private ChoiceBox<?> kommune;
	@FXML
	private DatePicker fraDato, tilDato;
	@FXML
	private ComboBox<?> cprsoeg;

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuAdmin();
	}

	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
