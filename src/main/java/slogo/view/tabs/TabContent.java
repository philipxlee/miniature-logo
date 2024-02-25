package slogo.view.tabs;

import javafx.scene.Node;

public interface TabContent {

  /**
   * Get the content to display in the tab
   *
   * @return the Node to display
   */
  Node getContent();
}
