package slogo.view.scenes.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.controller.CommandController;
import slogo.exceptions.InvalidCommandException;
import slogo.view.alert.Alert;

/**
 * InputPane is the pane for user provided commands.
 */
public class InputPane {

  private VBox inputBox;
  private TextArea commandInput;

  /**
   * Constructor for CommandConsole
   *
   * @param height            height
   * @param commandController commandController to execute commands
   */
  public InputPane(int height, CommandController commandController) {
    initializeInputBox(height, commandController);
  }

  /**
   * Returns the input box.
   *
   * @return VBox
   */
  public VBox getInputBox() {
    return inputBox;
  }

  private void initializeInputBox(int height, CommandController commandController) {
    commandInput = new TextArea();
    commandInput.setPromptText("Enter commands here...");
    commandInput.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
        String command = commandInput.getText().trim();
        if (!command.isEmpty()) {
          try {
            commandController.executeCommand(command);
          } catch (InvalidCommandException e) {
            Alert.showError("Invalid Command", "Please enter a valid command.");
          }
          commandInput.clear();
          event.consume();
        }
      }
    });

    inputBox = new VBox(10);
    inputBox.getChildren().add(commandInput);
    inputBox.setAlignment(Pos.BOTTOM_CENTER);
    inputBox.setPadding(new Insets(0, 20, 20, 50));
    inputBox.setPrefHeight(height * 0.5);

    // Allow the input box to grow vertically with the size of the box
    VBox.setVgrow(commandInput, Priority.ALWAYS);
  }
}
