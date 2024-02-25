package slogo.view.alert;

import javafx.scene.control.Alert.AlertType;

/**
 * Alert service triggers an alert dialog.
 */
public class Alert {

  /**
   * Trigger an alert in the UI.
   *
   * @param title   is the title used for the dialog
   * @param message is the message used in the alert
   */
  public static void showError(String title, String message) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
