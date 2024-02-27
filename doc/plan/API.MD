# API Design

### Philip Lee, Arnav Nayak, Connor Johnson, Prince Ahmed

### Team 2

#### English Description

The external API will be stored in the model.api package. The API will be the only package in the
model package that is used by other modules (view), while the rest of the model will be used
internally within the package. The external API that the model offers the view is called
CommandController. Here are the methods:

* executeCommand: executes command given a string
* getHistory: returns list of commands
* getHelp: returns help information given a string
* saveProgram: saves game state as .slogo file types

The model is encapsulated from other parts of the program since it is only accessible and modifiable
through the CommandController class. The view will interface with this CommandController API mainly
by sending it string literal inputs. The controller will validate the string, parse it into the
proper command, execute the command and return the result. If the command execution modifies the
model, any subscribers will be notified. For example, changes to the TurtleModel will notify the
TurtleView so that it can re-render the turtleModel.

This CommandController will encapsulate the TurtleModel. This information will be only modifiable by
the CommandController's api methods. The API can throw an invalid command error from this method.

The internal APIs are those that are used within the Model and the View. The internal API within the
Model consists of the ObservableModel interface, which are model states that store a list of
observers to notify about state changes. This API also consists of the Command interface, which
encapsulate command execution logic using the Command design pattern.

The API in the view will be separated out into respective components as well. There will be a view
for the user input, the help pane, the turtleModel view and the current variable pane. For basic
command
functionality, the user input view will call the model API when a user inputs a string. This will
trigger the TurtleModel to update if the command is a Turtle related command. Since the TurtleView
will be subscribed to changes in the TurtleModel, it will re-render itself based on the change.

#### Java Description

External API (CommandController)

```java
public double executeCommand(String commandString) throws InvalidCommandException;
public String getHelp(String commandString) throws InvalidCommandException;
public List<String> getHistory();
public saveProgram();
```