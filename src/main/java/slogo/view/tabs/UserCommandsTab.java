package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserCommandsTab implements TabContent {

  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("User Commands Tab Content"));
    return content;
  }
}

