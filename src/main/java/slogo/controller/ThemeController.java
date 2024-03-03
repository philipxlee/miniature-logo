package slogo.controller;

public class ThemeController {

  private static String currentTheme = "slogo/example/view/styles.css";

  public static void applyTheme(javafx.scene.Scene scene, String themeName) {
    switch (themeName) {
      case "Dark Mode" -> currentTheme = "path/to/dark_mode.css";
      case "Light Mode" -> currentTheme = "path/to/light_mode.css";
      case "Duke Mode" -> currentTheme = "path/to/duke_mode.css";
      case "UNC Mode" -> currentTheme = "path/to/unc_mode.css";
      default -> currentTheme = "slogo/example/view/styles.css";
    }
    scene.getStylesheets().clear();
    scene.getStylesheets().add(currentTheme);
  }

  public static String getCurrentTheme() {
    return currentTheme;
  }
}
