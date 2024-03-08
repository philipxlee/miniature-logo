package slogo.view.scenes.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.controller.command.CommandController;
import slogo.exceptions.InvalidCommandException;
import slogo.view.alert.Alert;

/**
 * The InputPane class is a pane that allows the user to input commands.
 */
public class InputPane {

  private static final String DOLLAR_SIGN = "$ ";
  private VBox inputBox;
  private TextArea commandInput;

  /**
   * The InputPane constructor creates a new instance of InputPane.
   *
   * @param height The height of the input pane
   * @param commandController The command controller
   */
  public InputPane(int height, CommandController commandController) {
    inputBox = new VBox(10);
    commandInput = new TextArea();
    configureCommandInput();
    setInputBoxProperties(height);
    setKeyPressEventHandler(commandController);
  }

  /**
   * Gets the input box.
   *
   * @return The input box
   */
  public VBox getInputBox() {
    return inputBox;
  }

  /**
   * Sets the input text.
   *
   * @param text The text to set the input to
   */
  public void setInputText(String text) {
    commandInput.setText(text);
  }

  /**
   * Executes a command.
   *
   * @param command The command to execute
   * @param commandController The command controller
   */
  public void executeCommand(String command, CommandController commandController) {
    if (!command.isEmpty()) {
      try {
        commandController.executeCommand(command);
      } catch (InvalidCommandException e) {
        Alert.showError("Invalid Command", "Please enter a valid command.");
      }
      resetCommandInput();
    }
  }

  private void configureCommandInput() {
    commandInput.setText(DOLLAR_SIGN);
    commandInput.getStyleClass().add("command-input");
    commandInput.positionCaret(DOLLAR_SIGN.length());
  }

  private void setKeyPressEventHandler(CommandController commandController) {
    commandInput.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
        processCommand(commandController);
        event.consume();
      }
    });
  }

  private void setInputBoxProperties(int height) {
    inputBox.getChildren().add(commandInput);
    inputBox.setAlignment(Pos.BOTTOM_CENTER);
    inputBox.setPadding(new Insets(0, 20, 20, 50));
    inputBox.setPrefHeight(height * 0.5);
    VBox.setVgrow(commandInput, Priority.ALWAYS);
  }

  private void resetCommandInput() {
    commandInput.setText(DOLLAR_SIGN);
    commandInput.positionCaret(commandInput.getText().length());
  }

  private void processCommand(CommandController commandController) {
    String command = commandInput.getText().trim();
    int dollarLength = DOLLAR_SIGN.length();
    if (command.startsWith(DOLLAR_SIGN)) {
      command = command.substring(dollarLength);
    }
    executeCommand(command, commandController);
  }
}
