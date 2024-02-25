package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * UserVariablesTab is the tab that displays user defined variables.
 */
public class UserVariablesTab implements TabContent {

  /**
   * Return a node with the user variable content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("User Variables Tab Content"));
    return content;
  }
}