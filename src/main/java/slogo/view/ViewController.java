package slogo.view;

import javafx.stage.Stage;

// Define a class for the view controller
public class ViewController {
  // Declare a splash screen field
  private SplashScreen splashScreen;
  // Declare a code editor field
  private CodeEditor codeEditor;

  // Create a constructor with a stage parameter
  public ViewController(Stage stage) {
    // Create a code editor object
    codeEditor = new CodeEditor(stage);
    // Create a splash screen object
    splashScreen = new SplashScreen(stage);

  }

  // Show the splash screen
  public void showSplashScreen() {
    splashScreen.show();
  }

  // Show the code editor
  public void showCodeEditor() {
    codeEditor.show();
  }
}
