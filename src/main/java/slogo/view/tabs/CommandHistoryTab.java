package slogo.view.tabs;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class CommandHistoryTab implements TabContent {

  @Override
  public Node getContent()  {
    VBox content = new VBox();
    content.getChildren().add(new Label("Command History Tab Content"));
    return content;
  }
}
