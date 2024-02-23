package slogo.view.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import slogo.controller.CommandController;

public class CommandConsole {

  private VBox inputBox;
  private TextArea commandInput;

  /**
   * Constructor for CommandConsole
   *
   * @param width width
   * @param height height
   * @param commandController commandController to execute commands
   */
  public CommandConsole(int width, int height, CommandController commandController) {
    initializeInputBox(width, height, commandController);
  }

  /**
   * Returns the input box
   *
   * @return VBox
   */
  public VBox getInputBox() {
    return inputBox;
  }

  private void initializeInputBox(int width, int height, CommandController commandController) {
    commandInput = new TextArea();
    commandInput.setPromptText("Enter commands here...");
    commandInput.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
        String command = commandInput.getText().trim();
        if (!command.isEmpty()) {
          commandController.executeCommand(command);
          commandInput.clear();
          event.consume();
        }
      }
    });

    inputBox = new VBox(10);
    inputBox.getChildren().add(commandInput);
    inputBox.setAlignment(Pos.BOTTOM_CENTER);
    inputBox.setPadding(new Insets(10, 50, 20, 50));
    inputBox.setPrefHeight(height * 0.25);

    // Allow the input box to grow vertically with the size of the box
    VBox.setVgrow(commandInput, javafx.scene.layout.Priority.ALWAYS);
  }
}
