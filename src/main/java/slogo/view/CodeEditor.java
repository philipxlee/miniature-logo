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
    super(stage);
    codeArea = new TextArea();
    session = new Session();
    codeArea.textProperty().bindBidirectional(session.codeProperty());
    Scene scene = new Scene(codeArea, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    stage.setScene(scene);
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
