package slogo.view;

import javafx.stage.Stage;

// Define a class for the view controller
public class ViewController {
  // Declare a splash screen field
  private static SplashScreen splashScreen;
  // Declare a code editor field
  private static CodeEditor codeEditor;
  private static Stage myStage;

  // Create a constructor with a stage parameter
  public ViewController(Stage stage) {
    myStage = stage;
    splashScreen = new SplashScreen(stage);
    showSplashScreen();
  }

  // Show the splash screen
  public static void showSplashScreen() {
    splashScreen.show();
  }

  // Show the code editor
  public static void showCodeEditor() {
    codeEditor = new CodeEditor(myStage);
    codeEditor.show();
  }
}
