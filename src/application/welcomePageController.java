package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class welcomePageController {

    @FXML
    void LoginWindowBtn(ActionEvent event) {
    		try {
    			Parent root = FXMLLoader.load(getClass().getResource("/Resources/Login.fxml"));
    			Scene scene = new Scene(root);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			Stage stage1 = new Stage();
    			stage1.setTitle("Login Page");
    			stage1.setScene(scene);
    			stage1.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    

  

}
