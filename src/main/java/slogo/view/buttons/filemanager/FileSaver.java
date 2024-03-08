package slogo.view.buttons.filemanager;

import java.io.File;

/**
 * The interface for saving files.
 */
public interface FileSaver {

  void saveFile(File file, String content);

}
