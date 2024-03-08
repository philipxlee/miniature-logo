package slogo.view.tabs;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import slogo.controller.command.CommandController;
import slogo.view.alert.Alert;

/**
 * Represents a control panel for turtle movements within the UI.
 */
public class TurtleControlTab implements TabContent {

  private final CommandController commandController;
  private GridPane content;

  private TextField forwardInput;
  private TextField backInput;
  private TextField leftInput;
  private TextField rightInput;

  /**
   * Constructor for TurtleControlTab. Initializes the UI components for turtle control.
   *
   * @param commandController The controller to execute turtle commands.
   */
  public TurtleControlTab(CommandController commandController) {
    this.commandController = commandController;
    initializeContent();
  }

  /**
   * Returns the main content of the tab.
   *
   * @return Node representing the tab content.
   */
  @Override
  public Node getContent() {
    return content;
  }

  private void initializeContent() {
    content = createConfiguredGrid();

    initializeInputFields();
    createAndAddButtons();
  }

  private void initializeInputFields() {
    forwardInput = createInputField();
    backInput = createInputField();
    leftInput = createInputField();
    rightInput = createInputField();
  }


  private void createAndAddButtons() {
    content.add(createMovementButton("fd", "Forward", forwardInput), 0, 0);
    content.add(forwardInput, 1, 0);
    content.add(createMovementButton("bk", "Backward", backInput), 0, 1);
    content.add(backInput, 1, 1);
    content.add(createMovementButton("lt", "Left Turn", leftInput), 0, 2);
    content.add(leftInput, 1, 2);
    content.add(createMovementButton("rt", "Right Turn", rightInput), 0, 3);
    content.add(rightInput, 1, 3);
  }

  private Button createMovementButton(String command, String label, TextField inputField) {
    Button button = new Button(label);
    button.setOnAction(event -> executeTurtleCommand(command, inputField));
    return button;
  }

  private TextField createInputField() {
    TextField inputField = new TextField("50"); // Default value set to 50
    inputField.setPrefWidth(60);
    return inputField;
  }

  private void executeTurtleCommand(String command, TextField inputField) {
    String commandValue = inputField.getText();
    try {
      commandController.executeCommand(command + " " + commandValue);
    } catch (Exception e) {
      Alert.showError("Error Executing Command", "An error occurred while executing the command.");
    }
  }

  private GridPane createConfiguredGrid() {
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10));
    grid.setVgap(10);
    grid.setHgap(10);
    return grid;
  }

}
