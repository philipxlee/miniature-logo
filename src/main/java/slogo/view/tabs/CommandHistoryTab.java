package slogo.view.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.SaveButtonController;

/**
 * CommandHistoryTab is the tab for command history.
 */
public class CommandHistoryTab implements TabContent {

  private VBox content;
  private ScrollPane scrollPane;
  private List<String> commandsHistory = new ArrayList<>();

  /**
   * Return a node with the command history content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    this.content = new VBox();
    content.setFillWidth(true);

    scrollPane = new ScrollPane(content);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    Button saveButton = new Button("Save");
    saveButton.setOnAction(new SaveButtonController(this));

    HBox buttonBox = new HBox(saveButton);
    content.getChildren().add(buttonBox);

    return scrollPane;
  }

  /**
   * Update content of command history tab.
   *
   * @param commands Iterator of commands.
   */
  public void updateContent(Iterator<String> commands) {
    // Temporarily store the commands to add them in reverse order
    List<Node> tempLabels = new ArrayList<>();
    while (commands.hasNext()) {
      String command = commands.toString();
      tempLabels.add(new Label(commands.next()));
      commandsHistory.add(command);
    }

    // Remove current text
    content.getChildren().clear();

    // Add all commands in reverse order to the VBox
    Collections.reverse(tempLabels);
    content.getChildren().addAll(tempLabels);
    scrollPane.setVvalue(1.0);
  }

  public List<String> getCommandsHistory() {
    return commandsHistory;
  }
}


