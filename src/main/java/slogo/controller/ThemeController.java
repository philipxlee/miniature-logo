package slogo.controller;

/**
 * ThemeController is a controller that manages the theme of the application.
 */
public class ThemeController {

  private static String currentTheme = "slogo/example/view/styles.css";

  /**
   * Apply a theme to a scene.
   *
   * @param scene     scene
   * @param themeName themeName
   */
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

  /**
   * Get the current theme.
   *
   * @return the current theme
   */
  public static String getCurrentTheme() {
    return currentTheme;
  }
}
