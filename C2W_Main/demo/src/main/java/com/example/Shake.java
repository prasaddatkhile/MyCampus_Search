// import javafx.animation.Animation;
// import javafx.animation.Interpolator;
// import javafx.animation.TranslateTransition;
// import javafx.scene.Node;
// import javafx.util.Duration;

// public class Shake extends Animation {

//     private final TranslateTransition translateTransition;

//     public Shake(Node node) {
//         setCycleDuration(Duration.millis(100));
//         setCycleCount(2);
//         setInterpolator(Interpolator.LINEAR);

//         translateTransition = new TranslateTransition(Duration.millis(50), node);
//         translateTransition.setFromX(0);
//         translateTransition.setToX(10);
//         translateTransition.setAutoReverse(true);
//     }

//     @Override
//     protected void interpolate(double frac) {
//         translateTransition.playFromStart();
//     }

//     public void playShakeAnimation() {
//         translateTransition.playFromStart();
//     }

//     public static void main(String[] args) {
//         // Example usage:
//         javafx.application.Application.launch(MainApp.class, args);
//     }
// }

package com.example; 

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {

    private final TranslateTransition translateTransition;

    public Shake(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(100), node);
        translateTransition.setFromX(0);
        translateTransition.setByX(10);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);
    }

    public void playShakeAnimation() {
        translateTransition.playFromStart();
    }
}
