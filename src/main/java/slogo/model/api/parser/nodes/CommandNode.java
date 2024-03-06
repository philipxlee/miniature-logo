package slogo.model.api.parser.nodes;

import java.util.List;

public class CommandNode extends ASTNode {
    private String commandName;
    private List<Object> arguments; // Can store both Double for numbers and String for variables

    public CommandNode(String commandName, List<Object> arguments) {
        this.commandName = commandName;
        this.arguments = arguments;
    }

    // Getters
    public String getCommandName() {
        return commandName;
    }

    public List<Object> getArguments() {
        return arguments;
    }
}
