package views;

import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;

public class AnimationBorderPane {
	public static void animationFadeIn(BorderPane borderPane) {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), borderPane);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0);
		fadeTransition.play();	
	}
	
	public static void animationFadeOut(BorderPane borderPane){
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), borderPane);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1.0);
		fadeTransition.play();
	}
}
