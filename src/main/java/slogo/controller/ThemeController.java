package slogo.controller;

public class ThemeController {

  private static String currentTheme = "slogo/example/view/styles.css";

  public static void applyTheme(javafx.scene.Scene scene, String themeName) {
    switch (themeName) {
      case "Dark Theme" -> currentTheme = "slogo/example/view/darkmode.css";
      case "Light Theme" -> currentTheme = "slogo/example/view/lightmode.css";
      case "Duke Theme" -> currentTheme = "slogo/example/view/dukemode.css";
      case "UNC Theme" -> currentTheme = "slogo/example/view/uncmode.css";
      default -> currentTheme = "slogo/example/view/styles.css";
    }
    scene.getStylesheets().clear();
    scene.getStylesheets().add(currentTheme);
  }

  public static String getCurrentTheme() {
    return currentTheme;
  }
}
