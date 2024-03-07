package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class AbstractFileLoader implements FileLoader, EventHandler<ActionEvent> {

  protected String readFileContents(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder commandsBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      commandsBuilder.append(line).append("\n");
    }
    reader.close();
    return commandsBuilder.toString().trim();
  }

  @Override
  public abstract void loadFile(File file);
}
