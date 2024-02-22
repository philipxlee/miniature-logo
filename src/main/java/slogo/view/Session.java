package slogo.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Define a class for the session
public class Session {
  // Declare the session data as properties
  private StringProperty code;
  private StringProperty history;
  private StringProperty display;

  // Create the session data with default values
  public Session() {
    code = new SimpleStringProperty("");
    history = new SimpleStringProperty("");
    display = new SimpleStringProperty("");
  }

  // Get the code property
  public StringProperty codeProperty() {
    return code;
  }

  // Get the code value
  public String getCode() {
    return code.get();
  }

  // Set the code value
  public void setCode(String code) {
    this.code.set(code);
  }

  // Get the history property
  public StringProperty historyProperty() {
    return history;
  }

  // Get the history value
  public String getHistory() {
    return history.get();
  }

  // Set the history value
  public void setHistory(String history) {
    this.history.set(history);
  }

  // Get the display property
  public StringProperty displayProperty() {
    return display;
  }

  // Get the display value
  public String getDisplay() {
    return display.get();
  }

  // Set the display value
  public void setDisplay(String display) {
    this.display.set(display);
  }
}
