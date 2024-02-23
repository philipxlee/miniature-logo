package slogo.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Define a class for the splash screen
public class SplashScreen extends Screen {
  // Declare a button field
  private Button startButton;

  // Create a constructor with a stage parameter
  public SplashScreen(Stage stage) {
    super(stage);
    startButton = new Button("Start Session");
    startButton.setOnAction(e -> {
//      hide();
      ViewController.showCodeEditor();
//      Stage secondStage = new Stage();
//      CodeEditor codeEditor = new CodeEditor(secondStage);
//      codeEditor.show();
    });
    StackPane pane = new StackPane();
    pane.getChildren().add(startButton);
    Scene scene = new Scene(pane, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    stage.setScene(scene);
    stage.setTitle("Splash Screen");
  }

  // Get the start button
  public Button getStartButton() {
    return startButton;
  }
}
