package slogo.view;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

// Define a class for the code editor
public class CodeEditor extends Screen {
  // Declare a text area field
  private TextArea codeArea;
  // Declare a session field
  private Session session;

  // Create a constructor with a stage parameter
  public CodeEditor(Stage stage) {
    // Call the superclass constructor
    super(stage);
    // Create a text area for the code editor
    codeArea = new TextArea();
    // Create a session object
    session = new Session();
    // Bind the code area to the session code
    codeArea.textProperty().bindBidirectional(session.codeProperty());
    // Create a scene for the code editor
    Scene scene = new Scene(codeArea, 300, 200);
    // Set the scene to the stage
    stage.setScene(scene);
    // Set the title of the stage
    stage.setTitle("Code Editor");
  }

  // Get the code area
  public TextArea getCodeArea() {
    return codeArea;
  }

  // Get the session
  public Session getSession() {
    return session;
  }
}
