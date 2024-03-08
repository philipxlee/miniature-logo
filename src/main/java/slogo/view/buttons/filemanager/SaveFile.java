package slogo.view.buttons.filemanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import slogo.view.tabs.CommandHistoryTab;

public class SaveFile extends AbstractFileProcessor implements FileSaver {

  private final CommandHistoryTab commandHistoryTab;

  /**
   * The SaveFile constructor creates a new instance of SaveFile.
   *
   * @param commandHistoryTab The command history tab
   */
  public SaveFile(CommandHistoryTab commandHistoryTab) {
    this.commandHistoryTab = commandHistoryTab;
  }

  /**
   * Handles the event of saving a file.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    List<String> commands = commandHistoryTab.getCommandsHistory();
    Optional<File> file = Optional.ofNullable(
        createFileChooser("Save .slogo File", "*.slogo").showSaveDialog(null));
    file.ifPresent(f -> saveFile(f, String.join(System.lineSeparator(), commands)));
  }

  /**
   * Saves the file.
   *
   * @param file the file to be saved
   * @param content the content to be saved
   */
  @Override
  public void saveFile(File file, String content) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(content);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
