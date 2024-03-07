package slogo.model.api.parser.metadata;

public class CommandMetadata {
    private String canonicalName;
    private String description;
    private String example;
    private String help;
    private int parametersCount;
    private String implementingClass;

    public CommandMetadata(String canonicalName, String description, String example, String help, int parametersCount, String implementingClass) {
        this.canonicalName = canonicalName;
        this.description = description;
        this.example = example;
        this.help = help;
        this.parametersCount = parametersCount;
        this.implementingClass = implementingClass;
    }

    // Getters
    public String getCanonicalName() { return canonicalName; }
    public String getDescription() { return description; }
    public String getExample() { return example; }
    public String getHelp() { return help; }
    public int getParametersCount() { return parametersCount; }
    public String getImplementingClass() { return implementingClass; }
}

