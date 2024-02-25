package slogo.view.tabs;

import java.util.Iterator;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CommandHistoryTab implements TabContent {

  private VBox content;

  @Override
  public Node getContent() {
    this.content = new VBox();
    return content;
  }

  public void updateContent(Iterator<String> commands) {
    // remove current text
    content.getChildren().clear();

    // add new commands
    while (commands.hasNext()) {
      content.getChildren().add(new Label(commands.next()));
    }
  }
}
