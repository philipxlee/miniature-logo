package slogo.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import slogo.view.tabs.CommandHistoryTab;

/**
 * SaveButtonController is the controller for the Save button.
 */
public class SaveButtonController implements EventHandler<ActionEvent> {

  private CommandHistoryTab commandHistoryTab;

  /**
   * Constructor for SaveButtonController.
   *
   * @param commandHistoryTab Reference to the CommandHistoryTab.
   */
  public SaveButtonController(CommandHistoryTab commandHistoryTab) {
    this.commandHistoryTab = commandHistoryTab;
  }

  @Override
  public void handle(ActionEvent event) {
    List<String> commands = getCommandsFromTab();

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save .slogo File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Slogo Files", "*.slogo"));
    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
      String filePath = file.getAbsolutePath();
      SlogoFileHandler.saveCommandsToFile(commands, filePath);
    }
  }

  /**
   * Get commands from the CommandHistoryTab.
   *
   * @return List of commands.
   */
  private List<String> getCommandsFromTab() {
    return commandHistoryTab.getCommandsHistory();
  }
}
