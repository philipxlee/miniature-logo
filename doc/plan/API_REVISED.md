# API Updated Design

### Philip Lee, Arnav Nayak, Connor Johnson, Prince Ahmed

### Team 2

#### English Description

The CommandController is the External API used by the view to interact with our model. We actually
have 4 APIs. 1 external model API that our CommandController uses to interact with the data. 1
internal model API that our model uses to store our data. 1 external CommandController API that our
view uses to handle commands and subscribe to models. And 1 internal view API that our view uses to
add
new UI modules.

The external model API interfaces with the model. It defines methods that move the turtle, rotate
the turtle, retrieve command history, iterate on lines and modify state in a specific way.

The internal model API defines our command interface that is used by the command controller to
execute commands, parse the string into commands and store lines.

The external CommandController API defines a method to handle the execution of a command. It also is
an interface that the view can use to subscribe to updates from a specific model.

The internal view API consists of Scenes that encapsulate Panes. Each pane module can be an observer
on a model and subscribe for updates.

#### Java Description

External CommandController API

CommandController.java

```java
/**
 * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
 * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
 *
 * @param commandString the command to be executed as a string
 */
public void executeCommand(String commandString) throws InvalidCommandException

/**
 * Subscribe to updates from the TurtleModel
 *
 * @param observer that wants to subscribe
 */
public void observeTurtle(Observer observer);
  
/**
 * Subscribe to updates from the CommandHistoryModel
 *
 * @param observer that wants to subscribe
 */
public void observeHistory(Observer observer);
```

External Model API

TurtleModel.java

```java
/**
 * Move Turtle forward or backward based on distance
 *
 * @param distance distance to move Turtle
 */
public double moveTurtle(double distance);

/**
 * Rotate Turtle clockwise
 *
 * @param angle angle to rotate turtle by
 */
public double rotateTurtle(double angle);

/**
 * Add observer to list of observers. Subscribing to the turtle will also subscribe you to its
 * lines
 */
@Override
public void addObserver(Observer observer);
```

CommandHistoryModel.java

```java
/**
* Adds a command to the command history queue.
*
* @param command the command to be added to the history
*/
public void addCommand(String command);

/**
 * Provides an iterator over the command history.
 *
 * @return An Iterator<String> for the command history.
 */
public Iterator<String> iterator();
```