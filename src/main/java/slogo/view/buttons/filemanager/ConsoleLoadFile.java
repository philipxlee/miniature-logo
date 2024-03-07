package slogo.view.buttons.filemanager;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import slogo.controller.command.CommandController;
import slogo.model.api.parser.exceptions.InvalidTokenException;

/**
 * ConsoleLoadFile is a button for loading a session from a file.
 */
public class ConsoleLoadFile extends AbstractFileLoader {

  private final CommandController commandController;

  /**
   * Constructor for ConsoleLoadFile.
   *
   * @param commandController the CommandController
   */
  public ConsoleLoadFile(CommandController commandController) {
    super();
    this.commandController = commandController;
  }

  /**
   * Method to handle the "Load Session" button action.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    FileChooser fileChooser = createFileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      loadFile(selectedFile);
    }
  }

  /**
   * Load the file and execute the commands.
   *
   * @param file the file to load
   */
  @Override
  public void loadFile(File file) {
    try {
      String commands = readFileContents(file);
      executeCommands(commands);
    } catch (IOException e) {
      throw new RuntimeException("File couldn't be loaded", e);
    }
  }

  private void executeCommands(String commands) {
    Platform.runLater(() -> {
      String[] commandLines = commands.split("\\n");
      for (String commandLine : commandLines) {
        try {
          commandController.executeCommand(commandLine);
        } catch (Exception e) {
          throw new RuntimeException("Command couldn't be executed", e);
        } catch (InvalidTokenException e) {
            throw new RuntimeException(e);
        }
      }
    });
  }

  private FileChooser createFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open SLogo File");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("SLogo Files", "*.slogo"));
    return fileChooser;
  }
}
