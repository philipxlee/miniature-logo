package slogo.controller.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {

  private static Locale currentLocale = new Locale("en", "US");
  private static ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

  public static void changeLanguage(String languageCode) {
    switch (languageCode) {
      case "Spanish" -> currentLocale = new Locale("es", "ES");
      case "English" -> currentLocale = new Locale("en", "US");
    }
    messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    updateTexts();
  }

  public static String getText(String key) {
    return messages.getString(key);
  }

  private static void updateTexts() {

  }
}
