package slogo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import slogo.controller.CommandController;

public class CommandLineScene implements GeneralScene {

  private Scene scene;
  private TextArea commandInput;
  private CommandController commandController;

  public CommandLineScene(int width, int height, CommandController commandController) {
    this.commandController = commandController;
    initializeScene(width, height);
  }

  @Override
  public void initializeScene(int width, int height) {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20));

    // Create components
    Pane displayArea = createTurtleDisplay(width, height);
    VBox commandBox = createCommandBox(width, height);

    // Add components to the root pane
    root.setTop(displayArea);
    root.setBottom(commandBox);
    this.scene = new javafx.scene.Scene(root, width, height);
  }

  private Pane createTurtleDisplay(int width, int height) {
    Pane displayArea = new Pane();
    displayArea.setPrefSize(width, height * 0.65);
    displayArea.setStyle("-fx-background-color: #e0e0e0;");
    return displayArea;
  }

  private VBox createCommandBox(int width, int height) {
    commandInput = new TextArea();
    commandInput.setPromptText("Enter commands here...");
    commandInput.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
        String command = commandInput.getText().trim();
        // Execute command only if not empty and then consume the event
        if (!command.isEmpty()) {
          commandController.executeCommand(command);
          commandInput.clear();
          event.consume();
        }
      }
    });

    VBox commandBox = new VBox(10);
    commandBox.getChildren().add(commandInput);
    commandBox.setAlignment(Pos.BOTTOM_CENTER);
    commandBox.setPadding(new Insets(10, 50, 20, 50));
    commandBox.setPrefHeight(height * 0.25);
    return commandBox;
  }

  @Override
  public Scene getScene() {
    return this.scene;
  }

  public TextArea getCommandInput() {
    return commandInput;
  }
}
