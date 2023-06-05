package models;

import javafx.application.Application;
import javafx.stage.Stage;


public class BibliotecaStart extends Application{
	
	public static CachePages cachePages = new CachePages();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		CachePages.stage = primaryStage;
		primaryStage.setTitle("Biblioteca Simples");
		primaryStage.setResizable(false);
		primaryStage.setScene(cachePages.loadPresentation());
	    primaryStage.show();
	}
	
}
