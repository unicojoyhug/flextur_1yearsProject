/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.FSController;
import logic.FSControllerImpl;

/**
 *
 * @author Jonas Mørch, Juyoung Choi
 */
public class FlexturGUI extends Application {

	private FSController fsController = new FSControllerImpl();

	private Stage primaryStage;
	@FXML
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Flextur System");
		initRootLayout();
		showLogin();
	}

	public void showLogin() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("Login.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);


			FSPane loginController = loader.<FSPane> getController();
			loginController.setFSController(fsController);			
			loginController.setMainApp(this);
			loginController.postInitialize();			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


	private void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showOpretProfil() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("OpretProfil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			FSPane opretProfilController = loader.<FSPane> getController();
			opretProfilController.setFSController(fsController);
			opretProfilController.setMainApp(this);
			opretProfilController.postInitialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMenuKunde() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("MenuKunde.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			FSPane showMenuKundeController = loader.<FSPane> getController();
			showMenuKundeController.setFSController(fsController);
			showMenuKundeController.setMainApp(this);
			showMenuKundeController.postInitialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMenuAdmin() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("MenuAdmin.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

//			MenuAdminController controller = loader.getController();
//			controller.setMainApp(this);
			
			// TODO remember to set this as fsController, in each controller : setMainApp to connect
//			f.eks. 
//			public void setMainApp(FlexturGUI flextur) {
//					this.flextur = flextur;
//					
//				}
			FSPane showMenuAdminController = loader.<FSPane> getController();
			showMenuAdminController.setFSController(fsController);
			showMenuAdminController.setMainApp(this);
			showMenuAdminController.postInitialize();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showBestilFlex() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("BestilFlex.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			FSPane bestilFlexController = loader.<FSPane> getController();
			bestilFlexController.setFSController(fsController);
			bestilFlexController.setMainApp(this);
			bestilFlexController.postInitialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAendreProfil() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("AendreProfil.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			FSPane aendreProfilContoller = loader.<FSPane> getController();
			aendreProfilContoller.setFSController(fsController);
			aendreProfilContoller.setMainApp(this);
			aendreProfilContoller.postInitialize();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHistorikKunde() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("SeHistorikKunde.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			
			FSPane seHistorikKundeController = loader.<FSPane> getController();
			seHistorikKundeController.setFSController(fsController);
			seHistorikKundeController.setMainApp(this);
			seHistorikKundeController.postInitialize();

		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHistorikAdmin() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("SeHistorikAdmin.fxml"));
			AnchorPane showHistorikAdmin = (AnchorPane) loader.load();

			// set FSPane : abstract class for setting FSController : setController
			rootLayout.setCenter(showHistorikAdmin);

			FSPane seHistorikAdminController = loader.<FSPane> getController();
			seHistorikAdminController.setFSController(fsController);
			seHistorikAdminController.setMainApp(this);
			seHistorikAdminController.postInitialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showBestillingsOversigt() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FlexturGUI.class.getResource("BestillingsOversigt.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
		
			FSPane bestillingsOversigtController = loader.<FSPane> getController();
			bestillingsOversigtController.setFSController(fsController);
			bestillingsOversigtController.setMainApp(this);
			bestillingsOversigtController.postInitialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
