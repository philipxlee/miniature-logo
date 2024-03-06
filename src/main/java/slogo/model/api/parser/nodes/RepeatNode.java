package slogo.model.api.parser.nodes;

import java.util.List;

public class RepeatNode extends ASTNode {
    private int times;
    private List<ASTNode> body;

    public RepeatNode(int times, List<ASTNode> body) {
        this.times = times;
        this.body = body;
    }

    // Public getters
    public int getTimes() {
        return times;
    }

    public List<ASTNode> getBody() {
        return body;
    }
}