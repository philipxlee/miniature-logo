## Model: User Executes Command 'fd 50':
* User enters the command 'fd 50' in the command window.
* View captures the entered command.
```
// View class
public void captureCommand(String command) {
// Capture the entered command
}
```
* Controller receives the command from the view.
```
// Controller class
public void receiveCommand(String command) {
// Receive command from view
}
```
* Controller sends the command to the model.
```
// Controller class
public void sendCommandToModel(String command) {
    model.processCommand(command);
}
```
* Model parses the command and moves the turtle forward 50 units.

```
// Model class
public void processCommand(String command) { }
```
* Model updates the turtle's position.
```
// Model class
public void processCommand(String command) {
// Parse command and move turtle
    notifyObservers();
}

public void addObserver(Observer observer) {
    observers.add(observer);
}

private void notifyObservers() {
    for (Observer observer : observers) {
        observer.update();
    }
}
```
* Model adds the command to the environment's history.
* View updates the display window to show the turtle's new position and trail.
```
// View class
public void updateDisplayWindow(Turtle turtle) {
// Update display window with turtle's new position
}
```

## Parser: User Executes Command 'lt 90':
* User enters the command 'lt 90' in the command window.
* View captures the entered command.
```
// View class
public void captureCommand(String command) {
// Capture the entered command
}
```
* Controller receives the command from the view.
```
// Controller class
public void receiveCommand(String command) {
// Receive command from view
}
```
* Controller sends the command to the parser.
```
// Controller class
public void sendCommandToParser(String command) {
    parser.parseCommand(command);
}
```
* Parser parses the command and generates corresponding action.
```
// Parser class
public void parseCommand(String command) {
 // Parse the command and generate corresponding action
}
```
* Parser sends the action to the Controller, which then sends it to the model for execution.
* Model executes the action (e.g., rotate turtle left by 90 degrees).
* Model then updates and notifies observers.
```
// Model class
public void executeAction(String action) {
    if (action.equals("lt 90")) {
        turtle.rotateLeft(90); // Rotate turtle left by 90 degrees
        notifyObservers(); // Notify observers of the change
    }
}
```
* View updates the display window to reflect the changes in the turtle's orientation.

## Controller: User Executes Command 'Repeat 3 [fd 50]':
* User enters the command 'Repeat 3 [fd 50]' in the command window.
* View captures the entered command.

```
// View class
public void captureCommand(String command) {
// Capture the entered command
}
```
* Controller receives the command from the view.
```
// Controller class
public void receiveCommand(String command) {
// Receive command from view
}
```
* Controller sends the command to the model.
```
// Controller class
public void sendCommandToModel(String command) {
    model.processCommand(command);
}
```
* Model processes the command, recognizing it as a repetition.
```
// Model class
public void processCommand(String command) {
if (isRepeatCommand(command)) {
    int count = extractRepeatCount(command);
    String repeatedCommand = extractRepeatedCommand(command);
    repeatCommand(count, repeatedCommand);
} else {
        Process non-repeat command
    }
}
```
* Model repeats the specified command for the given number of times.
* Controller updates the view with the execution of each repetition.
```
// Controller class
public void updateViewForRepetition(int currentIteration) {
    view.updateRepetitionStatus(currentIteration);
}
```
* View displays the progress of the repetition to the user.

## View: User Sets Background Color to Blue:
* User selects blue as the background color from the UI.
* View captures the color selection.
```
// View class
public void captureBackgroundColor(Color color) {
// Capture the selected background color
}
```
* Controller receives the color selection from the view.
```
// Controller class
public void receiveColorSelection(Color color) {
// Receive color selection from view
}
```
* Controller sends the color selection to the model.
```
// Controller class
public void sendColorToModel(Color color) {
model.updateBackgroundColor(color);
}
```
* Model updates the background color to blue.
```
// Model class
public void updateBackgroundColor(Color color) {
// Update background color
}
```
* Model notifies the view of the background color change.
* View refreshes the display window with the new background color.
```
// View class
public void refreshDisplayWindow(Color color) {
// Refresh display window with new background color
}
```
* NOTE: This can also be handled by just the View.

## Model: User Executes Command 'setxy 100 100':
* User enters the command 'setxy 100 100' in the command window.
* View captures the entered command.
```
// View class
public void captureCommand(String command) {
    // Capture the entered command
}
```
* Controller receives the command from the view.
```
// Controller class
public void receiveCommand(String command) {
    // Receive command from view
}
```
* Controller sends the command to the model.
```
// Controller class
public void sendCommandToModel(String command) {
    model.processCommand(command);
}
```
* Model processes the command and moves the turtle to the specified position.
```
// Model class
public void processCommand(String command) {
    // Parse command and move turtle
    notifyObservers();
}
```
* Model updates the turtle's position to (100, 100).
* View updates the display window to show the turtle's new position.

## User Loads Program from File:
* User selects the option to load a program from a file.
* View prompts the user to select a file from the file system.
* User selects a program file.
* View sends the file path to the controller.
* Controller loads the program file.
* Controller sends the program commands to the model for execution.
* Model executes the commands.
* View updates the display window to reflect the execution of the program.

## User Saves Program to File:
* User selects the option to save the current program to a file.
* Controller retrieves the current program from the model.
* Controller prompts the user to choose a location and filename for the saved file.
* User selects the save location and filename.
* Controller saves the program to the specified file location.

## User Selects Language:
* User selects the language option from the UI.
* View captures the selected language choice.
* Controller receives the language selection from the view.
* Controller updates the language settings.
* View refreshes to display UI elements in the selected language.

## User Plays Animation or Types Command:
* User selects the option to play the animation.
* View sends a request to the controller to start the animation.
* Controller starts the animation.
* View updates the display window to show the animation.
* Observer used to see if the model updates.

## User Pauses Animation:
* User selects the option to pause the animation.
* View sends a request to the controller to pause the animation.
* Controller pauses the animation.
* View updates the display window to show the paused animation.
* User Steps Through Animation:
* User selects the option to step through the animation.
* View sends a request to the controller to step through the animation.
* Controller advances the animation by one step.
* View updates the display window to show the updated animation.

## User Replays Previous Animation:
* User selects the option to replay the previous animation.
* Controller retrieves the previous animation data.
* Controller starts the replay of the previous animation.
* View updates the display window to show the replayed animation. 

## User Adjusts Animation Speed:
* User selects the option to adjust the animation speed.
* View presents a slider or input field to adjust the speed.
* User adjusts the animation speed.
* View captures the new speed value.
* Controller receives the new speed value from the view.
* Controller updates the animation speed settings.
* View updates the display window to reflect the new animation speed.

## User Selects UI Theme:
* User selects the option to change the UI theme.
* View presents a list of available themes to the user.
* User selects a theme from the list.
* View captures the selected theme.
* Controller receives the selected theme from the view.
* Controller updates the UI theme settings.
* View updates the display window with the new UI theme.

## User Enters Invalid Command:
* User enters an invalid command in the command window.
* View captures the entered command.
* Controller receives the command from the view.
* Controller sends the command to the model.
* Model detects that the command is invalid OR have Controller handle it
* Model generates an error message.
* View displays the error message to the user.

## User Sets Turtle Image:
* User selects the option to set the turtle image.
* View presents a file chooser to the user.
* User selects an image file for the turtle.
* View captures the selected image file.
* Controller receives the image file from the view.
* Controller updates the turtle's image settings.
* View updates the display window to show the turtle with the new image.