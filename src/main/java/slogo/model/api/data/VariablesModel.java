package slogo.model.api.data;

import java.util.HashMap;
import java.util.Map;
import slogo.observer.AbstractObservable;

/**
 * VariablesModel representing the user-defined variables.
 */
public class VariablesModel extends AbstractObservable {

  private final Map<String, Double> variables;

  /**
   * Constructor for VariablesModel.
   */
  public VariablesModel() {
    super();
    this.variables = new HashMap<>();
  }

  /**
   * Sets a variable with the given name and value.
   *
   * @param variableName Name of the variable
   * @param value        Value of the variable
   */
  public void setVariable(String variableName, double value) {
    variables.put(variableName, value);
    notifyObservers();
  }

  /**
   * Gets the value of a variable with the given name.
   *
   * @param variableName Name of the variable
   * @return Value of the variable, or 0.0 if not found
   */
  public double getVariable(String variableName) {
    return variables.getOrDefault(variableName, 0.0);
  }

  /**
   * Gets all variables.
   *
   * @return Map of all variables
   */
  public Map<String, Double> getAllVariables() {
    return new HashMap<>(variables);
  }
}
