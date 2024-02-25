package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * UserCommandsTab is the tab that displays user commands.
 */
public class UserCommandsTab implements TabContent {

  /**
   * Return a node with the user command content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("User Commands Tab Content"));
    return content;
  }
}

