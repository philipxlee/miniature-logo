package slogo.view;

//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class SimpleApp extends Application {
//
//  @Override
//  public void start(Stage primaryStage) {
//    // Create a button for the splash screen
//    Button startButton = new Button("Start Session");
//    // Define what happens when the button is clicked
//    startButton.setOnAction(e -> {
//      // Hide the first stage
//      primaryStage.hide();
//      // Create a new stage for the other screen
//      Stage secondStage = new Stage();
//      // Create a text area for the code editor
//      TextArea codeArea = new TextArea();
//      // Create a scene for the other screen
//      Scene secondScene = new Scene(codeArea, 300, 200);
//      // Set the scene to the stage
//      secondStage.setScene(secondScene);
//      // Set the title of the stage
//      secondStage.setTitle("Code Editor");
//      // Show the stage
//      secondStage.show();
//    });
//    // Create a pane for the splash screen
//    StackPane firstPane = new StackPane();
//    // Add the button to the pane
//    firstPane.getChildren().add(startButton);
//    // Create a scene for the splash screen
//    Scene firstScene = new Scene(firstPane, 300, 200);
//    // Set the scene to the stage
//    primaryStage.setScene(firstScene);
//    // Set the title of the stage
//    primaryStage.setTitle("Splash Screen");
//    // Show the stage
//    primaryStage.show();
//  }
//
//  public static void main(String[] args) {
//    launch(args);
//  }
//}

import javafx.application.Application;
import javafx.stage.Stage;


// simple main class to look at ui
public class SimpleApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    new ViewController(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}

