package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import slogo.controller.command.CommandController;

public class ConsoleLoadFile implements FileLoader, EventHandler<ActionEvent> {

  private final CommandController commandController;

  public ConsoleLoadFile(CommandController commandController) {
    this.commandController = commandController;
  }


  @Override
  public void handle(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open SLogo File");
    fileChooser.getExtensionFilters()
        .addAll(new FileChooser.ExtensionFilter("SLogo Files", "*.slogo"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      loadFile(selectedFile);
    }
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
