package slogo.controller.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LanguageController is a controller that manages the language of the application.
 */
public class LanguageController {

  private static ResourceBundle messages = loadResourceBundle("English");

  /**
   * Change the language of the application.
   *
   * @param languageName languageName
   */
  public static void changeLanguage(String languageName) {
    messages = loadResourceBundle(languageName);
  }

  /**
   * Get the text for a given key.
   *
   * @param key key
   * @return text
   */
  public static String getText(String key) {
    return messages.getString(key);
  }

  private static ResourceBundle loadResourceBundle(String languageName) {
    String baseName = "slogo.example.languages." + languageName;
    return ResourceBundle.getBundle(baseName, Locale.getDefault());
  }
}
