package slogo.model.api.parser.metadata;

public class CommandMetadata {
    private String canonicalName;
    private String alias; // Alias for the command, if any
    private String description;
    private String example;
    private String help;
    private int parametersCount;
    private String implementingClass;

    public CommandMetadata(String canonicalName, String alias, String description, String example, String help, int parametersCount, String implementingClass) {
        this.canonicalName = canonicalName;
        this.alias = (alias == null || alias.isEmpty()) ? canonicalName : alias; // Use canonical name if alias is not provided
        this.description = description;
        this.example = example;
        this.help = help;
        this.parametersCount = parametersCount;
        this.implementingClass = implementingClass;
    }

    // Getters
    public String getCanonicalName() { return canonicalName; }
    public String getAlias() { return alias; }
    public String getDescription() { return description; }
    public String getExample() { return example; }
    public String getHelp() { return help; }
    public int getParametersCount() { return parametersCount; }
    public String getImplementingClass() { return implementingClass; }
}


