package controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.ScenesEnum;
import models.validations.UserValidation;

public class LoginController implements Initializable {
	
	@FXML
	BorderPane borderPane;
	
	@FXML
	AnchorPane anchorAlert;
	
	@FXML
    private PasswordField passwordXML;

    @FXML
    private TextField userXML;
    	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		views.AnimationBorderPane.animationFadeOut(borderPane);
	}
	
	@FXML
	protected void buttonVoltar(ActionEvent event) throws IOException{
		PagesControllers.changePage(ScenesEnum.PRESENTATION);	
	}
	
	@FXML
	protected void hyperCadastrar(ActionEvent event) throws IOException {
		PagesControllers.changePage(ScenesEnum.REGISTRATION);		
	}
	
	@FXML
    void buttonEntrar(ActionEvent event) throws IOException{
		if(UserValidation.validationLogin(userXML.getText(), passwordXML.getText())){
			clearFroms();
			anchorAlert.setVisible(false);
		}
		else {
		anchorAlert.setVisible(true);
		clearFroms();
		}
    }
	
	
	
	private void clearFroms(){
		userXML.setText("");
		passwordXML.setText("");
	
	}
}
