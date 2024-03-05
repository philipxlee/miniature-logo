package slogo.view.tabs;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * UserVariablesTab is the tab that displays user defined variables.
 */
public class UserVariablesTab implements TabContent {

  private final VBox content;

  /**
   * Constructor for UserVariablesTab.
   */
  public UserVariablesTab() {
    this.content = new VBox();
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
      content.getChildren().add(label);
    });
  }
}
