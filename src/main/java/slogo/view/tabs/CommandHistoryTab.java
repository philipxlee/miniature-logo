package slogo.view.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.controller.command.CommandController;
import slogo.controller.config.LanguageController;
import slogo.view.buttons.filemanager.ConsoleLoadFile;
import slogo.view.buttons.filemanager.SaveFile;


/**
 * CommandHistoryTab is the tab for command history.
 */
public class CommandHistoryTab implements TabContent {

  private final CommandController commandController;
  private VBox content;
  private VBox historyContainer;
  private ScrollPane scrollPane;
  private List<String> commandsHistory;


  /**
   * Constructor for CommandHistoryTab.
   *
   * @param commandController the CommandController
   */
  public CommandHistoryTab(CommandController commandController) {
    this.commandController = commandController;
  }

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

    Button saveFileButton = new Button(LanguageController.getText("SaveFile"));
    saveFileButton.setOnAction(new SaveFile(this));
    Button loadFileButton = new Button(LanguageController.getText("LoadFile"));
    loadFileButton.setOnAction(new ConsoleLoadFile(commandController));

    HBox buttonBox = new HBox();
    buttonBox.getChildren().addAll(saveFileButton, loadFileButton);
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

  /**
   * Get the commands history.
   *
   * @return List of commands history.
   */
  public List<String> getCommandsHistory() {
    return commandsHistory;
  }
}


