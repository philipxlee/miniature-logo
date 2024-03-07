package slogo.view.tabs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.controller.command.CommandController;
import slogo.controller.config.LanguageController;
import slogo.exceptions.InvalidCommandException;
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
    commandsHistory = new ArrayList<>();
    historyContainer.getChildren().clear();  // Clear existing commands

    while (commands.hasNext()) {
      String commandString = commands.next();
      commandsHistory.add(commandString);

      // Create interactive text for each command
      Text commandText = new Text(commandString);
      commandText.setOnMouseClicked(event -> executeCommandInteractively(commandString));

      historyContainer.getChildren().add(commandText);
    }
    scrollPane.setVvalue(1.0);
  }

  /**
   * Get the command history.
   *
   * @return List of commands history.
   */
  public List<String> getCommandsHistory() {
    return commandsHistory;
  }

  // Execute command interactively from the command history (No-Code SLOGO)
  private void executeCommandInteractively(String command) {
    TextInputDialog dialog = new TextInputDialog(command);
    dialog.setTitle("Execute Command");
    dialog.setHeaderText("Modify and Execute Command");
    dialog.setContentText("Command:");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(updatedCommand -> {
      try {
        commandController.executeCommand(updatedCommand);
      } catch (InvalidCommandException e) {
        System.err.println("Invalid command: " + e.getMessage());
      }
    });
  }
}


