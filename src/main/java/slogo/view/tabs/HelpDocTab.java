package slogo.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelpDocTab implements TabContent {

  @Override
  public Node getContent() {
    VBox content = new VBox();
    content.getChildren().add(new Label("Help Docs Tab Content"));
    return content;
  }
}
