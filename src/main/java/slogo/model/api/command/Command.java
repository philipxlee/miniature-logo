package slogo.model.api.command;

/**
 * Command interface creates an abstraction for every command being run (Command design pattern).
 */
public abstract class Command {
  private String canonicalName;
  private String description;
  private String example;
  private String help;
  private int parametersCount;
  private String implementingClass;

  // Constructor to set the command details
  public Command(String canonicalName, String description, String example, String help, int parametersCount, String implementingClass) {
    this.canonicalName = canonicalName;
    this.description = description;
    this.example = example;
    this.help = help;
    this.parametersCount = parametersCount;
    this.implementingClass = implementingClass;
  }
  public Command() {
  }


  /**
   * Define behavior to run when command is executed.
   */
  public abstract Double execute();
}