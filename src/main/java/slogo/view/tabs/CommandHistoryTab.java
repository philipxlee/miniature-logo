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
 * Represents the command history tab within the application. It provides users with the
 * functionality to view the list of executed commands and interact with them.
 */
public class CommandHistoryTab implements TabContent {

  private final CommandController commandController;
  private VBox content;
  private VBox historyContainer;
  private ScrollPane scrollPane;
  private List<String> commandsHistory;

  /**
   * Constructs a CommandHistoryTab instance with a reference to the command controller.
   *
   * @param commandController The controller responsible for command execution and interaction.
   */
  public CommandHistoryTab(CommandController commandController) {
    this.commandController = commandController;
  }

  /**
   * Generates and returns the UI content for the command history tab.
   *
   * @return The Node representing the command history tab's UI.
   */
  @Override
  public Node getContent() {
    initializeComponents();
    layoutComponents();
    return content;
  }

  /**
   * Updates the command history view with a new set of commands.
   *
   * @param commands An iterator over the new set of commands to be displayed.
   */
  public void updateContent(Iterator<String> commands) {
    commandsHistory = new ArrayList<>();
    historyContainer.getChildren().clear();

    while (commands.hasNext()) {
      String command = commands.next();
      commandsHistory.add(command);
      historyContainer.getChildren().add(createCommandText(command));
    }
    scrollPane.setVvalue(1.0);
  }

  /**
   * Retrieves the command history list.
   *
   * @return A list containing the history of commands executed.
   */
  public List<String> getCommandsHistory() {
    return commandsHistory;
  }

  /**
   * Initializes the UI components used within the tab.
   */
  private void initializeComponents() {
    content = new VBox();
    historyContainer = new VBox();
    scrollPane = new ScrollPane(historyContainer);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setPrefHeight(300);
  }

  /**
   * Layouts the UI components within the tab.
   */
  private void layoutComponents() {
    content.setFillWidth(true);
    HBox buttonBox = new HBox(
        createSaveFileButton(),
        createLoadFileButton()
    );
    content.getChildren().addAll(scrollPane, buttonBox);
  }

  /**
   * Creates a clickable text element for a command.
   *
   * @param command The command string to be displayed and interacted with.
   * @return A Text node representing the command.
   */
  private Text createCommandText(String command) {
    Text commandText = new Text(command);
    commandText.setOnMouseClicked(event -> executeCommandInteractively(command));
    return commandText;
  }

  /**
   * Creates and returns a button for saving the command history.
   *
   * @return A configured save file button.
   */
  private Button createSaveFileButton() {
    Button saveFileButton = new Button(LanguageController.getText("SaveFile"));
    saveFileButton.setOnAction(new SaveFile(this));
    return saveFileButton;
  }

  /**
   * Creates and returns a button for loading commands from a file.
   *
   * @return A configured load file button.
   */
  private Button createLoadFileButton() {
    Button loadFileButton = new Button(LanguageController.getText("LoadFile"));
    loadFileButton.setOnAction(new ConsoleLoadFile(commandController));
    return loadFileButton;
  }

  /**
   * Executes a command interactively, allowing for potential modifications before execution.
   *
   * @param command The command to be executed.
   */
  private void executeCommandInteractively(String command) {
    TextInputDialog dialog = new TextInputDialog(command);
    dialog.setTitle(LanguageController.getText("ExecuteCommand"));
    dialog.setHeaderText(LanguageController.getText("ModifyAndExecuteCommand"));
    dialog.setContentText(LanguageController.getText("Command"));
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
