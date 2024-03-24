# DESIGN Document for SLogo

### Team: 2

### Names: Philip Lee, Arnav Nayak, Prince Ahmed, Connor Johnson

## Team Roles and Responsibilities

* Team Member #1: Philip Lee
    * Model/View

* Team Member #2: Arnav Nayak
    * Model/View

* Team Member #3: Prince Ahmed
    * Model/View

* Team Member #4: Connor Johnson
    * Parser/XML

## Design Goals

Design Goals

* Goal #1: Flexibility and Extensibility
    * The design emphasizes ease in adding new functionalities, especially new language commands and
      front-end components, without modifying existing code. This flexibility is crucial for
      future-proofing the application and accommodating evolving requirements.

* Goal #2: Maintainability
    * By employing design patterns and adhering to principles like Open/Closed and Interface
      Segregation, the project structure supports straightforward maintenance. Clear separation of
      concerns and modular design facilitate updates and bug fixes.

* Goal #3: User-Friendly Interface
    * The interface design is intuitive, allowing users to add commands and interact with various
      components effortlessly. This goal ensures a positive user experience and broadens the
      application's accessibility.

#### How were Specific Features Made Easy to Add

* Feature #1: Adding commands
    * Command interface helps to create new commands where all commands must implement the execute
      function. This makes sure it works with the compatability of the parser.
    * It also allows for easy addition of new commands by implementing the Command interface and
      adding the command to the CommandFactory.
    * The command factory is responsible for creating the command object based on the command name.
    * This design allows for easy addition of new commands without modifying existing code.

* Feature #2: Front End Components
    * Adding new UI elements is streamlined, demonstrated by the example of integrating a new tab.
      Implementing the TabContent interface and updating the view facilitates this process.
    * The view is responsible for creating the tab content based on the tab name.
    * This design allows for easy addition of new tabs without modifying existing code.

* Feature #3: Adding new languages
    * Property files are used to store the language-specific strings. This design allows for easy
      addition of new languages by creating a new property file and updating the Language
      controller.
    * The language controller is responsible for loading the language-specific strings based on the
      selected language.
    * This design allows for easy addition of new languages without modifying existing code.

## High-level Design

#### Core Classes and Abstractions, their Responsibilities and Collaborators

* Class #1: CommandController
    * Acts as a mediator, orchestrating the flow from user input to command execution, underlining
      the MVC pattern's controller aspect.

* Class #2: Language and Theme Controller
    * These controllers manage application-wide settings such as language preferences and themes,
      illustrating effective application configuration handling.

* Class #3: Observer interface
    * Defines the contract for observers, enabling the implementation of the Observer pattern for
      event handling.
    * Used to handle changes to the turtle, lines, pen colors, background colors, and other
      components.

* Class #4: Load and Save File
    * Interface that defines the contract for loading and saving files, facilitating the
      implementation for file handling.
    * Used to load and save XML files.
    * Loading interface allows different types of loads such as in the Splash Screen and in the
      actual console. It allows subclasses to implement
      differing logic.
    * This allows for easy addition of saving/loading new files without modifying existing code.

## Assumptions or Simplifications

* Decision #1: Command Parsing
    * Simplified command parsing with a direct mapping approach, assuming commands follow a
      predictable structure, which might limit more complex or context-sensitive parsing scenarios.
    * This design choice was made due to difficulties with more complex parsing methods and the need
      for a simple and easy-to-understand parsing method.
    * Additionally, the priority was on the design and not the actual functionality, which the group
      had trouble implementing.

* Decision #2: Observer Pattern
    * Adopted the observer pattern for state and UI updates, assuming this would suffice for all
      reactive updates, though some scenarios might benefit from more granular or event-driven
      architectures.
    * For example, the observer pattern is used to update the turtle's position and orientation,
      lines, pen color, and background color.

* Decision #3: UI Customization
    * Assume that UI customizations (e.g., themes, colors) have a global and immediate effect
      without side effects simplifies state management and UI re-rendering logic.
    * However, dynamic or contextual theme changes could require more intricate handling.
    * We decided to keep the UI simple and not allow for dynamic theme changes as it was not a
      priority for the project.
    * However, dynamic changes still are implemented for the TurtlePane background and PenColor.

* Decision #4: Simplification
    * Due to time constraints, we limited the amount of turtles occuring or games that could be
      played.
    * The current project only allows for one turtle to be displayed at a time, and one instance of
      the game to be played at a time.

## Changes from the Original Plan

* We needed a way of switching between scenes in our application. We implemented this by using a
  SceneSwitcher interface that gives the ability to switch scenes to any controller that implements
  it. The ViewController is given this ability because it needs to manage different views.

* Because we did not have a parser, we used the Factory design pattern to create Command objects
  based on the user inputted string. The CommandFactory is responsible for initializing the
  appropriate Command object by checking the value of the string with a switch statement.

* Because we did not have a parser, all of our model's components are within the api subpackage.
  This was not the intended structure but we needed to do this to make all of our modules accessible
  to the controllers. Ideally, most of the modules would be part of an internal API and the
  controller should interface with them through the model's external API (api subpackage).

* The LineModel was initially used to store line state. This was before we implemented animations
  and we had to update the view with a change in turtle position and render a new line (which we
  stored as model state). When we implemented animation, the view only needed the initial and final
  points of the turtle (which was in the turtleModel). As a result, we did not need to store line
  state anymore and LineModel became obsolete.

## How to Add New Features

#### Features Designed to be Easy to Add

* New Commands. New commands are made by creating a new command object that implements the command
  interface. The object can store any state it needs to modify through the constructor and the
  object defines the command's behavior through the execute method (Command design pattern).

* New state. New state that needs to be stored and displayed on the screen is made easy through the
  observer interface. An example of this is command history, which must be stored as state in the
  backend and displayed in the frontend in a pane that updates in real time. Other examples include
  help docs, command xml information, and user defined variables.

* New scenes. New scenes can be made by implementing the scene abstraction. The SceneSwitcher
  interface gives the controllers ability to switch between scenes (which the ViewController does).

* New API functionality. Because we added a controller layer to our architecture, adding new API
  functionality to allow our view to run computations on model's internal state became very easy.
  For example, if we wanted the view to add a command to the command history, we would add an
  endpoint to a controller that handled the respective action (CommandController) that the view can
  call.

#### Features Not Yet Done

* Parser. We don't have a functional parser that properly interprets a user command by tokenizing it
  and parsing it using an abstract syntax tree.

* Nested commands, user-defined commands and return values. Because our parser was not implemented,
  we did not have complex command functionality, like nested commands (e.g. fd fd fd 30) or using
  return values properly (e.g. fd sum 20 30).

* Multiple turtles. We don't have functionality for multiple turtles, but this change would be easy
  to make as we just have to keep track of more model state and ensure the view handles rendering
  multiple models.

* XML Command Metadata/Help Docs. 
 