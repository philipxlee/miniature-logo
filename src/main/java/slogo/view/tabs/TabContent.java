package slogo.view.tabs;

import javafx.scene.Node;

/**
 * TabContent interface defines content of tabs.
 */
public interface TabContent {

  /**
   * Get the content to display in the tab.
   *
   * @return the Node to display
   */
  Node getContent();
}
