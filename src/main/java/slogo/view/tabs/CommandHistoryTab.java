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
import javafx.scene.text.Text;
import slogo.controller.SaveButtonController;

/**
 * CommandHistoryTab is the tab for command history.
 */
public class CommandHistoryTab implements TabContent {

  private VBox content;
  private VBox historyContainer;
  private ScrollPane scrollPane;
  private List<String> commandsHistory;

  /**
   * Return a node with the command history content.
   *
   * @return Node representing the pane.
   */
  @Override
  public Node getContent() {
    this.content = new VBox();
    content.setFillWidth(true);

    historyContainer = new VBox();
    historyContainer.setFillWidth(true);

    scrollPane = new ScrollPane(historyContainer);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    Button saveButton = new Button("Save");
    saveButton.setOnAction(new SaveButtonController(this));

    HBox buttonBox = new HBox(saveButton);
    content.getChildren().add(scrollPane);
    content.getChildren().add(buttonBox);

    return content;
  }

  /**
   * Update content of command history tab.
   *
   * @param commands Iterator of commands.
   */
  public void updateContent(Iterator<String> commands) {
    // Temporarily store the commands to add them in reverse order
    List<Node> tempLabels = new ArrayList<>();
    commandsHistory = new ArrayList<>();
    while (commands.hasNext()) {
      Text commandText = new Text(commands.next());
      String text = commandText.getText();
      tempLabels.add(commandText);
      commandsHistory.add(text);
    }
    // Remove current text
    historyContainer.getChildren().clear();

    // Add all commands in reverse order to the VBox
    Collections.reverse(tempLabels);
    historyContainer.getChildren().addAll(tempLabels);
    scrollPane.setVvalue(1.0);
  }

  public List<String> getCommandsHistory() {
    return commandsHistory;
  }
}


