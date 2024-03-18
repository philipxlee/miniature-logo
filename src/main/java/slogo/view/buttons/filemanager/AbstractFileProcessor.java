package slogo.view.buttons.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

/**
 * Abstract class that provides common functionality for file processing.
 */
public abstract class AbstractFileProcessor implements EventHandler<ActionEvent> {

  /**
   * Reads the contents of a file.
   *
   * @param file the file to read
   * @return the contents of the file
   * @throws IOException if the file cannot be read
   */
  public String readFileContents(File file) throws IOException {
    StringBuilder commandsBuilder = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      reader.lines().forEach(line -> commandsBuilder.append(line).append(System.lineSeparator()));
    }
    return commandsBuilder.toString().trim();
  }

  /**
   * Creates a file chooser with the given title and extension.
   *
   * @param title     the title of the file chooser
   * @param extension the extension of the file
   * @return the file chooser
   */
  public FileChooser createFileChooser(String title, String extension) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("SLogo Files", extension));
    return fileChooser;
  }
}
