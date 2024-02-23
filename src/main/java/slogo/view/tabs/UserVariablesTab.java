package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserVariablesTab implements TabContent {

  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("User Variables Tab Content"));
    return content;
  }
}