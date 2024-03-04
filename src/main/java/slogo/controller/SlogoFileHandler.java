package slogo.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SlogoFileHandler {

  /**
   * Save the given commands to a .slogo file.
   *
   * @param commands List of commands to save.
   * @param filePath Path to save the file.
   * @return True if saving was successful, false otherwise.
   */
  public static boolean saveCommandsToFile(List<String> commands, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      for (String command : commands) {
        writer.write(command);
        writer.newLine();
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}

