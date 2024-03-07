package slogo.controller.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {

  private static ResourceBundle messages = loadResourceBundle("English");

  public static void changeLanguage(String languageName) {
    messages = loadResourceBundle(languageName);
  }

  public static String getText(String key) {
    return messages.getString(key);
  }

  private static ResourceBundle loadResourceBundle(String languageName) {
    String baseName = "slogo.example.languages." + languageName;
    return ResourceBundle.getBundle(baseName, Locale.getDefault());
  }
}
