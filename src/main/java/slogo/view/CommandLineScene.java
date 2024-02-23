package slogo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CommandLineScene implements GeneralScene {

  private Scene scene;
  private TextArea commandInput;

  public CommandLineScene(int width, int height) {
    initializeScene(width, height);
  }

  @Override
  public void initializeScene(int width, int height) {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20));

    // Top area for turtle display (placeholder)
    Pane displayArea = new Pane();
    displayArea.setPrefSize(width, height * 0.65);
    displayArea.setStyle("-fx-background-color: #e0e0e0;");

    // Bottom area for command input
    commandInput = new TextArea();
    commandInput.setPromptText("Enter commands here...");
    VBox commandBox = new VBox(10, commandInput);
    commandBox.setAlignment(Pos.BOTTOM_CENTER);
    commandBox.setPrefSize(width, height * 0.25);
    commandBox.setPadding(new Insets(10, 50, 20, 50));

    // Add components to the root pane
    root.setTop(displayArea);
    root.setBottom(commandBox);
    this.scene = new javafx.scene.Scene(root, width, height);
  }

  @Override
  public Scene getScene() {
    return this.scene;
  }

  public TextArea getCommandInput() {
    return commandInput;
  }
}
