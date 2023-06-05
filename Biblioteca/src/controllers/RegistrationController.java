package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.ScenesEnum;
import models.User;
import models.validations.UserValidation;

public class RegistrationController implements Initializable {
	@FXML
	private BorderPane borderPane;
	
	@FXML
    private TextField cpfXML;

    @FXML
    private CheckBox employeeXML;

    @FXML
    private PasswordField passwordXML;

    @FXML
    private TextField telefoneXML;

    @FXML
    private TextField userXML;
    
    @FXML
    private AnchorPane anchorAlert;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		views.AnimationBorderPane.animationFadeOut(borderPane);
	}
	
	@FXML
	protected void buttonVoltar(Event event) throws IOException{
		PagesControllers.changePage(ScenesEnum.LOGIN);	
	}
	
	@FXML
    void buttonCadastrar(ActionEvent event) {
				
		if(UserValidation.fieldIsNotEmpty( userXML.getText(), passwordXML.getText(), cpfXML.getText(), telefoneXML.getText())){
			UserValidation.daoOfUser.includeAtomic(createUserObject());
			clearFroms();	
		}else anchorAlert.setVisible(true);
    }
	
	private User createUserObject(){
		User user = new User(userXML.getText().trim().toLowerCase(),
				passwordXML.getText().trim(),
				cpfXML.getText().trim(),
				telefoneXML.getText().trim(),
				employeeXML.selectedProperty().getValue());
		
		return user;
	}
	
	private void clearFroms(){
		userXML.setText("");
		cpfXML.setText("");
		passwordXML.setText("");
		telefoneXML.setText("");
		employeeXML.setSelected(false);
		anchorAlert.setVisible(false);
	}
	
}
