package slogo.view.tabs;

import java.util.Map;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import slogo.controller.command.CommandController;


/**
 * UserVariablesTab is the tab that displays user defined variables.
 */
public class UserVariablesTab implements TabContent {

  private final VBox content;
  private final CommandController commandController;

  /**
   * Constructor for UserVariablesTab.
   */
  public UserVariablesTab(CommandController commandController) {
    this.content = new VBox();
    this.commandController = commandController;
  }

  /**
   * Return a node with the user variable content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    return content;
  }

  /**
   * Update the content of the tab with user-defined variables.
   *
   * @param variablesMap Map of variable names to their values
   */
  public void updateContent(Map<String, Double> variablesMap) {
    content.getChildren().clear();
    variablesMap.forEach((name, value) -> {
      Label label = new Label(name + " = " + value);
      label.setOnMouseClicked(event -> editVariableValue(name, value));
      content.getChildren().add(label);
    });
  }

  private void editVariableValue(String name, Double currentValue) {
    TextInputDialog dialog = new TextInputDialog(currentValue.toString());
    dialog.setTitle("Edit Variable");
    dialog.setHeaderText("Edit the value of " + name);
    dialog.setContentText("Enter new value:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(newValue -> updateVariable(name, newValue));
  }

  private void updateVariable(String name, String newValue) {
    // TODO: add variables
  }

}
