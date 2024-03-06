package slogo.model.api.parser.nodes;

import java.util.List;

public class ProcedureNode extends ASTNode {
    private String name;
    private List<String> parameters;
    private List<ASTNode> body;

    public ProcedureNode(String name, List<String> parameters, List<ASTNode> body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public List<ASTNode> getBody() {
        return body;
    }
}
