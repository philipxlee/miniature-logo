package slogo.view.scenes.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.controller.CommandController;
import slogo.exceptions.InvalidCommandException;
import slogo.view.alert.Alert;

/**
 * InputPane is the pane for user provided commands.
 */
public class InputPane {

  private static final String DOLLAR_SIGN = "$ ";
  private VBox inputBox;
  private TextArea commandInput;

  /**
   * Constructor for CommandConsole.
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

  /**
   * Set the input text in the commandInput TextArea.
   *
   * @param text the text to set
   */
  public void setInputText(String text) {
    commandInput.setText(text);
  }

  private void initializeInputBox(int height, CommandController commandController) {
    commandInput = new TextArea();
    commandInput.setText(DOLLAR_SIGN); // set initial text to DOLLAR_SIGN
    commandInput.setPromptText("Enter commands here...");
    commandInput.getStyleClass().add("command-input");
    commandInput.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
        String command = commandInput.getText().trim();
        if (!command.isEmpty()) {
          // remove DOLLAR_SIGN from the start of the command
          command =
              command.startsWith(DOLLAR_SIGN) ? command.substring(DOLLAR_SIGN.length()) : command;
          try {
            commandController.executeCommand(command);
          } catch (InvalidCommandException e) {
            Alert.showError("Invalid Command", "Please enter a valid command.");
          }
          commandInput.setText(DOLLAR_SIGN); // reset text to DOLLAR_SIGN after processing command
          commandInput.positionCaret(commandInput.getText().length());
          event.consume();
        }
      }
    });

    commandInput.positionCaret(DOLLAR_SIGN.length());
    inputBox = new VBox(10);
    inputBox.getChildren().add(commandInput);
    inputBox.setAlignment(Pos.BOTTOM_CENTER);
    inputBox.setPadding(new Insets(0, 20, 20, 50));
    inputBox.setPrefHeight(height * 0.5);

    // Allow the input box to grow vertically with the size of the box
    VBox.setVgrow(commandInput, Priority.ALWAYS);
  }
}
