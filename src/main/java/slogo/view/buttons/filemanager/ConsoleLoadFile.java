package slogo.view.buttons.filemanager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import slogo.controller.command.CommandController;
import slogo.exceptions.InvalidCommandException;
import slogo.view.alert.Alert;

/**
 * The ConsoleLoadFile class is a file loader that loads a file from the console.
 */
public class ConsoleLoadFile extends AbstractFileProcessor implements FileLoader {

  private final CommandController commandController;

  /**
   * The ConsoleLoadFile constructor creates a new instance of ConsoleLoadFile.
   *
   * @param commandController The command controller
   */
  public ConsoleLoadFile(CommandController commandController) {
    this.commandController = commandController;
  }

  /**
   * Handles the event of loading a file.
   *
   * @param event the event which occurred when the file was loaded
   */
  @Override
  public void handle(ActionEvent event) {
    Optional<File> selectedFile = Optional.ofNullable(
        createFileChooser("Open File", "*.slogo").showOpenDialog(null));
    selectedFile.ifPresent(this::loadFile);
  }

  /**
   * Loads the file.
   *
   * @param file the file to be loaded
   */
  @Override
  public void loadFile(File file) {
    try {
      String commands = readFileContents(file);
      executeCommands(commands);
    } catch (IOException e) {
      Platform.runLater(
          () -> Alert.showError("Load Error", "File couldn't be loaded: " + e.getMessage()));
    }
  }


  private void executeCommands(String commands) {
    Platform.runLater(() -> {
      String[] commandLines = commands.split("\\n");
      Arrays.stream(commandLines)
          .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("#"))
          .forEach(commandLine -> {
            try {
              commandController.executeCommand(commandLine.trim());
            } catch (InvalidCommandException e) {
              Alert.showError("Invalid Command",
                  "An invalid command was encountered: " + e.getMessage());
            }
          });
    });
  }

}
