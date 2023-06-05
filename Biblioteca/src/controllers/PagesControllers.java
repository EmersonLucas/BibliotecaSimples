package controllers;

import static models.CachePages.stage;
import java.io.IOException;
import models.BibliotecaStart;
import models.ScenesEnum;

public class PagesControllers {
	@SuppressWarnings("exports")
	public static void changePage(ScenesEnum scene) throws IOException{
		switch (scene) {
			case PRESENTATION:
				stage.setScene(BibliotecaStart.cachePages.loadPresentation());
				break;
			case LOGIN:
				stage.setScene(BibliotecaStart.cachePages.loadLogin());
				break;
			case REGISTRATION:
				stage.setScene(BibliotecaStart.cachePages.loadRegistration());
				break;
			case EMPLOYEE:
				stage.setScene(BibliotecaStart.cachePages.loadEmployee());
				break;
			case CLIENT:
				stage.setScene(BibliotecaStart.cachePages.loadClient());
				break;
		
		}
	}
}
