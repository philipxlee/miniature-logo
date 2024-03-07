package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class ConsoleLoadFile {

  private Consumer<String> onCommandsLoaded;

  public ConsoleLoadFile(Consumer<String> onCommandsLoaded) {
    this.onCommandsLoaded = onCommandsLoaded;
  }

  public void loadFile(File file) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      StringBuilder commandsBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        commandsBuilder.append(line).append("\n");
      }
      reader.close();
      if (onCommandsLoaded != null) {
        onCommandsLoaded.accept(commandsBuilder.toString().trim());
      }
    } catch (IOException e) {
      throw new RuntimeException("File couldn't be loaded", e);
    }
  }
}