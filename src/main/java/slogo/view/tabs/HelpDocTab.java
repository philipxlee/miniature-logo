package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * HelpDocTab is the tab that displays help information.
 */
public class HelpDocTab implements TabContent {

  /**
   * Return a node with the help doc content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("Help Docs Tab Content"));
    return content;
  }
}
