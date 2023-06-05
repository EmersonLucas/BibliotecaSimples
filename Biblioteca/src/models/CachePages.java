package models;


import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class CachePages{
	
	public static Stage stage;
	protected static Scene presentationScene;
	protected static Scene loginScene;
	protected static Scene registrationScene;
	protected static Scene employeeScene;
	protected static Scene clientScene;
	
	private static final int WINDOWS_WIDTH = 960;
	private static final int WINDOWS_HEIGHT = 640;
	
	public CachePages() {
	}
	
	public Scene loadPresentation() throws IOException {
		URL url = new File("src/views/presentation.fxml").toURI().toURL();
		Parent presentation = FXMLLoader.load(url);
		presentationScene = new Scene(presentation, WINDOWS_WIDTH, WINDOWS_HEIGHT);		
		return presentationScene;
	}
	
	public Scene loadLogin() throws IOException {
		URL url = new File("src/views/login.fxml").toURI().toURL();
		Parent login = FXMLLoader.load(url);
		loginScene = new Scene(login, WINDOWS_WIDTH, WINDOWS_HEIGHT);		
		return loginScene;
	}
	
	public Scene loadRegistration() throws IOException {
		URL url = new File("src/views/registration.fxml").toURI().toURL();
		Parent registration = FXMLLoader.load(url);
		registrationScene = new Scene(registration, WINDOWS_WIDTH, WINDOWS_HEIGHT);		
		return registrationScene;
	}
	
	public Scene loadEmployee() throws IOException {
		URL url = new File("src/views/employee.fxml").toURI().toURL();
		Parent employee = FXMLLoader.load(url);
		employeeScene = new Scene(employee, WINDOWS_WIDTH, WINDOWS_HEIGHT);		
		return employeeScene;
	}
	
	public Scene loadClient() throws IOException {
		URL url = new File("src/views/client.fxml").toURI().toURL();
		Parent client = FXMLLoader.load(url);
		clientScene = new Scene(client, WINDOWS_WIDTH, WINDOWS_HEIGHT);		
		return clientScene;
	}
	
}
