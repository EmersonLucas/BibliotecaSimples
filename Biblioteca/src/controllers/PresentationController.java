package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import models.ScenesEnum;
import models.validations.UserValidation;

public class PresentationController implements Initializable{
	
	@FXML
	BorderPane borderPane;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		views.AnimationBorderPane.animationFadeOut(borderPane);
		if(UserValidation.daoOfUser != null);
	}
	
	
	@FXML
	protected void buttonAvancar(ActionEvent event) throws IOException{
		views.AnimationBorderPane.animationFadeIn(borderPane);
		PagesControllers.changePage(ScenesEnum.LOGIN);	
	}
	
	
	
}
