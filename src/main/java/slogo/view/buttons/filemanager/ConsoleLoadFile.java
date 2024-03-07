package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Platform;
import slogo.controller.CommandController;

public class ConsoleLoadFile implements FileLoader {

  private final CommandController commandController;

  public ConsoleLoadFile(CommandController commandController) {
    this.commandController = commandController;
  }

  @Override
  public void loadFile(File file) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      StringBuilder commandsBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        commandsBuilder.append(line).append("\n");
      }
      reader.close();
      executeCommands(commandsBuilder.toString().trim());
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
        }
      }
    });
  }
}
