package slogo.view.tabs;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import slogo.controller.command.CommandController;
import slogo.model.api.parser.exceptions.InvalidTokenException;

/**
 * TurtleControlTab is the tab that allows the user to control the turtle.
 */
public class TurtleControlTab implements TabContent {

  private final CommandController commandController;
  private GridPane content;

  private TextField forwardInput;
  private TextField backInput;
  private TextField leftInput;
  private TextField rightInput;

  /**
   * Constructor for TurtleControlTab.
   *
   * @param commandController the CommandController
   */
  public TurtleControlTab(CommandController commandController) {
    this.commandController = commandController;
    initializeContent();
  }

  /**
   * Return a node with the turtle control content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    return content;
  }

  private void initializeContent() {
    content = new GridPane();
    content.setPadding(new Insets(10));
    content.setVgap(10);
    content.setHgap(10);

    // Initialize TextFields
    forwardInput = createInputField();
    backInput = createInputField();
    leftInput = createInputField();
    rightInput = createInputField();

    // Buttons for turtle movements
    Button forwardButton = createMovementButton("fd", "Forward", forwardInput);
    Button backButton = createMovementButton("bk", "Backward", backInput);
    Button leftButton = createMovementButton("lt", "Left Turn", leftInput);
    Button rightButton = createMovementButton("rt", "Right Turn", rightInput);

    // Adding components to the grid
    content.add(forwardButton, 0, 0);
    content.add(forwardInput, 1, 0);
    content.add(backButton, 0, 1);
    content.add(backInput, 1, 1);
    content.add(leftButton, 0, 2);
    content.add(leftInput, 1, 2);
    content.add(rightButton, 0, 3);
    content.add(rightInput, 1, 3);
  }

  private Button createMovementButton(String command, String label, TextField inputField) {
    Button button = new Button(label);
    button.setOnAction(event -> executeTurtleCommand(command, inputField));
    return button;
  }

  private TextField createInputField() {
    TextField inputField = new TextField("50");  // Default value set to 50
    inputField.setPrefWidth(60);
    return inputField;
  }

  private void executeTurtleCommand(String command, TextField inputField) {
    String commandValue = inputField.getText();
    try {
      commandController.executeCommand(command + " " + commandValue);
    } catch (Exception e) {
      e.printStackTrace();
    } catch (InvalidTokenException e) {
        throw new RuntimeException(e);
    }
  }

}
