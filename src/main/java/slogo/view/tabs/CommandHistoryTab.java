package slogo.view.tabs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.Collections;

public class CommandHistoryTab implements TabContent {

  private VBox content;
  private ScrollPane scrollPane;

  @Override
  public Node getContent() {
    this.content = new VBox();
    content.setFillWidth(true);

    scrollPane = new ScrollPane(content);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    return scrollPane;
  }

  public void updateContent(Iterator<String> commands) {
    // Temporarily store the commands to add them in reverse order
    List<Node> tempLabels = new ArrayList<>();
    while (commands.hasNext()) {
      tempLabels.add(new Label(commands.next()));
    }

    // Remove current text
    content.getChildren().clear();

    // Add all commands in reverse order to the VBox
    Collections.reverse(tempLabels);
    content.getChildren().addAll(tempLabels);
    scrollPane.setVvalue(1.0);
  }
}
