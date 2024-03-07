package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * AbstractFileLoader is an abstract class for loading files.
 */
public abstract class AbstractFileLoader implements FileLoader, EventHandler<ActionEvent> {

  /**
   * Method to handle the "Load Session" button action.
   *
   * @param file the file to load
   * @return the contents of the file
   * @throws IOException if the file couldn't be read
   */
  public String readFileContents(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder commandsBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      commandsBuilder.append(line).append("\n");
    }
    reader.close();
    return commandsBuilder.toString().trim();
  }

  /**
   * Load the file.
   *
   * @param file the file to load
   */
  @Override
  public abstract void loadFile(File file);
}
