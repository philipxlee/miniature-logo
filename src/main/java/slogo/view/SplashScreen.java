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
    // Call the superclass constructor
    super(stage);
    // Create a button for the splash screen
    startButton = new Button("Start Session");
    // Define what happens when the button is clicked
    startButton.setOnAction(e -> {
      // Hide the splash screen
      hide();
      // Create a new stage for the code editor
      Stage secondStage = new Stage();
      // Create a code editor object
      CodeEditor codeEditor = new CodeEditor(secondStage);
      // Show the code editor
      codeEditor.show();
    });
    // Create a pane for the splash screen
    StackPane pane = new StackPane();
    // Add the button to the pane
    pane.getChildren().add(startButton);
    // Create a scene for the splash screen
    Scene scene = new Scene(pane, 300, 200);
    // Set the scene to the stage
    stage.setScene(scene);
    // Set the title of the stage
    stage.setTitle("Splash Screen");
  }

  // Get the start button
  public Button getStartButton() {
    return startButton;
  }
}
